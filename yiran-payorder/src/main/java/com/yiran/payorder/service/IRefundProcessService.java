package com.yiran.payorder.service;

import com.yiran.paychannel.enums.ManualRefundType;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;

/**
 * 
 * <p>退款处理</p>
 */
public interface IRefundProcessService {

	/**
	 * 处理冲退失败订单业务
	 * @param instOrder
	 * @param result
	 */
	public void processRefund(PayInstOrder instOrder,ManualRefundType refundType);
	
	
	/**
     * 处理冲退失败订单业务
     * @param instOrder
     * @param result
     */
    public void processRefund(PayInstOrder instOrder, PayInstOrderResult result);
	
}
