package com.yiran.payorder.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.enums.YesNo;
import com.yiran.paychannel.service.IApiService;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.paychannel.service.ITmFundChannelService;
import com.yiran.paychannel.utils.CommonConverter;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.converter.ChannelResultConverter;
import com.yiran.payorder.converter.InstOrderConverter;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.InstOrderInfo;
import com.yiran.payorder.domain.InstOrderVO;
import com.yiran.payorder.domain.OrderQuery;
import com.yiran.payorder.domain.OrderQueryResult;
import com.yiran.payorder.domain.OrderVO;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.QueryOrderResult;
import com.yiran.payorder.domaindo.PayInstOrderDO;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IDistributeQueryService;
import com.yiran.payorder.service.IPayInstOrderResultService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.IUnityResultCodeService;
import com.yiran.payorder.service.InstitutionResultService;

/**
 * <p>
 * CMF机构订单处理服务
 * </p>
 */
@Service
public class DefaultInstOrderProcessFacade implements InstOrderProcessFacade, BasicConstant {

    private static final Logger logger   = LoggerFactory.getLogger(DefaultInstOrderProcessFacade.class);

    @Autowired
   	private IPayInstOrderService payInstOrderService;

    @Autowired
   	private IChannelPayOrderService channelPayOrderService;

    @Autowired
   	private IPayInstOrderResultService payInstOrderResultService;

    @Autowired
    private IFundChannelRouter fundChannelRouter;
    @Autowired
    private InstitutionResultService   institutionResultService;

    @Autowired
    private IDistributeQueryService             distributeQueryService;

    @Autowired
    private IUnityResultCodeService unityResultCodeService;
    @Autowired
    private IApiService apiService;
    @Autowired
    private ITmFundChannelService tmFundChannelService;

    @Override
    public InstOrderVO getInstOrder(String instOrderNo) {
        Assert.isTrue(StringUtil.isNotEmpty(instOrderNo));
        if (logger.isInfoEnabled()) {
            logger.info("[查询出款订单]instOrderNo=" + instOrderNo);
        }
        InstOrderVO vo = payInstOrderService.queryFundoutOrder(instOrderNo);
        populateInstOrderVO(vo);
        ChannelPayOrder  channelPayOrder  = channelPayOrderService.loadByInstOrderId(vo.getInstOrderId());
        if (channelPayOrder != null) {
            vo.setPaymentSeqNo(channelPayOrder.getPaymentSeqNo());
        }
        if (logger.isInfoEnabled()) {
            logger.info("[查询出款订单结果]InstOrderVO=" + vo);
        }
        return vo;
    }


    private void populateInstOrderVO(InstOrderVO vo) {
        if (null != vo && null != vo.getInstOrderId()) {
            PayInstOrderResult instOrderResult = payInstOrderResultService.getLastResult(vo.getInstOrderId());
            if (null != instOrderResult) {
                vo.setMemo(instOrderResult.getMemo());
            }

            vo.setCanManualChange(false); // 默认不允许修改; 处理结束的订单不允许修改.
        }
    }


    @Override
    public InstOrderVO getInstOrderWithGateOrderNo(String instOrderNo) {
        InstOrderVO vo = getInstOrder(instOrderNo);
        if (null == vo) {
            return null;
        }

        PayInstOrder order = payInstOrderService.loadById(vo.getInstOrderId());
        vo.setGateOrderNo(order.getExtension().get(ExtensionKey.GATE_ORDER_NO.key));
        if (logger.isInfoEnabled()) {
            logger.info("[查询出款订单结果withGateOrderNo]InstOrderVO=" + vo);
        }
        return vo;
    }

    @Override
    public OrderQueryResult getOrders(OrderQuery query) {
        if (logger.isInfoEnabled()) {
            logger.info("[查询出款订单]InstOrderQuery=" + query);
        }
        if (query == null || query.getGmtCreateBegin() == null || query.getGmtCreateEnd() == null) {
            throw new RuntimeException("开始时间与结束时间不能为空");
        }

        if (StringUtil.isEmpty(query.getInstOrderNo())
            && query.getGmtCreateEnd().after(DateUtil.addDays(query.getGmtCreateBegin(), 1))) {
            throw new RuntimeException("开始时间与结束时间不能超过一天");
        }
        List<PayInstOrderDO> entityList = payInstOrderService.selectPayInstOrderListByDate(query.getGmtCreateBegin(),query.getGmtCreateEnd());
        OrderQueryResult result = new OrderQueryResult();
        List<OrderVO> results = new ArrayList<OrderVO>();
        for (PayInstOrderDO entity : entityList) {
            OrderVO vo = convert(entity);
            results.add(vo);
        }
        result.setList(results);
        result.setTotalItems(query.getTotalItem());
        if (logger.isInfoEnabled()) {
            logger.info("[查询出款订单结果]List<OrderVO>=" + ((result == null) ? null : results.size()));
        }

        return result;
    }

    private OrderVO convert(PayInstOrderDO entity) {
        OrderVO vo = new OrderVO();
        vo.setAmount(new Money(new BigDecimal(entity.getAmount())));
        vo.setApiCode(entity.getFundChannelApi());
        vo.setFundChannelCode(entity.getFundChannelCode());
        vo.setBizType(BizType.getByCode(entity.getOrderType()));
        vo.setPaySeqNo(entity.getPaySeqNo());
        vo.setCommunicateStatus(entity.getCommunicateStatus());
        vo.setExpectTime(entity.getExpectTime());
        if (entity.getExtension() != null) {
            vo.setExtension(CommonConverter.convertExtension(CommonConverter.convertFromDb(entity
                .getExtension())));
        }
        vo.setGmtCreate(entity.getGmtCreate());
        vo.setGmtModified(entity.getGmtModified());
        vo.setGmtNextRetry(entity.getGmtNextRetry());
        vo.setInstCode(entity.getInstCode());
        vo.setInstOrderId(entity.getInstOrderId());
        vo.setInstOrderNo(entity.getInstOrderNo());
        vo.setIsSplit(YesNo.getByCode(entity.getIsSplit()));
        vo.setPayMode(vo.getPayMode());
        vo.setProductCode(vo.getProductCode());
        vo.setRetryTimes(entity.getRetryTimes());
        vo.setReversalStatus(entity.getReversalStatus());
        vo.setRiskStatus(entity.getRiskStatus());
        vo.setRouteVersion(vo.getRouteVersion());
        vo.setStatus(entity.getStatus());
        return vo;
    }

    @Override
    public QueryOrderResult queryChannelResult(String instOrderNo) {
        if (logger.isInfoEnabled()) {
            logger.error("Counter-机构订单" + instOrderNo + "查询开始");
        }
        PayInstOrder instOrder = payInstOrderService.loadByInstOrderNo(instOrderNo);
        if (instOrder == null) {
            return new QueryOrderResult(InstOrderStatus.IN_PROCESS,
                "订单号不存在");
        }
        try {
            ChannelFundResult channelFundResult = null;
            TmFundChannel channel = fundChannelRouter.routerFundChannel(
                instOrder.getFundChannelCode(), FundChannelApiType.SINGLE_QUERY);
            if (channel != null && !CollectionUtils.isEmpty(channel.getApiList())) {
                channelFundResult = distributeQueryService.queryResult(instOrder.getInstOrderId());
            } 
            if (channelFundResult == null) {
                logger.info("Counter-机构订单" + instOrderNo + "查询结果为空");
                return new QueryOrderResult(InstOrderStatus.IN_PROCESS,
                    "查询结果为空");
            }
            QueryOrderResult result = convertResult(channelFundResult, instOrder);
            if (logger.isInfoEnabled()) {
                logger.info("Counter-机构订单" + instOrderNo + "查询渠道结果," + result.toString());
            }
            return result;
        } catch (Exception e) {
            logger.error("Counter-机构订单" + instOrderNo + "查询异常", e);
            return new QueryOrderResult(InstOrderStatus.FAILURE, "查询异常");
        }
    }


   /**
    * 主要方法
    */
    @Override
    public QueryOrderResult queryInstOrderResult(String instOrderNo) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("订单状态查询,订单号=" + instOrderNo);
            }
            PayInstOrder instOrder = payInstOrderService.loadByInstOrderNo(instOrderNo);

            if (instOrder == null) {
                return new QueryOrderResult(InstOrderStatus.FAILURE,
                    "查询订单不存在异常");
            }


            ChannelFundResult channelFundResult = null;

            TmFundChannel fundChannel = tmFundChannelService.getFundChannel(instOrder.getFundChannelCode());

            if (BizType.FUNDIN.equals(instOrder.getBizType())) {
                TmFundChannelApi api = fundChannel.getApiByType(FundChannelApiType.SINGLE_QUERY);
                if (api != null) {
                    //apiService.selectSingleApi(fundChannel);
                    channelFundResult = distributeQueryService.queryResult(instOrder.getInstOrderId());
                } 
            } else if (BizType.REFUND.equals(instOrder.getBizType())) {
                channelFundResult = distributeQueryService.queryResult(instOrder.getInstOrderId());
            }else if (BizType.FUNDOUT.equals(instOrder.getBizType())){
                channelFundResult = distributeQueryService.queryResult(instOrder.getInstOrderId());
            }

            PayInstOrderResult instOrderResult = null;
            if (channelFundResult != null) {
                instOrderResult = institutionResultService.process(instOrder, channelFundResult,false);
            } else {
                return new QueryOrderResult(InstOrderStatus.FAILURE,"渠道返回结果为空");
            }
            if (instOrderResult == null) {
                return new QueryOrderResult(InstOrderStatus.FAILURE,"查询异常");
            }

            InstOrderStatus status;

            String code = channelFundResult.getApiResultCode();

            String message;
            switch (instOrderResult.getStatus()) {
                case SUCCESSFUL:
                    status = InstOrderStatus.SUCCESSFUL;
                    message = "订单已成功";
                    break;
                case FAILURE:
                    status = InstOrderStatus.FAILURE;
                    message = "订单已失败";
                    break;
                case QUESTIONABLE:
                    status = InstOrderStatus.FAILURE;
                    message = "订单已失败";
                    break;
                case AWAITING:
                    status = InstOrderStatus.IN_PROCESS;
                    message = "订单处理中";
                    break;
                default:
                    message = "结果异常" + channelFundResult.getApiResultMessage();
                    status = InstOrderStatus.FAILURE;
            }
            message = message + ",银行返回结果码" + code;

            if (logger.isInfoEnabled()) {
                logger.info("订单状态查询成功,订单号=" + instOrderNo + ",MSG=" + message);
            }
            return new QueryOrderResult(status, message);
        } catch (Exception e) {
            logger.error("机构订单" + instOrderNo + "查询异常", e);
            return new QueryOrderResult(InstOrderStatus.FAILURE, "查询异常");
        }

    }

    

   
    @Override
    public List<InstOrderInfo> qurey(List<String> instOrderNos) {
        if (logger.isInfoEnabled()) {
            logger.info("query instOrders begin,request info: " + instOrderNos.toString());
        }
        Assert.isTrue(!CollectionUtils.isEmpty(instOrderNos));
        try {
            List<PayInstOrder> instOrders = payInstOrderService.loadByInstOrderNos(instOrderNos);
            List<InstOrderInfo> instOrderInfos = new ArrayList<InstOrderInfo>();
            if (!CollectionUtils.isEmpty(instOrders)) {
                for (PayInstOrder instOrder : instOrders) {
                    instOrderInfos.add(InstOrderConverter.convertToInstOrderInfo(instOrder));
                }
            }
            if (logger.isInfoEnabled()) {
                logger.info("query instOrders end,response info: " + instOrderInfos.toString());
            }
            return instOrderInfos;
        } catch (Exception e) {
            logger.error("query instOrders end, error info ", e);
            return null;
        }

    }

    
    /**
     * 转换结果
     * 
     * @param channelFundResult
     * @return
     */
    private QueryOrderResult convertResult(ChannelFundResult channelFundResult, PayInstOrder instOrder) {
        PayInstOrderResult instResult = ChannelResultConverter.convert(channelFundResult, instOrder);

        InstOrderStatus status;
        // 设置统一返回码
        unityResultCodeService.fillResultStatus(instResult, instOrder.getFundChannel()
            .getFundChannelCode());

        String message;
        switch (instResult.getStatus()) {
            case SUCCESSFUL:
                status = InstOrderStatus.SUCCESSFUL;
                message = "查询返回订单已成功";
                break;
            case FAILURE:
                status = InstOrderStatus.FAILURE;
                message = "查询返回订单已失败";
                break;
            case QUESTIONABLE:
                status = InstOrderStatus.FAILURE;
                message = "查询返回订单已失败";
                break;
            case AWAITING:
                status = InstOrderStatus.IN_PROCESS;
                message = "查询返回订单处理中";
                break;
            default:
                message = "查询返回结果异常" + channelFundResult.getApiResultMessage();
                status = InstOrderStatus.FAILURE;
        }
        return new QueryOrderResult(status, message);
    }

}
