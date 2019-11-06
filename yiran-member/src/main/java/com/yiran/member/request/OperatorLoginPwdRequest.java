package com.yiran.member.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>验证操作员登陆密码请求信息</p>
 */
public class OperatorLoginPwdRequest extends Request {

    /**
     *
     */
    private static final long serialVersionUID = 7556804780863975016L;

    /**
     * 会员标识
     */
    private String            memberIdentity;
    /**
     * 会员标识平台类型
     */
    private String            platformType;

    /**
     * 登录密码
     */
    private String            password;

    /**
     * 登录名
     */
    private String            loginName;

    /**登录名平台类型 */
    private String            loginNamePlatformType;

    /**
     * 盐值
     */
    private String            salt;
    
    /** 密码错误是否统计次数  默认统计*/
    private boolean            countWhenWrong = true;

    public OperatorLoginPwdRequest() {

    }

    public OperatorLoginPwdRequest(String memberIdentity, String platformType, String password,
                                   String loginName, String loginNamePlatformType, String salt) {
        this.memberIdentity = memberIdentity;
        this.platformType = platformType;
        this.password = password;
        this.loginName = loginName;
        this.loginNamePlatformType = loginNamePlatformType;
        this.salt = salt;
    }

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLoginNamePlatformType() {
        return loginNamePlatformType;
    }

    public void setLoginNamePlatformType(String loginNamePlatformType) {
        this.loginNamePlatformType = loginNamePlatformType;
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
