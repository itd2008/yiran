package com.yiran.payorder.response;

import com.yiran.payorder.domain.ReturnInfo;

public class BankCardResponse implements BaseResponse{
	private static final long serialVersionUID = -1626179508105117422L;
	protected String channelSignNo;//签约协议号
	protected String memberId;//会员id
	protected ReturnInfo returnInfo;
	protected String bankCardId;
	/**手机号*/
	protected String mobileNo;
	/**证件号*/
	protected String certNo;
	/** 银行卡号 */
	protected String cardNo;
	/**持卡人姓名*/
	protected String cardHolder;
	
	public String getChannelSignNo() {
		return channelSignNo;
	}
	public void setChannelSignNo(String channelSignNo) {
		this.channelSignNo = channelSignNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public ReturnInfo getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	
	

}
