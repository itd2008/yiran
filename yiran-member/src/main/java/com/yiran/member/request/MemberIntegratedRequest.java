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
public class MemberIntegratedRequest extends Request {
    private static final long   serialVersionUID = 2842697300601932778L;

    /** 会员标识*/
    private String              memberIdentity;
    /** 会员所属平台类型（0默认：平台）*/
    private String              platformType;
    /** 是否需要查询认证信息*/
    private boolean             requireVerifyInfos;
    /** 是否需要查询默认操作员*/
    private boolean             requireDefaultOperator;
    /** 账户查询信息*/
    private AccountQueryRequest accountRequest;

    public String getMemberIdentity() {
        return memberIdentity;
    }

    public void setMemberIdentity(String memberIdentity) {
        this.memberIdentity = memberIdentity;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
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
