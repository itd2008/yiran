package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 会员标识表 member_tm_member_identity
 * 
 * @author yiran
 * @date 2019-03-31
 */
public class MemberTmMemberIdentity extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 会员编号 */
	private String memberId;
	/** 会员标识 */
	private String identity;
	/** 状态1：有效；0：无效 */
	private Integer status;
	/** 收款标识1：可以作为收款标识；0：不可以作为收款标识 */
	private Integer isRecvAddr;
	/** 标识类型（0：uid 1：邮箱 2:手机） */
	private Integer identityType;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	/** 备注信息 */
	private String memo;
	/** 平台类型：1：齐家uid 2：手机号 3：登录名 */
	private Integer pid;
	/** 是否是统一账户 */
	private Integer isUnionAccount;
	/** 统一账户状态 1.未确认登录名 2.未确认登录密码 3.未确认支付密码 4.确认完毕 */
	private Integer unionAccountStatus;

	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setIdentity(String identity) 
	{
		this.identity = identity;
	}

	public String getIdentity() 
	{
		return identity;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setIsRecvAddr(Integer isRecvAddr) 
	{
		this.isRecvAddr = isRecvAddr;
	}

	public Integer getIsRecvAddr() 
	{
		return isRecvAddr;
	}
	public void setIdentityType(Integer identityType) 
	{
		this.identityType = identityType;
	}

	public Integer getIdentityType() 
	{
		return identityType;
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
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}
	public void setIsUnionAccount(Integer isUnionAccount) 
	{
		this.isUnionAccount = isUnionAccount;
	}

	public Integer getIsUnionAccount() 
	{
		return isUnionAccount;
	}
	public void setUnionAccountStatus(Integer unionAccountStatus) 
	{
		this.unionAccountStatus = unionAccountStatus;
	}

	public Integer getUnionAccountStatus() 
	{
		return unionAccountStatus;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("memberId", getMemberId())
            .append("identity", getIdentity())
            .append("status", getStatus())
            .append("isRecvAddr", getIsRecvAddr())
            .append("identityType", getIdentityType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("memo", getMemo())
            .append("pid", getPid())
            .append("isUnionAccount", getIsUnionAccount())
            .append("unionAccountStatus", getUnionAccountStatus())
            .toString();
    }
}
