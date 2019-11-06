package com.yiran.paychannel.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.ControlRequestType;
import com.yiran.paychannel.enums.ErrorCode;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.paychannel.filter.FundChannelFilter;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.paychannel.service.ITmFundChannelApiService;
import com.yiran.paychannel.service.ITmFundChannelService;

import cn.hutool.core.lang.Assert;

@Service
public class FundChannelRouterImpl implements IFundChannelRouter {
	 protected static final Logger logger = LoggerFactory.getLogger(FundChannelRouterImpl.class);
	@Autowired
	private ITmFundChannelService tmFundChannelService;
	@Autowired
	private ITmFundChannelApiService tmFundChannelApiService;
	@Autowired
	private FundChannelFilter fundChannelFilter;
	@Override
	public TmFundChannel routerFundChannel(String targetInst, PayMode payMode, String requestType, Map<String, ?> param)
			throws RouteChannelException {
		 if (logger.isInfoEnabled()) {
	            logger.info("渠道路由->过滤参数信息:目标机构=" + targetInst + ",支付方式=" + payMode + ",请求类型=" + requestType + ",Map参数" + param);
	        }
		//将来如果前端传多个支付模式也支持,只需要将前端传过来扩展参数put即可
        List<String> payModeList = new ArrayList<String>();
        payModeList.add(payMode.getCode());
        List<TmFundChannel> sourceList = tmFundChannelService.getAvailableFundChannels(targetInst,payModeList);

        return routerFundChannel(sourceList, requestType, param);
	}

	private TmFundChannel routerFundChannel(List<TmFundChannel> sourceList, String requestType, Map<String, ?> param)throws RouteChannelException {
		assertObjectNotNull(sourceList, ErrorCode.ROUTE_ERROR_NO_VALID_CHANNEL, "没有可用渠道！");
        if (logger.isInfoEnabled()) {
            logger.info("过滤之前有" + sourceList.size() + "资金源");
        }
        fundChannelFilter.filter(sourceList, requestType, param);
        StringBuilder sb = new StringBuilder(32);
        for (Iterator<TmFundChannel> it = sourceList.iterator(); it.hasNext();) {
            TmFundChannel tempChannel = it.next();
            sb.append(tempChannel.getFundChannelCode()).append(",");
        }
        if (logger.isInfoEnabled()) {
            logger.info("过滤之后有" + sourceList.size() + "资金源[" + sb.toString() + "]");
        }
        assertObjectNotNull(sourceList, ErrorCode.ROUTE_ERROR_NO_VALID_CHANNEL, "过滤后没有可用渠道！");
        TmFundChannel fundChannel = sourceList.size()>0?sourceList.get(0):null;;
        //设置路由版本号
        //fundChannel.setRouteVersion(fundChannelManager.getRouteVersion().intValue());
        return fundChannel;
	}

	@Override
	public TmFundChannel routerFundChannel(String fundChannelCode, FundChannelApiType apiType) {
		TmFundChannel fundChannel = tmFundChannelService.selectTmFundChannelById(fundChannelCode);
        tmFundChannelApiService.filterWithApiType(fundChannel, apiType);
		return fundChannel;
	}

	@Override
	public TmFundChannel routerFundChannel(String fundChannelCode, String targetInst, PayMode payMode,
			String requestType, Map<String, ?> param) throws RouteChannelException {
		logger.info("渠道路由fundChannelCode["+fundChannelCode+"],targetInst["+targetInst+
    			"],payMode["+payMode.getCode()+"],requestType["+requestType+
    			"],param["+JSON.toJSONString(param)+"]");
        List<TmFundChannel> resultList = new ArrayList<TmFundChannel>();
        List<String> payModeList = new ArrayList<String>();
        payModeList.add(payMode.getCode());
        /*if (PayMode.QUICKPAY.equals(payMode)) {
        	//快捷的自动增加代扣的支付方式
        	payModeList.add(PayMode.TRUSTCOLLECT.getCode());
        }*/
        List<TmFundChannel> sourceList = tmFundChannelService.getAvailableFundChannels(targetInst,payModeList);
        boolean checked = false;
        for (TmFundChannel channel : sourceList) {
            if (fundChannelCode.equals(channel.getFundChannelCode())) {
                checked = true;
                resultList.add(channel);
                break;
            }
        }
        //快捷支付发送短信特殊处理，包含代扣通道
        if (PayMode.QUICKPAY.equals(payMode)
        		&&ControlRequestType.SEND_MESSAGE.getCode().equals(requestType)) {
        	payModeList = new ArrayList<String>();
        	//payModeList.add(PayMode.TRUSTCOLLECT.getCode());
        	sourceList = tmFundChannelService.getAvailableFundChannels(targetInst,payModeList);
        	resultList.addAll(sourceList);
        	return routerFundChannel(resultList, requestType, param);
		}else{
			assertTrue(checked, ErrorCode.ROUTE_ERROR_NO_VALID_CHANNEL, "可用通道中不包含指定的通道"+fundChannelCode);
			TmFundChannel fundChannel = tmFundChannelService.selectTmFundChannelById(fundChannelCode);
	        sourceList = new ArrayList<TmFundChannel>();
	        sourceList.add(fundChannel);
	        fundChannelFilter.filter(sourceList, requestType, param);
	        assertObjectNotNull(sourceList, ErrorCode.ROUTE_ERROR_NO_VALID_CHANNEL, "过滤后没有可用渠道！");
	        //设置路由版本号
	        //fundChannel.setRouteVersion(fundChannelManager.getRouteVersion().intValue());
	        return fundChannel;
		}
	}

	private void assertTrue(boolean flag, ErrorCode errorCode, String message)
            throws RouteChannelException {
    	if (!flag)
		throw new RouteChannelException(errorCode, message);
	}
	@Override
	public TmFundChannel routerRefundChannel(String fundChannelCode) throws RouteChannelException {
		return tmFundChannelService.selectTmFundChannelById(fundChannelCode);
	}

	@Override
	public TmFundChannel getFundChannel(String fundChannelCode) throws RouteChannelException {
		 TmFundChannel fundChannel = tmFundChannelService.selectTmFundChannelById(fundChannelCode);

        if (tmFundChannelApiService.filterWithApiType(fundChannel, FundChannelApiType.SINGLE_REFUND)) {
        	tmFundChannelApiService.filterWithApiType(fundChannel, FundChannelApiType.SINGLE_REFUND);
        } else {
        	tmFundChannelApiService.filterWithApiType(fundChannel, FundChannelApiType.MANUAL_REFUND);
        }
        assertObjectNotNull(fundChannel.getApiList(), ErrorCode.ROUTE_ERROR_NO_VALID_CHANNEL, "过滤后没有可用渠道！");
        //设置路由版本号
       // fundChannel.setRouteVersion(fundChannelManager.getRouteVersion().intValue());
        return fundChannel;
	}

	@Override
	public TmFundChannelApi getFundChannelApi(String fundChannelApiCode) {
		return tmFundChannelApiService.selectTmFundChannelApiById(fundChannelApiCode);
	}

	/**
     * 判断对象非空,属于下面三种情况,抛异常
     * <p>1.对象为空</p>
     * <p>2.对象为String类型,并且字符串为空</p>
     * <p>3.对象为List类型,并且size==0</p>
     * @param o
     * @param errorCode
     * @throws RouteChannelException
     */
    @SuppressWarnings("rawtypes")
	private void assertObjectNotNull(Object o, ErrorCode errorCode, String message)
                        throws RouteChannelException {
        if (o == null)
            throw new RouteChannelException(errorCode, message);
        if (o instanceof String && StringUtils.isEmpty((String) o))
            throw new RouteChannelException(errorCode, message);
        if (o instanceof List && ((List) o).size() == 0)
            throw new RouteChannelException(errorCode, message);
    }
}
