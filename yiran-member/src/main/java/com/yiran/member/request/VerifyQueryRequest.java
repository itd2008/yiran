/**
 * 
 */
package com.yiran.member.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Request;


/**
 * <p>查询本会员的认证信息</p>
 */
public class VerifyQueryRequest extends Request {
    private static final long serialVersionUID = -6054168840645825764L;
    private String            memberId;                                //会员编号
    private Integer           verifyType;                              //认证类型

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(Integer verifyType) {
        this.verifyType = verifyType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
