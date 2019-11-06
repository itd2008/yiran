/**
 * 
 */
package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>查询账户余额收支明细请求参数</p>
 */
public class AccBalanceListRequest extends Request {
    private static final long          serialVersionUID = -3344007814992290088L;
    private GetBalanceListRequestParam balanceRequest;                          //帐户入参
    private String                     memberId;                                //会员编号
    private Long                       accountType;                             //账户类型
    private String                     accountId;                               //账户编号

    public GetBalanceListRequestParam getBalanceRequest() {
        return balanceRequest;
    }

    public void setBalanceRequest(GetBalanceListRequestParam balanceRequest) {
        this.balanceRequest = balanceRequest;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
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
