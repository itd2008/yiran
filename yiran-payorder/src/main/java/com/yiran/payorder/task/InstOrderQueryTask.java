package com.yiran.payorder.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import org.apache.commons.collections.CollectionUtils;
import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.netfinworks.common.domain.Extension;
import com.netfinworks.common.util.DateUtil;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.service.IApiService;
import com.yiran.paychannel.service.ITmFundChannelService;
import com.yiran.paychannel.utils.CommonConverter;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.Lock;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.CommunicateStatus;
import com.yiran.payorder.enums.LockType;
import com.yiran.payorder.enums.OrderFlag;
import com.yiran.payorder.enums.TaskResult;
import com.yiran.payorder.exception.LockException;
import com.yiran.payorder.service.IDistributeQueryService;
import com.yiran.payorder.service.IPayDistributedLockService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.InstitutionResultService;
@Service
public class InstOrderQueryTask {
	 private Logger logger = LoggerFactory.getLogger(InstOrderQueryTask.class);
	@Autowired
   	private IPayInstOrderService payInstOrderService;
	@Autowired
	private IApiService                 apiService;
    @Autowired
    private IDistributeQueryService     distributeQueryService;

    @Autowired
    private InstitutionResultService   institutionResultService;
    
    @Autowired
    private ITmFundChannelService tmFundChannelService;

    /** 每批处理数量。 */
    protected int                  batchSize      = 100;
    /** 补发步幅（分钟） */
    protected int                  resendInterval = 5;

    public void run (){
    	List<TmFundChannelApi> list = filterChannels();
    	for (TmFundChannelApi tmFundChannelApi : list) {
    		executeTask(tmFundChannelApi);
		}
    	
    }
    
    private List<TmFundChannelApi> filterChannels() {
        List<TmFundChannel> fundChannels = tmFundChannelService.loadAllFundChannels();
        if (fundChannels == null) {
            return null;
        }
        List<TmFundChannelApi> channelApis = new ArrayList<TmFundChannelApi>();
        StringBuffer channelApiStr = new StringBuffer();
        List<FundChannelApiType> apiTypeList = apiService.getQueryControlApiTypeList();
        for (TmFundChannel fundChannel : fundChannels) {
            for (FundChannelApiType apiType : apiTypeList) {
                TmFundChannelApi api = fundChannel.getApiByType(apiType);
                if (fundChannel.isAvailable() && null != api) {
                    if (!BasicConstant.API_URL_MULE.equals(api.getApiUri())) {
                        channelApis.add(api);
                        channelApiStr.append(api.getApiCode() + ",");
                    }
                }
            }
        }
        logger.info("ControlQueryTask query list :" + channelApiStr.toString());
        return channelApis;
    }

    private boolean executeTask(TmFundChannelApi fundChannelApi) {
        logger.info("单笔订单查询结果启动[" + fundChannelApi.getApiCode() + "]");
        try {
            int[] timeScope = populateTimeScope(CommonConverter.convertExtension(CommonConverter.convertFromDb(fundChannelApi.getExtension())));
            Date currentDate = new Date();
            Date startTime = DateUtil.addMinutes(currentDate, -timeScope[0]);
            Date endTime = DateUtil.addMinutes(currentDate, -timeScope[1]);

            List<String> communicateStatusList = new ArrayList<String>();
            communicateStatusList.add(CommunicateStatus.FAILURE.getCode());
            communicateStatusList.add(CommunicateStatus.SENT.getCode());
            int count = payInstOrderService.updateFlag(endTime, startTime, OrderFlag.PAUSE,
                    OrderFlag.DEFAULT, fundChannelApi.getFundChannelCode(), communicateStatusList);
            if (logger.isInfoEnabled()) {
                logger.info("[单笔控制订单查询]:" + fundChannelApi.getApiCode() + ",查询时间:" + timeScope[0]
                            + "-" + timeScope[1] + ",总共笔数" + count);
            }
            if (logger.isInfoEnabled()) {
                logger.info("[单笔控制订单查询]:" + fundChannelApi.getApiCode() + ",查询时间:"
                            + timeScope[0] + "-" + timeScope[1] + ",最大笔数" + batchSize);
            }

            List<PayInstOrder> orders = payInstOrderService.loadOrderIdForDurationQueryResultPaging(fundChannelApi.getFundChannelCode(),
                    communicateStatusList, startTime, endTime, OrderFlag.PAUSE, batchSize);
            if (CollectionUtils.isEmpty(orders)) {
                return false;
            }
            for (Iterator<PayInstOrder> iterator = orders.iterator(); iterator.hasNext();) {
                final Integer domain = iterator.next().getInstOrderId();
                payInstOrderService.updateFlagWithOrderId(domain, OrderFlag.DEFAULT);
                boolean rev = reorder(domain);
            }
           
            return true;
        } catch (Exception e) {
            logger.error("[单笔订单查询结果失败]:" + fundChannelApi.getApiCode(), e);
            return false;
        } 
    }

    /**
     * 针对一笔订单补单，查询渠道结果.
     *
     * @param instOrderId
     */
    private boolean reorder(Integer orderId) {
        try {
            logger.info("单笔控制订单查询开始查询[" + orderId + "]");
            PayInstOrder instOrder = payInstOrderService.loadWithOrderId(orderId);
            if (!InstOrderStatus.IN_PROCESS.equals(instOrder.getStatus())) {
                logger.info("单笔订单查询结果处理结束,结果已经返回[" + orderId + "]");
                return true;
            }
            ChannelFundResult channelResult = distributeQueryService.queryResult(orderId);
            PayInstOrderResult insOrderResult = null;
            if (channelResult != null) {
            	insOrderResult = institutionResultService.process(instOrder,channelResult, false);
            }
            logger.info("单笔订单查询结果处理结束[" + orderId + "],处理结果" + insOrderResult);

            return true;
        } catch (Exception e) {
            logger.error("[单笔订单查询结果失败]:" + orderId, e);
            return false;
        }
    }

    public static int[] populateTimeScope(Extension apiExtension) {
        int[] timeScope = { BasicConstant.QUERY_START_TIME, BasicConstant.QUERY_END_TIME };
        if (apiExtension != null) {
            String timeFence = apiExtension.getValue(BasicConstant.QUERY_TIME_FENCE);
            String duration = apiExtension.getValue(BasicConstant.QUERY_DURATION);
            if (!StringUtils.isEmpty(timeFence)) {
                timeScope[1] = Integer.parseInt(timeFence);
            }
            if (!StringUtils.isEmpty(duration)) {
                timeScope[0] = timeScope[1] + Integer.parseInt(duration);
            }
        }
        return timeScope;
    }

   

}
