package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>重置个人会员登录密码锁请求参数</p>
 */
public class PersonalLoginPwdLockRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = 129916717581046571L;

    /**
     * 会员标识
     */
    private String          memberIdentity;
    /**
     * 平台类型（0默认：微汇平台， 1：新浪微博）
     */
    private String          platformType;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
