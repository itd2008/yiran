package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;
import com.yiran.member.domain.MemberTrBankAccount;


/**
 * <p>会员银行卡绑定请求信息</p>
 */
public class BankAccInfoRequest extends Request {
    private static final long serialVersionUID = 1586762843889339972L;

    private MemberTrBankAccount   bankInfo;                               //会员银行绑定信息


    public MemberTrBankAccount getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(MemberTrBankAccount bankInfo) {
        this.bankInfo = bankInfo;
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
