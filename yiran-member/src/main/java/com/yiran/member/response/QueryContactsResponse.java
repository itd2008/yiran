package com.yiran.member.response;

import java.util.List;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTmContact;


public class QueryContactsResponse extends Response{

    /**
     * 
     */
    private static final long serialVersionUID = -6595887398541081207L;
    
    /**
     * 联系信息
     */
    private List<MemberTmContact> contactInfo;

    public List<MemberTmContact> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<MemberTmContact> contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
