package com.yiran.member.request;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.member.base.Request;


/**
 * <p>查询联系信息</p>
 */
public class QueryContactRequest extends Request {

    /**
     * 
     */
    private static final long serialVersionUID = -3151937921785650258L;

    /**
     * 联系信息类型（0 会员联系信息 1 操作员联系信息）
     */
    private Long              contactType;

    /**
     * 联系信息所属对象编号（会员memberId,操作员operatorId）
     */
    private String            targetId;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
