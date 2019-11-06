package com.yiran.weixin.entity;

import java.io.Serializable;

/**
 * 卡券扩展字段cardExt
 * @author pandaa
 *
 */
public class WeixinCardExt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5928889297311315134L;
	/**
	 * 指定的卡券code码，只能被领一次。自定义code模式的卡券必须填写，非自定义code和预存code模式的卡券不必填写。详情见： 是否自定义code码
	 */
	private String code;
	/**
	 * 指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，bind_openid字段为false不必填写。
	 */
	private String openid;
	/**
	 * 时间戳，商户生成从1970年1月1日00:00:00至今的秒数,即当前的时间,且最终需要转换为字符串形式;由商户生成后传入,不同添加请求的时间戳须动态生成，若重复将会导致领取失败！。
	 */
	private String timestamp;
	/**
	 * 随机字符串，由开发者设置传入， 加强安全性（若不填写可能被重放请求） 。随机字符串，不长于32位。推荐使用大小写字母和数字，不同添加请求的nonce须动态生成，若重复将会导致领取失败。
	 */
	private String nonce_str;
	/**
	 * 卡券在第三方系统的实际领取时间，为东八区时间戳（UTC+8,精确到秒）。当卡券的有效期类型为 DAT E_TYPE_FIX_TERM时专用，标识卡券的实际生效时间，用于解决商户系统内起始时间和领取时间不同步的问题。
	 */
	private String fixed_begintimestamp;
	/**
	 * 领取渠道参数，用于标识本次领取的渠道值。
	 */
	private String outer_str;
	/**
	 * 签名，商户将接口列表中的参数按照指定方式进行签名,签名方式使用SHA1,具体签名方案参见下文;由商户按照规范签名后传入。
	 */
	private String signature;
	
	private String api_ticket;
	
	private String card_id;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getFixed_begintimestamp() {
		return fixed_begintimestamp;
	}
	public void setFixed_begintimestamp(String fixed_begintimestamp) {
		this.fixed_begintimestamp = fixed_begintimestamp;
	}
	public String getOuter_str() {
		return outer_str;
	}
	public void setOuter_str(String outer_str) {
		this.outer_str = outer_str;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getApi_ticket() {
		return api_ticket;
	}
	public void setApi_ticket(String api_ticket) {
		this.api_ticket = api_ticket;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	@Override
	public String toString() {
		return "WeixinCardExt [code=" + code + ", openid=" + openid + ", timestamp=" + timestamp + ", nonce_str="
				+ nonce_str + ", fixed_begintimestamp=" + fixed_begintimestamp + ", outer_str=" + outer_str
				+ ", signature=" + signature + ", api_ticket=" + api_ticket + ", card_id=" + card_id + "]";
	}
	
	
}
