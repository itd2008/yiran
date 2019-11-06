package com.yiran.pay.sdk.model.alipay.response;

public class AliResponse {
	/**
	 * 网关返回码
	 */
	private String code;
	/**
	 * 网关返回码描述
	 */
	private String msg;
	/**
	 * 	业务返回码(支付宝)
	 */
	private String subCode;
	/**
	 * 业务返回码描述(支付宝)
	 */
	private String subMsg;
	/**
	 * 签名(支付宝)
	 */
	private String sign;
	
	/**
	 * 支付宝交易号
	 */
	private String tradeNo;
	
	/**
	 * 收款支付宝账号对应的支付宝唯一用户号。 以2088开头的纯16位数字
	 */
	private String sellerId;
	/**
	 * 交易金额
	 */
	private String totalAmount;
	/**
	 * 商户原始订单号，最大长度限制32位
	 */
	private String merchantOrderNo;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubMsg() {
		return subMsg;
	}
	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

}
