/**
 * 
 */
package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>激活个人会员请求</p>
 */
public class ActivatePersonalRequest extends Request {

    private static final long         serialVersionUID = -7698053606025411202L;

    /**
     * 支付密码
     */
    private String                    payPassword;

    /**
     * 个人会员信息
     */
    private PersonalMemberInfoRequest personalMemberInfo;

    /**
     * <li>无支付密码时是否激活储值账户:true 激活，false 不激活。默认false</li>
     * <li>如果设置了支付密码，储值账户自动激活。</li>
     */
    private boolean                   activateAccount;

    /**
     * @return the payPassword
     */
    public String getPayPassword() {
        return payPassword;
    }

    /**
     * @param payPassword the payPassword to set
     */
    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    /**
     * @return the personalMemberInfo
     */
    public PersonalMemberInfoRequest getPersonalMemberInfo() {
        return personalMemberInfo;
    }

    /**
     * @param personalMemberInfo the personalMemberInfo to set
     */
    public void setPersonalMemberInfo(PersonalMemberInfoRequest personalMemberInfo) {
        this.personalMemberInfo = personalMemberInfo;
    }

    /**
     * @return the activateAccount
     */
    public boolean isActivateAccount() {
        return activateAccount;
    }

    /**
     * @param activateAccount the activateAccount to set
     */
    public void setActivateAccount(boolean activateAccount) {
        this.activateAccount = activateAccount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
