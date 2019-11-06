/**
 * 
 */
package com.yiran.member.response;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;


/**
 * <p>激活个人会员响应</p>
 */
public class ActivatePersonalResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = 5908601704276514902L;
    
    /**
     * 账户号
     */
    private String            accountId;

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
