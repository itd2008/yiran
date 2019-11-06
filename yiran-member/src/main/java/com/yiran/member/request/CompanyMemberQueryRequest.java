/**
 * 
 */
package com.yiran.member.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>企业会员查询请求</p>
 */
public class CompanyMemberQueryRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = 4944725583337105083L;

    /**
     * 会员id
     */
    private String            memberId;

    public CompanyMemberQueryRequest() {
        super();
    }

    public CompanyMemberQueryRequest(String memberId) {
        super();
        this.memberId = memberId;
    }

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
