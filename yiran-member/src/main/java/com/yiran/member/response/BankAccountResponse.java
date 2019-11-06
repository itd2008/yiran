package com.yiran.member.response;

import java.util.List;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTrBankAccount;

/**
 * <p>查询会员银行绑定响应</p>
 */
public class BankAccountResponse extends Response {

    /**
     * 
     */
    private static final long     serialVersionUID = 201340581166562597L;

    private List<MemberTrBankAccount> bankAccountInfos;                      // 会员银行账户信息

    public List<MemberTrBankAccount> getBankAccountInfos() {
        return bankAccountInfos;
    }

    public void setBankAccountInfos(List<MemberTrBankAccount> bankAccountInfos) {
        this.bankAccountInfos = bankAccountInfos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
