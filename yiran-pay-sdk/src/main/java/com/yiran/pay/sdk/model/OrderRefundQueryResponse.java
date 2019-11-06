package com.yiran.pay.sdk.model;

public class OrderRefundQueryResponse {

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
     * 商户ID
     */
    private String mchId;
    
    /**
     * 微信支付订单号
     */
    private String transactionId;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    
    /**
     * 退款状态	refund_status_$n
     */
    private String refundStatus$n;
    
    /**
     * 退款入账账户	refund_recv_accout_$n
     */
    private String refundRecvAccout$n;
    /**
     * 退款成功时间	refund_success_time_$n
     */
    private String refundSuccessTime$n;
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
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
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
	public String getRefundStatus$n() {
		return refundStatus$n;
	}
	public void setRefundStatus$n(String refundStatus$n) {
		this.refundStatus$n = refundStatus$n;
	}
	public String getRefundRecvAccout$n() {
		return refundRecvAccout$n;
	}
	public void setRefundRecvAccout$n(String refundRecvAccout$n) {
		this.refundRecvAccout$n = refundRecvAccout$n;
	}
	public String getRefundSuccessTime$n() {
		return refundSuccessTime$n;
	}
	public void setRefundSuccessTime$n(String refundSuccessTime$n) {
		this.refundSuccessTime$n = refundSuccessTime$n;
	}
    
    
    
}
