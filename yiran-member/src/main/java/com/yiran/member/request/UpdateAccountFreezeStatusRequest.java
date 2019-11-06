/**
 * 
 */
package com.yiran.member.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>更新账户冻结状态</p>
 */
public class UpdateAccountFreezeStatusRequest extends Request {
    private static final long serialVersionUID = -3717301191282236626L;
    private String            accountId;
    private String            memberId;
    private Long              accountType;
    private Long              freezeStatus;                            //冻结状态    0:未冻结    1:止出    2:止入    3:双向冻结(锁定)
    
    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public Long getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Long freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
