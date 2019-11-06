/**
 * 
 */
package com.yiran.member.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Response;

/**
 * <p>创建会员响应</p>
 */
public class CreateMemberInfoResponse extends Response{

    /**
     * 
     */
    private static final long serialVersionUID = 6405210553542745298L;

    private String            memberId;

    private String            operatorId;

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

    /**
     * @return the operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId the operatorId to set
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
