package com.yiran.payorder.request;

import java.io.Serializable;

public class BankCardRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2206449685082850123L;
	//加密后的银行卡号
	private String bankCard;
	//连连主商户号
	private String mainMerchantNo;
	//姓名
	private String cardName;
	//身份证号
	private String idNo;
	//渠道编号
	private String fundChannelCode;
	
	private String bankCardId;
	
	
	
	public String getFundChannelCode() {
		return fundChannelCode;
	}
	public void setFundChannelCode(String fundChannelCode) {
		this.fundChannelCode = fundChannelCode;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	public String getMainMerchantNo() {
		return mainMerchantNo;
	}
	public void setMainMerchantNo(String mainMerchantNo) {
		this.mainMerchantNo = mainMerchantNo;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	

}