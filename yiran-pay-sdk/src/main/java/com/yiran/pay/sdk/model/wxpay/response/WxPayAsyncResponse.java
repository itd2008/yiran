package com.yiran.pay.sdk.model.wxpay.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 异步返回参数
 */
@Root(name = "xml", strict = false)
public class WxPayAsyncResponse {

    @Element(name = "return_code")
    private String returnCode;

    @Element(name = "return_msg", required = false)
    private String returnMsg;

    /** 以下字段在return_code为SUCCESS的时候有返回. */
    @Element(name = "appid", required = false)
    private String appid;

    @Element(name = "mch_id", required = false)
    private String mchId;

    @Element(name = "device_info", required = false)
    private String deviceInfo;

    @Element(name = "nonce_str", required = false)
    private String nonceStr;

    @Element(name = "sign", required = false)
    private String sign;

    @Element(name = "result_code", required = false)
    private String resultCode;

    @Element(name = "err_code", required = false)
    private String errCode;

    @Element(name = "err_code_des", required = false)
    private String errCodeDes;

    @Element(name = "openid", required = false)
    private String openid;

    @Element(name = "is_subscribe", required = false)
    private String isSubscribe;

    @Element(name = "trade_type", required = false)
    private String tradeType;

    @Element(name = "bank_type", required = false)
    private String bankType;

    @Element(name = "total_fee", required = false)
    private Integer totalFee;

    @Element(name = "fee_type", required = false)
    private String feeType;

    @Element(name = "cash_fee", required = false)
    private String cashFee;

    @Element(name = "cash_fee_type", required = false)
    private String cashFeeType;

    @Element(name = "coupon_fee", required = false)
    private String couponFee;

    @Element(name = "coupon_count", required = false)
    private String couponCount;

    @Element(name = "transaction_id", required = false)
    private String transactionId;

    @Element(name = "out_trade_no", required = false)
    private String outTradeNo;

    @Element(name = "attach", required = false)
    private String attach;

    @Element(name = "time_end", required = false)
    private String timeEnd;

    @Element(name = "mweb_url", required = false)
    private String mwebUrl;

    //支付优惠时多返回字段
    @Element(name = "settlement_total_fee", required = false)
    private Integer settlementTotalFee;

    @Element(name = "coupon_type", required = false)
    private String couponType;

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

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getCashFee() {
		return cashFee;
	}

	public void setCashFee(String cashFee) {
		this.cashFee = cashFee;
	}

	public String getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public String getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(String couponFee) {
		this.couponFee = couponFee;
	}

	public String getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(String couponCount) {
		this.couponCount = couponCount;
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

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getMwebUrl() {
		return mwebUrl;
	}

	public void setMwebUrl(String mwebUrl) {
		this.mwebUrl = mwebUrl;
	}

	public Integer getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public void setSettlementTotalFee(Integer settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
    
    
}