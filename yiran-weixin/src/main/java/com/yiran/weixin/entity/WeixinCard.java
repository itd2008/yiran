package com.yiran.weixin.entity;

import java.io.Serializable;

/**
 * 微信卡券
 * @author pandaa
 *
 */
public class WeixinCard implements Serializable{
	/**
	 * 门店ID。shopID用于筛选出拉起带有指定location_list(shopID)的卡券列表，非必填。
	 */
	private String shopId;
	/**
	 * 卡券类型，用于拉起指定卡券类型的卡券列表。当cardType为空时，默认拉起所有卡券的列表，非必填。
	 */
	private String cardType;
	/**
	 * 卡券ID，用于拉起指定cardId的卡券列表，当cardId为空时，默认拉起所有卡券的列表，非必填。
	 */
	private String cardId;
	/**
	 * 时间戳。必填
	 */
	private String timestamp;
	/**
	 * 随机字符串。必填
	 */
	private String nonceStr;
	/**
	 * 签名方式，目前仅支持SHA1 必填
	 */
	private String signType;
	/**
	 * 签名 必填
	 */
	private String cardSign;
	@Override
	public String toString() {
		return "WeixinCard [shopId=" + shopId + ", cardType=" + cardType + ", cardId=" + cardId + ", timestamp="
				+ timestamp + ", nonceStr=" + nonceStr + ", signType=" + signType + ", cardSign=" + cardSign + "]";
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getCardSign() {
		return cardSign;
	}
	public void setCardSign(String cardSign) {
		this.cardSign = cardSign;
	}
	
	
}
