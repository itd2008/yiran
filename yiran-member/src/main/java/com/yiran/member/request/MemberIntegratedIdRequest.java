/**
 * 
 */
package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>会员综合查询请求信息</p>
 */
public class MemberIntegratedIdRequest extends Request {

    /**
     * 
     */
    private static final long   serialVersionUID = -3046978392659615148L;

    /** 会员编号*/
    private String              memberId;

    /** 是否需要查询认证信息*/
    private boolean             requireVerifyInfos;

    /** 是否需要查询默认操作员*/
    private boolean             requireDefaultOperator;

    /** 账户查询信息*/
    private AccountQueryRequest accountRequest;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public boolean isRequireVerifyInfos() {
        return requireVerifyInfos;
    }

    public void setRequireVerifyInfos(boolean requireVerifyInfos) {
        this.requireVerifyInfos = requireVerifyInfos;
    }

    public AccountQueryRequest getAccountRequest() {
        return accountRequest;
    }

    public void setAccountRequest(AccountQueryRequest accountRequest) {
        this.accountRequest = accountRequest;
    }

    public boolean isRequireDefaultOperator() {
        return requireDefaultOperator;
    }

    public void setRequireDefaultOperator(boolean requireDefaultOperator) {
        this.requireDefaultOperator = requireDefaultOperator;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
