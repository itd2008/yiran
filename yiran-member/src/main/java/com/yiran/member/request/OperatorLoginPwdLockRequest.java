package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>重置操作员登录密码锁请求参数</p>
 */
public class OperatorLoginPwdLockRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -8704603824243673000L;

    /**
     * 会员标识
     */
    private String            memberIdentity;
    /**
     * 会员标识平台类型
     */
    private String            platformType;
    /**
     * 登录名
     */
    private String            loginName;

    /**登录名平台类型 */
    private String            loginNamePlatformType;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginNamePlatformType() {
        return loginNamePlatformType;
    }

    public void setLoginNamePlatformType(String loginNamePlatformType) {
        this.loginNamePlatformType = loginNamePlatformType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
