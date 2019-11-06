/**
 * 
 */
package com.yiran.member.request;

import com.yiran.member.enums.ContactTypeEnum;

/**
 * <p>联系信息查询对象</p>
 */
public class ContactQuery {
    private String          objectId;
    private ContactTypeEnum contactType;

    /**
     * @return the objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * @return the contactType
     */
    public ContactTypeEnum getContactType() {
        return contactType;
    }

    /**
     * @param contactType the contactType to set
     */
    public void setContactType(ContactTypeEnum contactType) {
        this.contactType = contactType;
    }

}
