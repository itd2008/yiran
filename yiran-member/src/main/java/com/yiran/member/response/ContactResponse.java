package com.yiran.member.response;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Response;


/**
 * <p>设置联系信息响应信息</p>
 */
public class ContactResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = -553333022697129121L;

    private String            contactId;                               //联系人Id

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
