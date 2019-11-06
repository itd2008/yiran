package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 操作员表 member_tm_operator
 * 
 * @author yiran
 * @date 2019-03-31
 */
public class MemberTmOperator extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 操作员编号(由seq_operator_id 生成,并以7开头) */
	private String operatorId;
	/** 会员ID */
	private String memberId;
	/** 昵称 */
	private String nickname;
	/** 操作员类型(1个人 2企业) */
	private Integer operatorType;
	/** 是否为默认操作员(0 否 1是) */
	private Integer isDefault;
	/** 状态(0未激活 1正常  3注销) */
	private Integer status;
	/** 激活时间 */
	private Date activeTime;
	/** 锁定状态(0未锁定 1已锁定) */
	private Integer lockStatus;
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
	/** 安全等级标记 0：默认无  1：数字证书 */
	private Integer securityFlag;
	/** 登录密码 */
	private String password;
	/**  */
	private String contact;
	/** 密码状态 0.初始 1.正常 */
	private Integer pwdStatus;
	/** 联系方式是否验证 0.初始 1.已验证 */
	private Integer contactVerify;
	
	private Integer flagEnum;
	
	/**
     * 支付密码列表
     */
    private List<PayPassWord>    payPwdList;

    /**
     * 登陆名列表
     */
    private List<LoginName>      loginNameList;
    public List<LoginName> getLoginNameList() {
        return loginNameList;
    }

    public void setLoginNameList(List<LoginName> loginNameList) {
        this.loginNameList = loginNameList;
    }


    public void addLoginName(LoginName loginName) {
        if (this.loginNameList == null) {
            this.loginNameList = new ArrayList<LoginName>();
        }
        this.loginNameList.add(loginName);
    }

    /**
     * 获得单个登录名
     * @return
     */
    public LoginName getSingleLoginName() {
        if (!(loginNameList == null || loginNameList.isEmpty())) {
            return loginNameList.get(0);
        }
        return null;
    }

    public List<PayPassWord> getPayPwdList() {
        return payPwdList;
    }

    public void setPayPwdList(List<PayPassWord> payPwdList) {
        this.payPwdList = payPwdList;
    }

    /**
     * 获得基本户支付密码
     * @return
     */
    public PayPassWord getBasePayPassword() {
        if (!(this.payPwdList == null || this.payPwdList.isEmpty())) {
            return this.payPwdList.get(0);
        }
        return null;
    }

    /**
     * 获得基本户的账户号
     * @return
     */
    public String getBaseAccountId(){
        if(null!=this.getBasePayPassword()){
            return this.getBasePayPassword().getAccountId();
        }
        return null;
    }
    
    public void addPayPwd(PayPassWord payPassWord) {
        if (this.payPwdList == null) {
            this.payPwdList = new ArrayList<PayPassWord>(1);
        }
        this.payPwdList.add(payPassWord);
    }
	public void setOperatorId(String operatorId) 
	{
		this.operatorId = operatorId;
	}

	public String getOperatorId() 
	{
		return operatorId;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setNickname(String nickname) 
	{
		this.nickname = nickname;
	}

	public String getNickname() 
	{
		return nickname;
	}
	public void setOperatorType(Integer operatorType) 
	{
		this.operatorType = operatorType;
	}

	public Integer getOperatorType() 
	{
		return operatorType;
	}
	public void setIsDefault(Integer isDefault) 
	{
		this.isDefault = isDefault;
	}

	public Integer getIsDefault() 
	{
		return isDefault;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setActiveTime(Date activeTime) 
	{
		this.activeTime = activeTime;
	}

	public Date getActiveTime() 
	{
		return activeTime;
	}
	public void setLockStatus(Integer lockStatus) 
	{
		this.lockStatus = lockStatus;
	}

	public Integer getLockStatus() 
	{
		return lockStatus;
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
	public void setSecurityFlag(Integer securityFlag) 
	{
		this.securityFlag = securityFlag;
	}

	public Integer getSecurityFlag() 
	{
		return securityFlag;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getContact() 
	{
		return contact;
	}
	public void setPwdStatus(Integer pwdStatus) 
	{
		this.pwdStatus = pwdStatus;
	}

	public Integer getPwdStatus() 
	{
		return pwdStatus;
	}
	public void setContactVerify(Integer contactVerify) 
	{
		this.contactVerify = contactVerify;
	}

	public Integer getContactVerify() 
	{
		return contactVerify;
	}

	
    public Integer getFlagEnum() {
		return flagEnum;
	}

	public void setFlagEnum(Integer flagEnum) {
		this.flagEnum = flagEnum;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("operatorId", getOperatorId())
            .append("memberId", getMemberId())
            .append("nickname", getNickname())
            .append("operatorType", getOperatorType())
            .append("isDefault", getIsDefault())
            .append("status", getStatus())
            .append("activeTime", getActiveTime())
            .append("lockStatus", getLockStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("securityFlag", getSecurityFlag())
            .append("password", getPassword())
            .append("contact", getContact())
            .append("pwdStatus", getPwdStatus())
            .append("contactVerify", getContactVerify())
            .append("flagEnum", getFlagEnum())
            .toString();
    }
}
