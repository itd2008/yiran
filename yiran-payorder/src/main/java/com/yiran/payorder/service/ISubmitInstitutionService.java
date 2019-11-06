package com.yiran.payorder.service;

import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrderResult;

/**
 * <p>提交机构服务</p>
 */
public interface ISubmitInstitutionService {

    /**
     * 提交资金订单
     * @param channelPayOrder
     * @param isSync 判断是否要通知PE
     * @return
     * @throws RouteChannelException
     */
    public PayInstOrderResult submit(ChannelPayOrder channelPayOrder, boolean isSync) throws RouteChannelException;

    
}
