package com.yiran.pay.sdk.model.wxpay.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xml", strict = false)
public class WxPayUnifiedorderRequest {

    @Element(name = "appid")
    private String appid;

    @Element(name = "mch_id")
    private String mchId;

    @Element(name = "nonce_str")
    private String nonceStr;

    @Element(name = "sign")
    private String sign;

    @Element(name = "attach", required = false)
    private String attach;

    @Element(name = "body", required = false)
    private String body;

    @Element(name = "detail", required = false)
    private String detail;

    @Element(name = "notify_url")
    private String notifyUrl;

    @Element(name = "openid", required=false)
    private String openid;

    @Element(name = "out_trade_no")
    private String outTradeNo;

    @Element(name = "spbill_create_ip")
    private String spbillCreateIp;

    @Element(name = "total_fee")
    private Integer totalFee;

    @Element(name = "trade_type")
    private String tradeType;

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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
    
    
}
