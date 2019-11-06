package com.yiran.api.vo;

import java.io.Serializable;
/**
 * 支付查询请求
 * @author Administrator
 *
 */
public class PayQueryRequestVo implements Serializable{
	private static final long serialVersionUID = -3503686595324699348L;
	private String oid_partner;			//商户编号
	private String sign_type;			//签名方式
	private String sign;				//签名
	
	private String no_order;			//商户唯一订单号
	private String dt_order;			//商户订单时间
	private String oid_paybill;			//连连支付支付单号
	private String query_version;		//查询版本号
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
	public String getOid_paybill() {
		return oid_paybill;
	}
	public void setOid_paybill(String oid_paybill) {
		this.oid_paybill = oid_paybill;
	}
	public String getQuery_version() {
		return query_version;
	}
	public void setQuery_version(String query_version) {
		this.query_version = query_version;
	}
	@Override
	public String toString() {
		return "PayQueryRequestVo [oid_partner=" + oid_partner + ", sign_type="
				+ sign_type + ", sign=" + sign + ", no_order=" + no_order
				+ ", dt_order=" + dt_order + ", oid_paybill=" + oid_paybill
				+ ", query_version=" + query_version + "]";
	}
	
}
