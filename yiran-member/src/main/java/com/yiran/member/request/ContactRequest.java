package com.yiran.member.request;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;
import com.yiran.member.domain.MemberTmContact;

/**
 * <p>设置联系信息</p>
 */
public class ContactRequest extends Request {

    /**
     * 
     */
    private static final long   serialVersionUID = 6007809814122793163L;

    private Long                contactType;                             //联系信息类型（0 会员联系信息 1 操作员联系信息）

    private String              targetId;                                //联系信息所属对象编号（会员memberId,操作员operatorId）

    private MemberTmContact             contactInfo;                             //联系信息(必填)

    public Long getContactType() {
        return contactType;
    }

    public void setContactType(Long contactType) {
        this.contactType = contactType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public MemberTmContact getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(MemberTmContact contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
