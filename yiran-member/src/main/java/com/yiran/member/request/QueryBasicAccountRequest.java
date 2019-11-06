/**
 * 
 */
package com.yiran.member.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>基本户查询条件</p>
 */
public class QueryBasicAccountRequest extends Request {
    /**
     * 
     */
    private static final long serialVersionUID = 8272742791679042521L;
    /** 会员编号*/
    private String     memberId;
    /** 会员标识*/
    private String     memberIdentity;
    /** 会员所属平台类型*/
    private String        platformType;
 
    
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

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
   
}
