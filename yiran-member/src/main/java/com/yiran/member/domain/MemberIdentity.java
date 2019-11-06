package com.yiran.member.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.enums.IdentityStatusEnum;


/**
 * 
 * 功能描述：会员登录标识
 */
public class MemberIdentity implements Serializable {

    private static final long  serialVersionUID = 1179796166075669588L;

    /**
     * 会员标识
     */
    private String             identity;

    /**
     * 标识状态
     */
    private IdentityStatusEnum status;

    /**
     * 标识类型:
     * 0  外部平台用户id
     * 1  登录名：邮箱
     * 2  登录名：手机号
     * 3  登录名： 普通文字
     * 5  商户号
     */
    private int                identityType;

    /**
     * 标志平台类型:
     *  0  微汇平台
     *  1  新浪
     */
    private int                platFormType;

    private Date               createTime;

    private Date               updateTime;

    private String             memo;
    
    private boolean isUnionAccount;
	private int unionAccountStatus;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public IdentityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(IdentityStatusEnum status) {
        this.status = status;
    }

    public int getIdentityType() {
        return identityType;
    }

    public void setIdentityType(int identityType) {
        this.identityType = identityType;
    }

    public int getPlatFormType() {
        return platFormType;
    }

    public void setPlatFormType(int platFormType) {
        this.platFormType = platFormType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    
    public boolean getIsUnionAccount() {
		return isUnionAccount;
	}

	public void setIsUnionAccount(boolean isUnionAccount) {
		this.isUnionAccount = isUnionAccount;
	}

	public int getUnionAccountStatus() {
		return unionAccountStatus;
	}

	public void setUnionAccountStatus(int unionAccountStatus) {
		this.unionAccountStatus = unionAccountStatus;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
