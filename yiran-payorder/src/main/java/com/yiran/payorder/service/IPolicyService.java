package com.yiran.payorder.service;

import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.OrderRiskStatus;

/**
 * 
 * <p>订单审核流程</p>
 */
public interface IPolicyService {

    /**
     * 提交cmf订单审核
     * @param cmfOrder
     * @return
     */
    public PayInstOrderResult apply(ChannelPayOrder channelPayOrder) throws RouteChannelException;

    /**
     * 提交cmf订单审核结果
     * @param cmfOrder
     * @param status
     * @return
     * @throws RouteChannelException
     */
    public PayInstOrderResult applyResult(ChannelPayOrder channelPayOrder, OrderRiskStatus status, boolean isSync);

}
