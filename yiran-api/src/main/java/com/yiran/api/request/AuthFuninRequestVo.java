package com.yiran.api.request;

import java.io.Serializable;

public class AuthFuninRequestVo implements Serializable{
	private static final long serialVersionUID = -3859013971634623392L;
	//平台来源
	private String platform;
	//商户用户唯一编号
	private String user_id;
	//交易结算商户编号
	private String oid_partner;
	//签名方式
	private String sign_type;
	//签名
	private String sign;
	//商户业务类型
	private String busi_partner;
	//版本号
	private String api_version;
	//商户唯一订单号
	private String no_order;
	//商户订单时间
	private String dt_order;
	//商品名称
	private String name_goods;
	//订单描述
	private String info_order;
	//交易金额
	private String money_order;
	//服务器异步通知地址
	private String notify_url;
	//订单有效时间       *可空，此时默认为7天，过时查询会显示失败状态
	private String valid_order;
	//风控参数
	private String risk_item;
	//支付方式
	private String pay_type;
	//银行卡号
	private String card_no;
	//银行编号
	private String bank_code;
	//银行账号姓名
	private String acct_name;
	//绑定手机号
	private String bind_mob;
	//证件类型
	private String id_type;
	//证件号
	private String id_no;
	//签约协议号
	private String no_agree;
	//短信参数
	private String sms_param;

	public String getSms_param() {
		return sms_param;
	}

	public void setSms_param(String sms_param) {
		this.sms_param = sms_param;
	}

	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
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
	public String getBusi_partner() {
		return busi_partner;
	}
	public void setBusi_partner(String busi_partner) {
		this.busi_partner = busi_partner;
	}
	public String getApi_version() {
		return api_version;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}
	public String getNo_order() {
		return no_order;
	}
	public void setNo_order(String no_order) {
		this.no_order = no_order;
	}
	public String getDt_order() {
		return dt_order;
	}
	public void setDt_order(String dt_order) {
		this.dt_order = dt_order;
	}
	public String getName_goods() {
		return name_goods;
	}
	public void setName_goods(String name_goods) {
		this.name_goods = name_goods;
	}
	public String getInfo_order() {
		return info_order;
	}
	public void setInfo_order(String info_order) {
		this.info_order = info_order;
	}
	public String getMoney_order() {
		return money_order;
	}
	public void setMoney_order(String money_order) {
		this.money_order = money_order;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getValid_order() {
		return valid_order;
	}
	public void setValid_order(String valid_order) {
		this.valid_order = valid_order;
	}
	public String getRisk_item() {
		return risk_item;
	}
	public void setRisk_item(String risk_item) {
		this.risk_item = risk_item;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public String getBind_mob() {
		return bind_mob;
	}
	public void setBind_mob(String bind_mob) {
		this.bind_mob = bind_mob;
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
	public String getNo_agree() {
		return no_agree;
	}
	public void setNo_agree(String no_agree) {
		this.no_agree = no_agree;
	}

	@Override
	public String toString() {
		return "AuthFuninRequestVo [platform=" + platform + ", user_id="
				+ user_id + ", oid_partner=" + oid_partner + ", sign_type="
				+ sign_type + ", sign=" + sign + ", busi_partner="
				+ busi_partner + ", api_version=" + api_version + ", no_order="
				+ no_order + ", dt_order=" + dt_order + ", name_goods="
				+ name_goods + ", info_order=" + info_order + ", money_order="
				+ money_order + ", notify_url=" + notify_url + ", valid_order="
				+ valid_order + ", risk_item=" + risk_item + ", pay_type="
				+ pay_type + ", card_no=" + card_no + ", bank_code="
				+ bank_code + ", acct_name=" + acct_name + ", bind_mob="
				+ bind_mob + ", id_type=" + id_type + ", id_no=" + id_no
				+ ", no_agree=" + no_agree + ", sms_param=" + sms_param + "]";
	}

	
	
	
	
}
