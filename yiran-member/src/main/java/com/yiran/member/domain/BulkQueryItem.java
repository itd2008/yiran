/**
 * 
 */
package com.yiran.member.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * <p>批量查询会员账户状态项</p>
 */
public class BulkQueryItem implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8040975727462404069L;

    /**
     * 会员号
     */
    private String            memberId;
    /**
     * 账户类型
     */
    private String            accountType;
    /**
     * 账户号
     */
    private String            accountId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
