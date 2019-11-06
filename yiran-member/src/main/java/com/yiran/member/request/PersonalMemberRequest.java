package com.yiran.member.request;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <p>集成创建时个人会员请求信息</p>
 */
public class PersonalMemberRequest implements Serializable {
    private static final long serialVersionUID = 7595146087599120449L;

    /**会员名称*/
    private String            memberName;
    /**登录名*/
    private String            loginName;
    /**登录密码*/
    private String            loginPassword;
    /**登录名类型：1(邮箱), 2(手机号), 3(普通文字帐号）   */
    private Integer           loginNameType;
    /**登录名平台类型 */
    private String            loginNamePlatformType;
    /**真实姓名*/
    private String            realName;
    /**性别（0:未知；1：男；2：女）*/
    private Long              gender;
    /**职位（1：默认）*/
    private Long              position;
    /**职业（1：默认）*/
    private Long              career;
    /**生日*/
    private String            birthDay;

    /**
     * 扩展信息
     */
    private String            extention;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Integer getLoginNameType() {
        return loginNameType;
    }

    public void setLoginNameType(Integer loginNameType) {
        this.loginNameType = loginNameType;
    }

    public String getLoginNamePlatformType() {
        return loginNamePlatformType;
    }

    public void setLoginNamePlatformType(String loginNamePlatformType) {
        this.loginNamePlatformType = loginNamePlatformType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getCareer() {
        return career;
    }

    public void setCareer(Long career) {
        this.career = career;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
