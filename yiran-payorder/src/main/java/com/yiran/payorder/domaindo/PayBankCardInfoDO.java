package com.yiran.payorder.domaindo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 银行卡表 pay_bank_card_info
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayBankCardInfoDO extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer bankCardId;
	/** 卡号 */
	private String cardNo;
	/** 卡类型 */
	private String cardType;
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
	private String certType;
	/** 有效期 */
	private String validDate;
	/** 信用卡安全码 */
	private String cvv2;
	/** 状态：A(有效)，I(无效) */
	private String status;
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
	public void setCardType(String cardType) 
	{
		this.cardType = cardType;
	}

	public String getCardType() 
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
	public void setCertType(String certType) 
	{
		this.certType = certType;
	}

	public String getCertType() 
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
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
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
            .append("cardType", getCardType())
            .append("targetInstCode", getTargetInstCode())
            .append("bankBranch", getBankBranch())
            .append("cardHolder", getCardHolder())
            .append("mobileNo", getMobileNo())
            .append("certNo", getCertNo())
            .append("certType", getCertType())
            .append("validDate", getValidDate())
            .append("cvv2", getCvv2())
            .append("status", getStatus())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
