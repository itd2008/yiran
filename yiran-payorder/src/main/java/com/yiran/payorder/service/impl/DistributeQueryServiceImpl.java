package com.yiran.payorder.service.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.paychannel.service.ITmChannelTransInfoService;
import com.yiran.payorder.converter.ChannelRequestConverter;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.exception.CommunicateException;
import com.yiran.payorder.service.IChannelSendService;
import com.yiran.payorder.service.IDistributeQueryService;
import com.yiran.payorder.service.IPayInstOrderService;

/**
 * 统一分发查询接口实现
 */
@Service
public class DistributeQueryServiceImpl implements IDistributeQueryService {

    private static final Logger             logger = LoggerFactory.getLogger(DistributeQueryServiceImpl.class);
    @Autowired
	private IPayInstOrderService payInstOrderService;
    /** 资金渠道路由器 */
    @Autowired
    private IFundChannelRouter fundChannelRouter;
    @Autowired
    private IChannelSendService channelSendService;
    
    @Autowired
    private ITmChannelTransInfoService channelTransInfoService;
    @Override
    public ChannelFundResult queryResult(Integer instOrderId) throws CommunicateException,
                                                          RouteChannelException {
        PayInstOrder instOrder = payInstOrderService.loadById(instOrderId);
        ChannelFundResult channelFundResult = null;
        TmFundChannel channel = null;
        if (BizType.REFUND.equals(instOrder.getBizType())) {
            if (FundChannelApiType.MANUAL_REFUND.equals(instOrder.getFundChannelApi().getApiType())) {
                return null;
            }
            PayInstOrder orgiInstOrder = payInstOrderService.loadByInstOrderNo(instOrder.getInstOrderNo());
            channel = fundChannelRouter.routerFundChannel(instOrder.getFundChannelCode(),
                FundChannelApiType.SINGLE_REFUND_QUERY);
            //部分退款不走查询,因为害怕查回来结果与订单信息部匹配
            //1.支付5.00,第一次部分退款1.00成功
            //2.第二次部分退款1.00发送失败
            //3.查询就会把第一笔部分退款结果查回来,导致处理中的订单变为成功
            //4.如果渠道是通过订单号查询的,则可配置渠道支持部分退款查询
            if (channel.getApiList() != null && channel.getApiList().size() > 0
                && !orgiInstOrder.getAmount().equals(instOrder.getAmount())) {
                logger.info("单笔冲退查询订单结果处理结束[" + instOrderId + "],部分退款订单不查询");
                return null;
            }

        } else {
            channel = fundChannelRouter.routerFundChannel(instOrder.getFundChannelCode(),FundChannelApiType.SINGLE_QUERY);
        }
        if (channel.getApiList() != null && channel.getApiList().size() > 0) {
            instOrder.setFundChannelApi(channel.getApi());
            channelFundResult = channelSendService.query(ChannelRequestConverter.covertQuery(
                instOrder,channelTransInfoService));
            if (channelFundResult != null) {
                logger.info("查询结果,机构订单id:[" + instOrderId + "],结果:" + channelFundResult.toString());
            }
        }
        return channelFundResult;
    }


}
