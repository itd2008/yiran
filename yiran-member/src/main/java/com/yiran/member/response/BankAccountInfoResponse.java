package com.yiran.member.response;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTrBankAccount;

/**
 * <p>查询会员银行绑定响应</p>
 */
public class BankAccountInfoResponse extends Response {

    /**
     * 
     */
    private static final long  serialVersionUID = -8390890286290061793L;

    private MemberTrBankAccount bankAcctInfo;                            //银行账户详细信息

    /**
     * @return the bankAcctInfo
     */
    public MemberTrBankAccount getBankAcctInfo() {
        return bankAcctInfo;
    }

    /**
     * @param bankAcctInfo the bankAcctInfo to set
     */
    public void setBankAcctInfo(MemberTrBankAccount bankAcctInfo) {
        this.bankAcctInfo = bankAcctInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
