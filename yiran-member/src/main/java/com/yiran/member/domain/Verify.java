package com.yiran.member.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yiran.member.enums.VerifyStatusEnum;


/**
 * 
 * <p>验证信息vo</p>
 */
public class Verify implements Serializable {

    private static final long serialVersionUID = 6695200338010035363L;
    
    private long              verifyId;

    private String            memberId;

    private Integer           verifyType;

    private String            verifyEntity;
    
    private String            verifyEntityTicket;

    private Date              verifiedTime;

    private Date              expireTime;

    private String            imgPath;

    private String            memo;
    
    private VerifyStatusEnum  status;

    private Integer           verifySubType;
    
    private Integer           channel;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(long verifyId) {
        this.verifyId = verifyId;
    }

    public Integer getVerifySubType() {
        return verifySubType;
    }

    public void setVerifySubType(Integer verifySubType) {
        this.verifySubType = verifySubType;
    }

    public VerifyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(VerifyStatusEnum status) {
        this.status = status;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getVerifyEntityTicket() {
        return verifyEntityTicket;
    }

    public void setVerifyEntityTicket(String verifyEntityTicket) {
        this.verifyEntityTicket = verifyEntityTicket;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
