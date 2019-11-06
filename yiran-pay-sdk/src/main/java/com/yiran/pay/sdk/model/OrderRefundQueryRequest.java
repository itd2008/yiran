package com.yiran.pay.sdk.model;



/**
 * 退款查询
 */
public class OrderRefundQueryRequest {

  

	/**
	 * 商户订单号
	 */
    private String orderId;


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
}
