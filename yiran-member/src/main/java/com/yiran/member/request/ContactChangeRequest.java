package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;
import com.yiran.member.domain.MemberTmContact;


/**
 * <p>修改联系信息</p>
 */
public class ContactChangeRequest extends Request {
    /**
     * 
     */
    private static final long serialVersionUID = -8358768039515146467L;
    
    private MemberTmContact           contactInfo;                             //联系信息

    public MemberTmContact getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(MemberTmContact contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
