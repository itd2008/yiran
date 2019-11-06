/**
 * 
 */
package com.yiran.member.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>认证信息</p>
 */
public class VerifyInfo implements Serializable {
    private static final long serialVersionUID = -2290431989118058448L;

    /**
     * 会员编号(新增时必填)
     */
    private String            memberId;

    /**
     * 认证类型(新增时必填,修改时如果提供了verifyEntity，verifyType 也需要提供)
     */
    private Integer           verifyType;

    /**
     * 认证实体(新增时必填,修改时如果提供了verifyType，verifyEntity 也需要提供)
     */
    private String            verifyEntity;

    private Date              verifiedTime;

    private Date              expireTime;

    private String            imgPath;

    private Integer           status;

    private Integer           channel;

    /**
     * 认证id (修改时必填)
     */
    private Long              verifyId;

    private String            extention;

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

    public String getVerifyEntity() {
        return verifyEntity;
    }

    public void setVerifyEntity(String verifyEntity) {
        this.verifyEntity = verifyEntity;
    }

    public Date getVerifiedTime() {
        return verifiedTime;
    }

    public void setVerifiedTime(Date verifiedTime) {
        this.verifiedTime = verifiedTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Long verifyId) {
        this.verifyId = verifyId;
    }

    public String getExtention() {
        return extention;
    }

    public void setExtention(String extention) {
        this.extention = extention;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
