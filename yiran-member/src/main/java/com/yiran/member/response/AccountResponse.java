package com.yiran.member.response;

import java.util.List;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;
import com.yiran.member.domain.Account;


/**
 * <p>查询会员账户余额响应结果</p>
 */
public class AccountResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = -3514563135693175414L;

    /**
     * 账户信息列表
     */
    private List<Account>     accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
