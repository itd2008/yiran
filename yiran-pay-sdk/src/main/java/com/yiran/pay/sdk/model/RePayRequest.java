package com.yiran.pay.sdk.model;

import com.yiran.pay.sdk.enums.BestPayTypeEnum;


/**
 * 退款时请求参数
 */
public class RePayRequest {

    /**
     * 支付方式.
     */
    private BestPayTypeEnum payTypeEnum;

    /**
     * 订单号.
     */
    private String orderId;

    /**
     * 订单金额.
     */
    private Double orderAmount;

	public BestPayTypeEnum getPayTypeEnum() {
		return payTypeEnum;
	}

	public void setPayTypeEnum(BestPayTypeEnum payTypeEnum) {
		this.payTypeEnum = payTypeEnum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
    
    
}
