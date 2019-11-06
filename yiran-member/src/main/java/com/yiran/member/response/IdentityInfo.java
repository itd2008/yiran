/**
 * 
 */
package com.yiran.member.response;

import java.io.Serializable;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>标志信息</p>
 */
public class IdentityInfo implements Serializable {
    private static final long serialVersionUID = 4004227794411907013L;

    /**
     * 会员标志
     */
    private String            identity;

    /**
     * 标志平台类型:
     *  0  微汇平台
     *  1  新浪
     */
    private String            platformType;

    /**
     * 标识类型:
     * 0  外部平台用户id
     * 1  登录名：邮箱
     * 2  登录名：手机号
     * 3  登录名： 普通文字
     * 5  商户号
     */
    private int               identityType;

    /**
     * 扩展信息
     */
    private String            extention;
    
    private boolean isUnionAccount;
	private int unionAccountStatus;
    

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public int getIdentityType() {
        return identityType;
    }

    public void setIdentityType(int identityType) {
        this.identityType = identityType;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
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
