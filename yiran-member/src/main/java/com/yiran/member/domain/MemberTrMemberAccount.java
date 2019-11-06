package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 会员账户表 member_tr_member_account
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTrMemberAccount extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 会员编号 */
	private String memberId;
	/** 开户接口请求号(由seq_remote_request_no生成) */
	private String originalRequestNo;
	/** 账户类型 */
	private Integer accountType;
	/** 账户编号 */
	private String accountId;
	/** 账户属性(1:对私 2:对公) */
	private Integer accountAttribute;
	/** 状态状态(0:未激活 1:正常 2:锁定 3:止出 4:止入) */
	private Integer status;
	/** 账户别名 */
	private String alias;
	/** 建立时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 建立人 */
	private String createUser;
	/** 更新人 */
	private String updateUser;
	/** 备注 */
	private String memo;
	/** 类别: DPM,CREDIT,默认为 DPM */
	private String category;
	/** 类型id */
	private String typeId;

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
	public void setOriginalRequestNo(String originalRequestNo) 
	{
		this.originalRequestNo = originalRequestNo;
	}

	public String getOriginalRequestNo() 
	{
		return originalRequestNo;
	}
	public void setAccountType(Integer accountType) 
	{
		this.accountType = accountType;
	}

	public Integer getAccountType() 
	{
		return accountType;
	}
	public void setAccountId(String accountId) 
	{
		this.accountId = accountId;
	}

	public String getAccountId() 
	{
		return accountId;
	}
	public void setAccountAttribute(Integer accountAttribute) 
	{
		this.accountAttribute = accountAttribute;
	}

	public Integer getAccountAttribute() 
	{
		return accountAttribute;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}

	public String getAlias() 
	{
		return alias;
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
	public void setCategory(String category) 
	{
		this.category = category;
	}

	public String getCategory() 
	{
		return category;
	}
	public void setTypeId(String typeId) 
	{
		this.typeId = typeId;
	}

	public String getTypeId() 
	{
		return typeId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("originalRequestNo", getOriginalRequestNo())
            .append("accountType", getAccountType())
            .append("accountId", getAccountId())
            .append("accountAttribute", getAccountAttribute())
            .append("status", getStatus())
            .append("alias", getAlias())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("category", getCategory())
            .append("typeId", getTypeId())
            .toString();
    }
}
