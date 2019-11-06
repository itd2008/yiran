package com.yiran.api.vo;

import java.io.Serializable;

public class VerifySignRequestVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8832236348557128395L;

	/**基本参数*/
	//商户编号
	private String oid_partner;
	
	//签名方式
	private String sign_type;
	//签名
	private String sign;
	/**订单参数*****/
	//订单时间
	private String dt_order;
	//商户退款流水号
	private String no_refund;
	//商户退款时间
	private String dt_refund;
	//退款金额
	private String money_refund;
	//连连银通退款流水号
	private String oid_refundno;
	//退款状态
	private String sta_refund;
	//商户唯一订单号
	private String no_order;
	//连连支付单号
	private String oid_paybill;
	//交易金额
	private String money_order;
	//支付结果
	private String result_pay;
	//清算日期
	private String settle_date;
	//订单描述
	private String info_order;
	//支付方式
	private String pay_type;
	//银行编号
	private String bank_code;
	//协议号
	private String no_agree;
	//证件类型 
	private String id_type;
	//证件号码
	private String id_no;
	//银行账号姓名
	private String acct_name;
	//银行卡号 
	private String card_no;
	
	public String getOid_partner() {
		return oid_partner;
	}
	public void setOid_partner(String oid_partner) {
		this.oid_partner = oid_partner;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getDt_order() {
		return dt_order;
	}
	public void setDt_order(String dt_order) {
		this.dt_order = dt_order;
	}
	public String getNo_order() {
		return no_order;
	}
	public void setNo_order(String no_order) {
		this.no_order = no_order;
	}
	public String getOid_paybill() {
		return oid_paybill;
	}
	public void setOid_paybill(String oid_paybill) {
		this.oid_paybill = oid_paybill;
	}
	public String getMoney_order() {
		return money_order;
	}
	public void setMoney_order(String money_order) {
		this.money_order = money_order;
	}
	public String getResult_pay() {
		return result_pay;
	}
	public void setResult_pay(String result_pay) {
		this.result_pay = result_pay;
	}
	public String getSettle_date() {
		return settle_date;
	}
	public void setSettle_date(String settle_date) {
		this.settle_date = settle_date;
	}
	public String getInfo_order() {
		return info_order;
	}
	public void setInfo_order(String info_order) {
		this.info_order = info_order;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getNo_agree() {
		return no_agree;
	}

	public void setNo_agree(String no_agree) {
		this.no_agree = no_agree;
	}
	public String getId_type() {
		return id_type;
	}
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getNo_refund() {
		return no_refund;
	}
	public void setNo_refund(String no_refund) {
		this.no_refund = no_refund;
	}
	public String getDt_refund() {
		return dt_refund;
	}
	public void setDt_refund(String dt_refund) {
		this.dt_refund = dt_refund;
	}
	public String getMoney_refund() {
		return money_refund;
	}
	public void setMoney_refund(String money_refund) {
		this.money_refund = money_refund;
	}
	public String getOid_refundno() {
		return oid_refundno;
	}
	public void setOid_refundno(String oid_refundno) {
		this.oid_refundno = oid_refundno;
	}
	public String getSta_refund() {
		return sta_refund;
	}
	public void setSta_refund(String sta_refund) {
		this.sta_refund = sta_refund;
	}

	
	
	

}
