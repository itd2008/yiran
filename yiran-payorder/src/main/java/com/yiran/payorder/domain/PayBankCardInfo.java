package com.yiran.payorder.domain;

import com.yiran.common.base.BaseEntity;
import com.yiran.paychannel.enums.CardType;
import com.yiran.paychannel.enums.CertType;
import com.yiran.payorder.enums.BankCardStatus;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 银行卡表 pay_bank_card_info
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayBankCardInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer bankCardId;
	/** 卡号 */
	private String cardNo;
	/** 卡类型 */
	private CardType cardType;
	/** 银行代码 */
	private String targetInstCode;
	/** 开户行 */
	private String bankBranch;
	/** 持卡人姓名 */
	private String cardHolder;
	/** 预留手机号码 */
	private String mobileNo;
	/** 证件号 */
	private String certNo;
	/** 证件类型 */
	private CertType certType;
	/** 有效期 */
	private String validDate;
	/** 信用卡安全码 */
	private String cvv2;
	/** 状态：A(有效)，I(无效) */
	private BankCardStatus status;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;

	public void setBankCardId(Integer bankCardId) 
	{
		this.bankCardId = bankCardId;
	}

	public Integer getBankCardId() 
	{
		return bankCardId;
	}
	public void setCardNo(String cardNo) 
	{
		this.cardNo = cardNo;
	}

	public String getCardNo() 
	{
		return cardNo;
	}
	public void setCardType(CardType cardType) 
	{
		this.cardType = cardType;
	}

	public CardType getCardType() 
	{
		return cardType;
	}
	public void setTargetInstCode(String targetInstCode) 
	{
		this.targetInstCode = targetInstCode;
	}

	public String getTargetInstCode() 
	{
		return targetInstCode;
	}
	public void setBankBranch(String bankBranch) 
	{
		this.bankBranch = bankBranch;
	}

	public String getBankBranch() 
	{
		return bankBranch;
	}
	public void setCardHolder(String cardHolder) 
	{
		this.cardHolder = cardHolder;
	}

	public String getCardHolder() 
	{
		return cardHolder;
	}
	public void setMobileNo(String mobileNo) 
	{
		this.mobileNo = mobileNo;
	}

	public String getMobileNo() 
	{
		return mobileNo;
	}
	public void setCertNo(String certNo) 
	{
		this.certNo = certNo;
	}

	public String getCertNo() 
	{
		return certNo;
	}
	public void setCertType(CertType certType) 
	{
		this.certType = certType;
	}

	public CertType getCertType() 
	{
		return certType;
	}
	public void setValidDate(String validDate) 
	{
		this.validDate = validDate;
	}

	public String getValidDate() 
	{
		return validDate;
	}
	public void setCvv2(String cvv2) 
	{
		this.cvv2 = cvv2;
	}

	public String getCvv2() 
	{
		return cvv2;
	}
	public void setStatus(BankCardStatus status) 
	{
		this.status = status;
	}

	public BankCardStatus getStatus() 
	{
		return status;
	}
	public void setGmtCreate(Date gmtCreate) 
	{
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() 
	{
		return gmtCreate;
	}
	public void setGmtModified(Date gmtModified) 
	{
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() 
	{
		return gmtModified;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bankCardId", getBankCardId())
            .append("cardNo", getCardNo())
            .append("cardType", getCardType().getCode())
            .append("targetInstCode", getTargetInstCode())
            .append("bankBranch", getBankBranch())
            .append("cardHolder", getCardHolder())
            .append("mobileNo", getMobileNo())
            .append("certNo", getCertNo())
            .append("certType", getCertType().getCode())
            .append("validDate", getValidDate())
            .append("cvv2", getCvv2())
            .append("status", getStatus().getCode())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
