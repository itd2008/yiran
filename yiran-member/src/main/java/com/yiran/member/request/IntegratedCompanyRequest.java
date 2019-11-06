/**
 * 
 */
package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.domain.MemberTmMerchant;
import com.yiran.member.domain.MemberTrCompanyMember;

/**
 * <p>企业会员集成创建</p>
 */
public class IntegratedCompanyRequest extends CreateMemberInfoRequest {
    private static final long     serialVersionUID = -4068656419075118498L;
    
    /**
     * 企业会员信息
    */
    private MemberTrCompanyMember companyInfo;
    /**
     * 商户信息
     */
    private MemberTmMerchant merchantInfo;
 
    /**
     * 会员状态与账户标志（0默认：会员未激活，且不开储值基本户；
     * 1：会员激活，并开未激活储值基本户；
     * 2：会员账户都激活）
     */
    private Integer               memberAccountFlag;

    public MemberTrCompanyMember getCompanyInfo() {
        return companyInfo;
    }


    public void setCompanyInfo(MemberTrCompanyMember companyInfo) {
        this.companyInfo = companyInfo;
    }


    public MemberTmMerchant getMerchantInfo() {
        return merchantInfo;
    }


    public void setMerchantInfo(MemberTmMerchant merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    public Integer getMemberAccountFlag() {
        return memberAccountFlag;
    }

    public void setMemberAccountFlag(Integer memberAccountFlag) {
        this.memberAccountFlag = memberAccountFlag;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
