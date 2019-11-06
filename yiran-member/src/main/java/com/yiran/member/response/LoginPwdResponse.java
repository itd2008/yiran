package com.yiran.member.response;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;

public class LoginPwdResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = -5107823982903362192L;

    /**
     * ResponseCode=274 表示解锁剩余时间（单位为分钟），ResponseCode=266 表示还可以重试次数。
     */
    private Long              remainNum;
    /**
     * 成功后返回会员ID
     */
    private String            memberId;
    /**
     * 成功后返回操作员ID
     */
    private String            operatorId;

    public Long getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Long remainNum) {
        this.remainNum = remainNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
