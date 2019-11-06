package com.yiran.member.response;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;
import com.yiran.member.domain.Account;


/**
 * <p>查询会员账户余额响应结果</p>
 */
public class AccountInfoResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = -5180772439765060714L;

    /**
    * 账户信息
    */
    private Account           account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
