package com.yiran.payorder.service;

import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.OrderRiskStatus;

/**
 * 
 * <p>风险审核系统</p>
 */
public interface IRmsVerifyService {

    /**
     * 校验机构订单结果
     * @param cmfOrder
     * @param instOrder
     * @param result
     * @return
     */
    public OrderRiskStatus verify(ChannelPayOrder channelPayOrder,PayInstOrder instOrder,PayInstOrderResult result);
    
    
    /**
     * 校验cmf订单
     * @param cmfOrder
     * @return
     */
    public OrderRiskStatus verify(ChannelPayOrder cmfOrder);
    
    /**
     * 检查渠道结果信息.内部校验
     *
     * @param request
     * @return false表示有风险, true表示无风险
     */
    public boolean checkChannelResult(PayInstOrder instOrder,PayInstOrderResult result);
    
}
