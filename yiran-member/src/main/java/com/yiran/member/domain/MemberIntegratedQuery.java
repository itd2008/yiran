/**
 * 
 */
package com.yiran.member.domain;

import java.util.List;

/**
 * <p>会员综合信息查询条件</p>
 */
public class MemberIntegratedQuery {
    /** 会员编号*/
    private String     memberId;
    /** 会员标识*/
    private String     memberIdentity;
    /** 会员所属平台类型（0默认：微汇平台， 1：新浪微博）*/
    private int        platformType;
    /** 是否需要查询认证信息*/
    private boolean    requireVerifyInfos;
    /** 是否需要查询默认操作员*/
    private boolean    requireDefaultOperator;
    /** 账户查询信息*/
    private boolean    requireAccountInfos;
    /**要查询的账户类型，当RequireAccounts为true时有效，空代表查询所有账户类型 */
    private List<Long> accountTypes;
    
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberIdentity() {
        return memberIdentity;
    }

    public void setMemberIdentity(String memberIdentity) {
        this.memberIdentity = memberIdentity;
    }

    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

    public boolean isRequireVerifyInfos() {
        return requireVerifyInfos;
    }

    public void setRequireVerifyInfos(boolean requireVerifyInfos) {
        this.requireVerifyInfos = requireVerifyInfos;
    }

    public boolean isRequireAccountInfos() {
        return requireAccountInfos;
    }

    public void setRequireAccountInfos(boolean requireAccountInfos) {
        this.requireAccountInfos = requireAccountInfos;
    }

    public List<Long> getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(List<Long> accountTypes) {
        this.accountTypes = accountTypes;
    }

    public boolean isRequireDefaultOperator() {
        return requireDefaultOperator;
    }

    public void setRequireDefaultOperator(boolean requireDefaultOperator) {
        this.requireDefaultOperator = requireDefaultOperator;
    }

}
