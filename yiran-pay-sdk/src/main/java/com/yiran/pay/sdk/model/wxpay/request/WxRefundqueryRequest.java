package com.yiran.pay.sdk.model.wxpay.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
/**
 * 退款查询请求
 * @author pandaa
 *
 */
@Root(name = "xml", strict = false)
public class WxRefundqueryRequest {

	//公众账号ID	appid
	@Element(name = "appid")
	private String appid;
	
	//商户号	mch_id
	@Element(name = "mch_id")
	private String mchId;
	
	//随机字符串	nonce_str
	@Element(name="nonce_str")
	private String nonceStr;
	//签名	sign
	@Element(name="sign")
	private String sign;
	
	//签名类型	sign_type
	@Element(name="sign_type")
	private String signType;
	
	//微信订单号	transaction_id	
	@Element(name = "transaction_id", required = false)
    private String transactionId;

	//商户订单号	out_trade_no
    @Element(name = "out_trade_no", required = false)
    private String outTradeNo;
    
    //商户退款单号	out_refund_no
    @Element(name = "out_refund_no", required = false)
    private String outRefund_No;
    
    //微信退款单号	refund_id
    @Element(name = "refund_id")
    private String refundId;
    
    //偏移量	offset
    @Element(name = "offset")
    private String offset;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
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

	public String getOutRefund_No() {
		return outRefund_No;
	}

	public void setOutRefund_No(String outRefund_No) {
		this.outRefund_No = outRefund_No;
	}

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}
    
    
}
