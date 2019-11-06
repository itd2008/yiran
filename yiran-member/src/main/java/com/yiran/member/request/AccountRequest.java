package com.yiran.member.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>查询会员账户余额请求参数</p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountRequest extends Request {

    /**
     *
     */
    private static final long serialVersionUID = 7180748671646375395L;

    /**
    * 会员编号
    */
    private String            memberId;
    /**
    * 账户类型
    */
    private Long              accountType;

    public AccountRequest() {

    }

    public AccountRequest(String memberId, Long accountType) {
        this.memberId = memberId;
        this.accountType = accountType;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
