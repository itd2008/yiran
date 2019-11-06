package com.yiran.member.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 *
 * <p>查询个人会员请求</p>
 */
public class PersonalMemberQueryRequest extends Request {
    private static final long serialVersionUID = 7595146087599120449L;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 默认构造子
     */
    public PersonalMemberQueryRequest() {
        super();
    }

    /**
     * 带参数构造子
     */
    public PersonalMemberQueryRequest(String memberId) {
        super();
        this.memberId = memberId;
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
