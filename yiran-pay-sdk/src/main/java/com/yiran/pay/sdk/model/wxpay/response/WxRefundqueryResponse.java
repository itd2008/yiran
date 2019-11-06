package com.yiran.pay.sdk.model.wxpay.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 退款查询响应
 * @author pandaa
 *
 */
@Root(name = "xml", strict = false)
public class WxRefundqueryResponse {
	
	//返回状态码  return_code
	@Element(name = "return_code")
    private String returnCode;

	//返回信息	return_msg
    @Element(name = "return_msg", required = false)
    private String returnMsg;

    /** 以下字段在return_code为SUCCESS的时候有返回. */
    //业务结果	result_code  
    @Element(name = "result_code")
    private String resultCode;
    
    //错误码	err_code
    @Element(name = "err_code")
    private String errCode;
    
    //错误描述	err_code_des
    @Element(name = "err_code_des")
    private String errCodeDes;
    
    //公众账号ID	appid
    @Element(name = "appid")
    private String appid;
    
    //商户号	mch_id
    @Element(name = "mch_id")
    private String mchId;
    
    //随机字符串	nonce_str
    @Element(name = "nonce_str")
    private String nonceStr;
    
    //签名	sign
    @Element(name = "sign")
    private String sign;
    
    //订单总退款次数	total_refund_count
    @Element(name = "total_refund_count")
    private String totalRefundCount;
    
    //微信订单号	transaction_id
    @Element(name = "transaction_id")
    private String transactionId;
    
    //商户订单号	out_trade_no
    @Element(name = "out_trade_no")
    private String outTradeNo;
    
    //订单金额	total_fee
    @Element(name = "total_fee")
    private String totalFee;
    
    //应结订单金额	settlement_total_fee
    @Element(name = "settlement_total_fee")
    private String settlementTotalFee;
    
    //货币种类	fee_type
    @Element(name = "fee_type")
    private String feeType;
    
    //现金支付金额	cash_fee
    @Element(name = "cash_fee")
    private String cashFee;
    
    //退款笔数	refund_count
    @Element(name = "refund_count")
    private String refund_count;
    
    //商户退款单号	out_refund_no_$n
    @Element(name = "out_refund_no_$n")
    private String outRefundNo$n;
    
    //微信退款单号	refund_id_$n
    @Element(name = "refund_id_$n")
    private String refundId$n;
    
    //退款渠道	refund_channel_$n
    @Element(name = "refund_channel_$n")
    private String refundChannel$n;
    
    //申请退款金额	refund_fee_$n
    @Element(name = "refund_fee_$n")
    private String refundFee$n;
    
    //退款金额	settlement_refund_fee_$n
    @Element(name = "settlement_refund_fee_$n")
    private String settlementRefundFee$n;
    
    //代金券类型	coupon_type_$n_$m
    @Element(name = "coupon_type_$n_$m")
    private String couponType$n$m;
    
    //总代金券退款金额	coupon_refund_fee_$n
    @Element(name = "coupon_refund_fee_$n")
    private String couponRefundFee$n;
    
    //退款代金券使用数量	coupon_refund_count_$n
    @Element(name = "coupon_refund_count_$n")
    private String couponRefundCount$n;
    
    //退款代金券ID	coupon_refund_id_$n_$m
    @Element(name = "coupon_refund_id_$n_$m")
    private String couponRefundId$n$m;
    
    //单个代金券退款金额	coupon_refund_fee_$n_$m
    @Element(name = "coupon_refund_fee_$n_$m")
    private String couponRefundFee$n$m;
    
    //退款状态	refund_status_$n
    @Element(name = "refund_status_$n")
    private String refundStatus$n;
    
    //退款资金来源	refund_account_$n
    @Element(name = "refund_account_$n")
    private String refundAccount$n;
    
    //退款入账账户	refund_recv_accout_$n
    @Element(name = "refund_recv_accout_$n")
    private String refundRecvAccout$n;
    
    //退款成功时间	refund_success_time_$n
    @Element(name = "refund_success_time_$n")
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

	public String getTotalRefundCount() {
		return totalRefundCount;
	}

	public void setTotalRefundCount(String totalRefundCount) {
		this.totalRefundCount = totalRefundCount;
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

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public void setSettlementTotalFee(String settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
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

	public String getRefund_count() {
		return refund_count;
	}

	public void setRefund_count(String refund_count) {
		this.refund_count = refund_count;
	}

	public String getOutRefundNo$n() {
		return outRefundNo$n;
	}

	public void setOutRefundNo$n(String outRefundNo$n) {
		this.outRefundNo$n = outRefundNo$n;
	}

	public String getRefundId$n() {
		return refundId$n;
	}

	public void setRefundId$n(String refundId$n) {
		this.refundId$n = refundId$n;
	}

	public String getRefundChannel$n() {
		return refundChannel$n;
	}

	public void setRefundChannel$n(String refundChannel$n) {
		this.refundChannel$n = refundChannel$n;
	}

	public String getRefundFee$n() {
		return refundFee$n;
	}

	public void setRefundFee$n(String refundFee$n) {
		this.refundFee$n = refundFee$n;
	}

	public String getSettlementRefundFee$n() {
		return settlementRefundFee$n;
	}

	public void setSettlementRefundFee$n(String settlementRefundFee$n) {
		this.settlementRefundFee$n = settlementRefundFee$n;
	}

	public String getCouponType$n$m() {
		return couponType$n$m;
	}

	public void setCouponType$n$m(String couponType$n$m) {
		this.couponType$n$m = couponType$n$m;
	}

	public String getCouponRefundFee$n() {
		return couponRefundFee$n;
	}

	public void setCouponRefundFee$n(String couponRefundFee$n) {
		this.couponRefundFee$n = couponRefundFee$n;
	}

	public String getCouponRefundCount$n() {
		return couponRefundCount$n;
	}

	public void setCouponRefundCount$n(String couponRefundCount$n) {
		this.couponRefundCount$n = couponRefundCount$n;
	}

	public String getCouponRefundId$n$m() {
		return couponRefundId$n$m;
	}

	public void setCouponRefundId$n$m(String couponRefundId$n$m) {
		this.couponRefundId$n$m = couponRefundId$n$m;
	}

	public String getCouponRefundFee$n$m() {
		return couponRefundFee$n$m;
	}

	public void setCouponRefundFee$n$m(String couponRefundFee$n$m) {
		this.couponRefundFee$n$m = couponRefundFee$n$m;
	}

	public String getRefundStatus$n() {
		return refundStatus$n;
	}

	public void setRefundStatus$n(String refundStatus$n) {
		this.refundStatus$n = refundStatus$n;
	}

	public String getRefundAccount$n() {
		return refundAccount$n;
	}

	public void setRefundAccount$n(String refundAccount$n) {
		this.refundAccount$n = refundAccount$n;
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
