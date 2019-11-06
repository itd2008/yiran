package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 会员支付密码表 member_tr_password
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTrPassword extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 操作员编号 */
	private String operatorId;
	/** 账户ID */
	private String accountId;
	/** 密码 */
	private String password;
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
	/** 密码状态 0.初始 1.正常 */
	private Integer status;
	/** 手机端密码 */
	private String mpassword;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOperatorId(String operatorId) 
	{
		this.operatorId = operatorId;
	}

	public String getOperatorId() 
	{
		return operatorId;
	}
	public void setAccountId(String accountId) 
	{
		this.accountId = accountId;
	}

	public String getAccountId() 
	{
		return accountId;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
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
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setMpassword(String mpassword) 
	{
		this.mpassword = mpassword;
	}

	public String getMpassword() 
	{
		return mpassword;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("operatorId", getOperatorId())
            .append("accountId", getAccountId())
            .append("password", getPassword())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("status", getStatus())
            .append("mpassword", getMpassword())
            .toString();
    }
}
