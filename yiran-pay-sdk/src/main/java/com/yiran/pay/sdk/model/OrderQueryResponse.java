package com.yiran.pay.sdk.model;


public class OrderQueryResponse {

	/**
	 * 通讯标识
	 */
	private String returnCode;
	
	private String returnMsg;
	/**
	 * 支付结果返回code
	 */
	private String resultCode;
	/**
	 * 错误代码
	 */
	private String errCode;
	/**
	 * 错误代码描述	
	 */
	private String errCodeDes;

    /** 以下字段仅在微信h5支付返回. */
    private String appId;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 交易状态SUCCESS—支付成功REFUND—转入退款NOTPAY—未支付CLOSED—已关闭REVOKED—已撤销（付款码支付）USERPAYING--用户支付中（付款码支付）PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private String tradeState;
    /**
     * 交易状态描述
     */
    private String tradeStateDesc;
    /**
     * 微信支付订单号
     */
    private String transactionId;
    /**
     * 商户订单号
     */
    private String outTradeNo;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getTradeState() {
		return tradeState;
	}
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getTradeStateDesc() {
		return tradeStateDesc;
	}
	public void setTradeStateDesc(String tradeStateDesc) {
		this.tradeStateDesc = tradeStateDesc;
	}
    
    
}
