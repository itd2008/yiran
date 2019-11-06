/**
 * 
 */
package com.yiran.member.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>更新会员锁状态</p>
 */
public class UpdateMemberLockStatusRequest extends Request {
    /**
     * 
     */
    private static final long serialVersionUID = -8113167457528036305L;

    /**
     * 会员编码
     */
    private String            memberId;

    /**
     * 锁定状态:0 未锁定， 1 锁定
     */
    private Long              lockStatus;

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
     * @return the lockStatus
     */
    public Long getLockStatus() {
        return lockStatus;
    }

    /**
     * @param lockStatus the lockStatus to set
     */
    public void setLockStatus(Long lockStatus) {
        this.lockStatus = lockStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
