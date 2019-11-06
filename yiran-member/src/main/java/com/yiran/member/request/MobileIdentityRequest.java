package com.yiran.member.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>手机标识请求对象</p>
 */
public class MobileIdentityRequest extends Request {

    /**
     *
     */
    private static final long serialVersionUID = 7180748671646375395L;

    /**
     * 会员号
     */
    private String            memberId;

    /**
    * 手机号
    */
    private String            mobileNo;

    /** 会员所属平台类型（0默认：平台）*/
    private String            platformType;

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
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
