package com.yiran.member.response;

import java.util.List;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.domain.MemberTrVerifyEntity;
import com.yiran.member.domain.VerifyInfo;
import com.yiran.member.request.BaseMemberInfo;


/**
 * 
 * <p>会员综合信息</p>
 */
public class MemberIntegratedResponse extends Response {
    private static final long serialVersionUID = 4987794898420837377L;

    /**
     * 会员基本信息
     */
    private BaseMemberInfo    baseMemberInfo;

    /**
     * 认证信息集合
     */
    private List<MemberTrVerifyEntity>  verifyInfos;

    /**
     * 账户信息集合
     */
    private List<AccountInfo> accountInfos;

    /**
     * 默认操作员
     */
    private MemberTmOperator          defaultOperator;

    public BaseMemberInfo getBaseMemberInfo() {
        return baseMemberInfo;
    }

    public void setBaseMemberInfo(BaseMemberInfo baseMemberInfo) {
        this.baseMemberInfo = baseMemberInfo;
    }

    public List<MemberTrVerifyEntity> getVerifyInfos() {
        return verifyInfos;
    }

    public void setVerifyInfos(List<MemberTrVerifyEntity> verifyInfos) {
        this.verifyInfos = verifyInfos;
    }

    public List<AccountInfo> getAccountInfos() {
        return accountInfos;
    }

    public void setAccountInfos(List<AccountInfo> accountInfos) {
        this.accountInfos = accountInfos;
    }

    public MemberTmOperator getDefaultOperator() {
        return defaultOperator;
    }

    public void setDefaultOperator(MemberTmOperator defaultOperator) {
        this.defaultOperator = defaultOperator;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
