/**
 * 
 */
package com.yiran.member.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>会员标识请求</p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MemberIdentityRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -763347322142772673L;

    /** 会员编号*/
    private String            memberId;

    /** 会员标识*/
    private String            memberIdentity;

    /** 会员所属平台类型（0默认：微汇平台， 1：新浪微博）*/
    private String            platformType;

    /**
     * 标识类型:
     * 0  外部平台用户id(新浪uid)
     * 1  登录名：邮箱
     * 2  登录名：手机号
     * 3  登录名： 普通文字
     * 5  商户号
     */
    private Integer           identityType;

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the memberIdentity
     */
    public String getMemberIdentity() {
        return memberIdentity;
    }

    /**
     * @param memberIdentity the memberIdentity to set
     */
    public void setMemberIdentity(String memberIdentity) {
        this.memberIdentity = memberIdentity;
    }

    /**
     * @return the platformType
     */
    public String getPlatformType() {
        return platformType;
    }

    /**
     * @param platformType the platformType to set
     */
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    /**
     * @return the identityType
     */
    public Integer getIdentityType() {
        return identityType;
    }

    /**
     * @param identityType the identityType to set
     */
    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
