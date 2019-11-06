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
 * <p>
 * 会员帐号信息查询请求
 * </p>
 * 
 */
public class MemberLoginNameQueryRequest extends Request {
    /**
     * 
     */
    private static final long serialVersionUID = -8113167457528036305L;

    /** 手机号 */
    private String            phone;

    /** 邮箱 */
    private String            mail;

    /** 身份证 */
    private String            IDCard;

    /** 会员类型，多个会员类型以,分隔， null查询所有会员类型 */
    private String            memberType;

    /** 是否身份证匹配到后就返回，默认为false */
    private boolean           IDCardMatchReturn;

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * @return the iDCard
     */
    public String getIDCard() {
        return IDCard;
    }

    /**
     * @param iDCard the iDCard to set
     */
    public void setIDCard(String iDCard) {
        IDCard = iDCard;
    }

    /**
     * @return the iDCardMatchReturn
     */
    public boolean isIDCardMatchReturn() {
        return IDCardMatchReturn;
    }

    /**
     * @param iDCardMatchReturn the iDCardMatchReturn to set
     */
    public void setIDCardMatchReturn(boolean iDCardMatchReturn) {
        IDCardMatchReturn = iDCardMatchReturn;
    }

    /**
     * @return the memberType
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     * @param memberType the memberType to set
     */
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

}
