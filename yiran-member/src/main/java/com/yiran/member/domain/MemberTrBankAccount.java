package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 个人银行卡表 member_tr_bank_account
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTrBankAccount extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 会员ID */
	private String memberId;
	/** 银行编号 */
	private String bankId;
	/** 银行名称 */
	private String bankName;
	/** 支行名称 */
	private String bankBranch;
	/** 银行卡号 */
	private String bankAccountNo;
	/** 户名 */
	private String bankAccountName;
	/** 卡属性(0对公 1对私) */
	private Integer cardAttribute;
	/** 卡类型(1借记卡 2信用卡,3存折,4其它) */
	private Integer cardType;
	/** 协议号 */
	private String agreementNo;
	/** 别名 */
	private String alias;
	/** 银行卡皮肤 */
	private String cardSkin;
	/** 账户摘要 */
	private String accountNoSummary;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 认证状态(0未认证 1已认证) */
	private Integer isVerified;
	/** 是否签约（Y是 N否） */
	private String isSigning;
	/** 状态(0失效  1正常 2锁定) */
	private Integer status;
	/** 建立时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 建立人 */
	private String createUser;
	/** 更新人 */
	private String updateUser;
	/** 备注信息 */
	private String memo;
	/** 扩展信息 */
	private String extendable;
	/** 协议号 */
	private String signNo;
	/** 内部协议号 */
	private String signId;
	/** 证件类型 */
	private String certType;
	/** 证件号 */
	private String certNo;
	/** cvv2 */
	private String cvNo;
	/** 卡片有效期 */
	private String cardValidDate;
	/** 协议有效期 */
	private Date agreementValidDate;
	/** 手机号 */
	private String mobileNo;
	/** 支付属性 */
	private String payAttribute;
	/** 是否填充证件号 */
	private String isFillCertNo;
	/** 渠道编号 */
	private String channelCode;
	/** 激活时间 */
	private Date activateDate;
	/** 联行号 */
	private String branchNo;
	/** 理财标识，Y是理财卡，否则不是 */
	private String financialCard;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setBankId(String bankId) 
	{
		this.bankId = bankId;
	}

	public String getBankId() 
	{
		return bankId;
	}
	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}

	public String getBankName() 
	{
		return bankName;
	}
	public void setBankBranch(String bankBranch) 
	{
		this.bankBranch = bankBranch;
	}

	public String getBankBranch() 
	{
		return bankBranch;
	}
	public void setBankAccountNo(String bankAccountNo) 
	{
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountNo() 
	{
		return bankAccountNo;
	}
	public void setBankAccountName(String bankAccountName) 
	{
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountName() 
	{
		return bankAccountName;
	}
	public void setCardAttribute(Integer cardAttribute) 
	{
		this.cardAttribute = cardAttribute;
	}

	public Integer getCardAttribute() 
	{
		return cardAttribute;
	}
	public void setCardType(Integer cardType) 
	{
		this.cardType = cardType;
	}

	public Integer getCardType() 
	{
		return cardType;
	}
	public void setAgreementNo(String agreementNo) 
	{
		this.agreementNo = agreementNo;
	}

	public String getAgreementNo() 
	{
		return agreementNo;
	}
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}

	public String getAlias() 
	{
		return alias;
	}
	public void setCardSkin(String cardSkin) 
	{
		this.cardSkin = cardSkin;
	}

	public String getCardSkin() 
	{
		return cardSkin;
	}
	public void setAccountNoSummary(String accountNoSummary) 
	{
		this.accountNoSummary = accountNoSummary;
	}

	public String getAccountNoSummary() 
	{
		return accountNoSummary;
	}
	public void setProvince(String province) 
	{
		this.province = province;
	}

	public String getProvince() 
	{
		return province;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity() 
	{
		return city;
	}
	public void setIsVerified(Integer isVerified) 
	{
		this.isVerified = isVerified;
	}

	public Integer getIsVerified() 
	{
		return isVerified;
	}
	public void setIsSigning(String isSigning) 
	{
		this.isSigning = isSigning;
	}

	public String getIsSigning() 
	{
		return isSigning;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setCreateUser(String createUser) 
	{
		this.createUser = createUser;
	}

	public String getCreateUser() 
	{
		return createUser;
	}
	public void setUpdateUser(String updateUser) 
	{
		this.updateUser = updateUser;
	}

	public String getUpdateUser() 
	{
		return updateUser;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setExtendable(String extendable) 
	{
		this.extendable = extendable;
	}

	public String getExtendable() 
	{
		return extendable;
	}
	public void setSignNo(String signNo) 
	{
		this.signNo = signNo;
	}

	public String getSignNo() 
	{
		return signNo;
	}
	public void setSignId(String signId) 
	{
		this.signId = signId;
	}

	public String getSignId() 
	{
		return signId;
	}
	public void setCertType(String certType) 
	{
		this.certType = certType;
	}

	public String getCertType() 
	{
		return certType;
	}
	public void setCertNo(String certNo) 
	{
		this.certNo = certNo;
	}

	public String getCertNo() 
	{
		return certNo;
	}
	public void setCvNo(String cvNo) 
	{
		this.cvNo = cvNo;
	}

	public String getCvNo() 
	{
		return cvNo;
	}
	public void setCardValidDate(String cardValidDate) 
	{
		this.cardValidDate = cardValidDate;
	}

	public String getCardValidDate() 
	{
		return cardValidDate;
	}
	public void setAgreementValidDate(Date agreementValidDate) 
	{
		this.agreementValidDate = agreementValidDate;
	}

	public Date getAgreementValidDate() 
	{
		return agreementValidDate;
	}
	public void setMobileNo(String mobileNo) 
	{
		this.mobileNo = mobileNo;
	}

	public String getMobileNo() 
	{
		return mobileNo;
	}
	public void setPayAttribute(String payAttribute) 
	{
		this.payAttribute = payAttribute;
	}

	public String getPayAttribute() 
	{
		return payAttribute;
	}
	public void setIsFillCertNo(String isFillCertNo) 
	{
		this.isFillCertNo = isFillCertNo;
	}

	public String getIsFillCertNo() 
	{
		return isFillCertNo;
	}
	public void setChannelCode(String channelCode) 
	{
		this.channelCode = channelCode;
	}

	public String getChannelCode() 
	{
		return channelCode;
	}
	public void setActivateDate(Date activateDate) 
	{
		this.activateDate = activateDate;
	}

	public Date getActivateDate() 
	{
		return activateDate;
	}
	public void setBranchNo(String branchNo) 
	{
		this.branchNo = branchNo;
	}

	public String getBranchNo() 
	{
		return branchNo;
	}
	public void setFinancialCard(String financialCard) 
	{
		this.financialCard = financialCard;
	}

	public String getFinancialCard() 
	{
		return financialCard;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("bankId", getBankId())
            .append("bankName", getBankName())
            .append("bankBranch", getBankBranch())
            .append("bankAccountNo", getBankAccountNo())
            .append("bankAccountName", getBankAccountName())
            .append("cardAttribute", getCardAttribute())
            .append("cardType", getCardType())
            .append("agreementNo", getAgreementNo())
            .append("alias", getAlias())
            .append("cardSkin", getCardSkin())
            .append("accountNoSummary", getAccountNoSummary())
            .append("province", getProvince())
            .append("city", getCity())
            .append("isVerified", getIsVerified())
            .append("isSigning", getIsSigning())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("extendable", getExtendable())
            .append("signNo", getSignNo())
            .append("signId", getSignId())
            .append("certType", getCertType())
            .append("certNo", getCertNo())
            .append("cvNo", getCvNo())
            .append("cardValidDate", getCardValidDate())
            .append("agreementValidDate", getAgreementValidDate())
            .append("mobileNo", getMobileNo())
            .append("payAttribute", getPayAttribute())
            .append("isFillCertNo", getIsFillCertNo())
            .append("channelCode", getChannelCode())
            .append("activateDate", getActivateDate())
            .append("branchNo", getBranchNo())
            .append("financialCard", getFinancialCard())
            .toString();
    }
}
