package com.yiran.member.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>登陆名领域对象</p>
 */
public class LoginName implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6702993427529135809L;
    private Long              loginId;
    private String            loginName;                               //登陆名
    private Integer           platFormType;                            //平台类型
    private Integer           loginNameType;                           //登录名类型：对应会员标识类型
//    private Integer           loginType;                               //登陆账户类型（0统一账户 1快捷通账户 2商户会员账户）
    
    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getPlatFormType() {
        return platFormType;
    }

    public void setPlatFormType(Integer platFormType) {
        this.platFormType = platFormType;
    }

    public Integer getLoginNameType() {
        return loginNameType;
    }

    public void setLoginNameType(Integer loginNameType) {
        this.loginNameType = loginNameType;
    }
    
//    public Integer getLoginType() {
//        return loginType;
//    }
//
//    public void setLoginType(Integer loginType) {
//        this.loginType = loginType;
//    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
