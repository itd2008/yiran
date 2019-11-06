package com.yiran.pay.sdk.model;

import com.yiran.pay.sdk.model.alipay.response.AliResponse;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支付时的同步返回参数
 */
public class PayFundResponse extends AliResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5656345059848075991L;

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
	
    private String prePayParams;

    private URI payUri;

    /** 以下字段仅在微信h5支付返回. */
    private String appId;

    private String timeStamp;

    private String nonceStr;

    //@JsonProperty("package")
    private String packAge;

    private String signType;

    private String paySign;

    /** 以下字段在微信异步通知下返回. */
    private Double orderAmount;

    private String orderId;

    //第三方支付的流水号
    private String outTradeNo;

    /** 以下支付是h5支付返回*/
    private String mwebUrl;
    /**
     * 预支付交易会话标识
     */
    private String prepayId;
    /**
     * 二维码链接
     */
    private String codeUrl;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * HTML文本
     */
    private String htmlText;
    
    protected Map<String, String> extension        = new ConcurrentHashMap<String, String>();
    
    
	public String getHtmlText() {
		return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

	public Map<String, String> getExtension() {
		return extension;
	}

	public void setExtension(Map<String, String> extension) {
		this.extension = extension;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPrePayParams() {
		return prePayParams;
	}

	public void setPrePayParams(String prePayParams) {
		this.prePayParams = prePayParams;
	}

	public URI getPayUri() {
		return payUri;
	}

	public void setPayUri(URI payUri) {
		this.payUri = payUri;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackAge() {
		return packAge;
	}

	public void setPackAge(String packAge) {
		this.packAge = packAge;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getMwebUrl() {
		return mwebUrl;
	}

	public void setMwebUrl(String mwebUrl) {
		this.mwebUrl = mwebUrl;
	}

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
    
	
    
}
