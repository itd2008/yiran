package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 账户配置表 member_td_account_config
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTdAccountConfig extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 账户类型 */
	private Integer accountType;
	/** 一个会员该类型账户最大数目(负数代表无限制) */
	private Integer maxAccountCount;
	/** 状态(0不可用;1可用) */
	private Integer status;
	/** 支持的会员类型，以,分隔 */
	private String supportMemberTypes;
	/** 备注 */
	private String memo;
	/** 是否共用基本户的支付密码 */
	private Integer shareBasePwd;
	/** 账户属性：1:对私、2:对公 */
	private Integer accountAttr;
	/** 账户子类型,对应dpm中账户类型:1:基本户    2:一般户    3:专用户    4:临时户    5:保证金户 */
	private Integer accountSubType;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setAccountType(Integer accountType) 
	{
		this.accountType = accountType;
	}

	public Integer getAccountType() 
	{
		return accountType;
	}
	public void setMaxAccountCount(Integer maxAccountCount) 
	{
		this.maxAccountCount = maxAccountCount;
	}

	public Integer getMaxAccountCount() 
	{
		return maxAccountCount;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setSupportMemberTypes(String supportMemberTypes) 
	{
		this.supportMemberTypes = supportMemberTypes;
	}

	public String getSupportMemberTypes() 
	{
		return supportMemberTypes;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setShareBasePwd(Integer shareBasePwd) 
	{
		this.shareBasePwd = shareBasePwd;
	}

	public Integer getShareBasePwd() 
	{
		return shareBasePwd;
	}
	public void setAccountAttr(Integer accountAttr) 
	{
		this.accountAttr = accountAttr;
	}

	public Integer getAccountAttr() 
	{
		return accountAttr;
	}
	public void setAccountSubType(Integer accountSubType) 
	{
		this.accountSubType = accountSubType;
	}

	public Integer getAccountSubType() 
	{
		return accountSubType;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("accountType", getAccountType())
            .append("maxAccountCount", getMaxAccountCount())
            .append("status", getStatus())
            .append("supportMemberTypes", getSupportMemberTypes())
            .append("memo", getMemo())
            .append("shareBasePwd", getShareBasePwd())
            .append("accountAttr", getAccountAttr())
            .append("accountSubType", getAccountSubType())
            .toString();
    }
}
