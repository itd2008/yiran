package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>验证个人会员登陆密码信息</p>
 */
public class PersonalLoginPwdRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = 6018118639632452509L;

    /**
     * 会员标识
     */
    private String            memberIdentity;
    /**
     * 会员标识平台类型
     */
    private String            platformType;

    /**
     * 登录密码: SHA-256(SHA-256(密码明文） + salt)
     */
    private String            password;

    /**
     * 盐值
     */
    private String            salt;
    
    /** 错误时是否累计错误次数  默认统计*/
    private boolean			  countWhenWrong = true;

    public String getMemberIdentity() {
        return memberIdentity;
    }

    public void setMemberIdentity(String memberIdentity) {
        this.memberIdentity = memberIdentity;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isCountWhenWrong() {
		return countWhenWrong;
	}

	public void setCountWhenWrong(boolean countWhenWrong) {
		this.countWhenWrong = countWhenWrong;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
