package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>会员账户关联关系请求参数</p>
 */
public class AccRelationRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -8535725685246759465L;

    /**
     * 会员编号
     */
    private String            memberId;
    /**
     * 账户类型 type_id 101
     */
    private Long              accountType;
    /**
     * 账户编号
     */
    private String            accountId;

    public AccRelationRequest() {
        super();
    }

    public AccRelationRequest(String memberId, Long accountType, String accountId) {
        super();
        this.memberId = memberId;
        this.accountType = accountType;
        this.accountId = accountId;
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
