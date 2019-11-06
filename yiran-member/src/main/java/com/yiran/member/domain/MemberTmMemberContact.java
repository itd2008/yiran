package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 会员账户表 member_tm_member_contact
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTmMemberContact extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer contactId;
	/** 所属会员ID */
	private String memberId;
	/** 联系人类型（0系统账户，1银行卡账户） */
	private Integer contactType;
	/** 关联联系人名称-用户可定义默认账户名或者开户名 */
	private String name;
	/** 名称的拼音用于页面检索 */
	private String pinyin;
	/** 关联联系人会员ID */
	private String contactMemberId;
	/** 关联联系人手机号 */
	private String mobile;
	/** 开户银行 */
	private String bankName;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 开户银行支行名称 */
	private String bankBranch;
	/** 开户银行分支行号 */
	private String bankBranchNo;
	/** 银行账户开户名 */
	private String bankAccountName;
	/** 银行账户账号 */
	private String bankAccountNo;
	/** 账户摘要****卡号尾四位 */
	private String accountNoSummary;
	/** 卡类型(1借记卡 0其它)目前只支持1 */
	private Integer cardType;
	/** 卡属性(0对公 1对私) */
	private Integer cardAttribute;
	/** 联系人状态(0正常 1注销)用于逻辑删除 */
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
	/** 会员联系人标识 */
	private String contactIdentity;
	/** 银行编号 */
	private String bankCode;

	public void setContactId(Integer contactId) 
	{
		this.contactId = contactId;
	}

	public Integer getContactId() 
	{
		return contactId;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setContactType(Integer contactType) 
	{
		this.contactType = contactType;
	}

	public Integer getContactType() 
	{
		return contactType;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setPinyin(String pinyin) 
	{
		this.pinyin = pinyin;
	}

	public String getPinyin() 
	{
		return pinyin;
	}
	public void setContactMemberId(String contactMemberId) 
	{
		this.contactMemberId = contactMemberId;
	}

	public String getContactMemberId() 
	{
		return contactMemberId;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getMobile() 
	{
		return mobile;
	}
	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}

	public String getBankName() 
	{
		return bankName;
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
	public void setBankBranch(String bankBranch) 
	{
		this.bankBranch = bankBranch;
	}

	public String getBankBranch() 
	{
		return bankBranch;
	}
	public void setBankBranchNo(String bankBranchNo) 
	{
		this.bankBranchNo = bankBranchNo;
	}

	public String getBankBranchNo() 
	{
		return bankBranchNo;
	}
	public void setBankAccountName(String bankAccountName) 
	{
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountName() 
	{
		return bankAccountName;
	}
	public void setBankAccountNo(String bankAccountNo) 
	{
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountNo() 
	{
		return bankAccountNo;
	}
	public void setAccountNoSummary(String accountNoSummary) 
	{
		this.accountNoSummary = accountNoSummary;
	}

	public String getAccountNoSummary() 
	{
		return accountNoSummary;
	}
	public void setCardType(Integer cardType) 
	{
		this.cardType = cardType;
	}

	public Integer getCardType() 
	{
		return cardType;
	}
	public void setCardAttribute(Integer cardAttribute) 
	{
		this.cardAttribute = cardAttribute;
	}

	public Integer getCardAttribute() 
	{
		return cardAttribute;
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
	public void setContactIdentity(String contactIdentity) 
	{
		this.contactIdentity = contactIdentity;
	}

	public String getContactIdentity() 
	{
		return contactIdentity;
	}
	public void setBankCode(String bankCode) 
	{
		this.bankCode = bankCode;
	}

	public String getBankCode() 
	{
		return bankCode;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contactId", getContactId())
            .append("memberId", getMemberId())
            .append("contactType", getContactType())
            .append("name", getName())
            .append("pinyin", getPinyin())
            .append("contactMemberId", getContactMemberId())
            .append("mobile", getMobile())
            .append("bankName", getBankName())
            .append("province", getProvince())
            .append("city", getCity())
            .append("bankBranch", getBankBranch())
            .append("bankBranchNo", getBankBranchNo())
            .append("bankAccountName", getBankAccountName())
            .append("bankAccountNo", getBankAccountNo())
            .append("accountNoSummary", getAccountNoSummary())
            .append("cardType", getCardType())
            .append("cardAttribute", getCardAttribute())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("contactIdentity", getContactIdentity())
            .append("bankCode", getBankCode())
            .toString();
    }
}
