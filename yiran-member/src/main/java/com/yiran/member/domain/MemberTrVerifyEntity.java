package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 认证实体表 member_tr_verify_entity
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTrVerifyEntity extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer verifyEntityId;
	/** 认证类型(1身份证 3 登录名 11 手机 12信箱) */
	private Integer verifyType;
	/** 认证实体 */
	private String verifyEntity;
	/** 认证子类型 */
	private Integer verifySubType;
	/** 认证渠道 */
	private Integer channel;
	/** 状态(0未认证 1已认证) */
	private Integer status;
	/** 建立时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 认证摘要 */
	private String verifySummary;
	/** 认证备注 */
	private String verifyMemo;
	
	/** 生效时间 */
	private Date verifiedTime;
	/** 失效时间 */
	private Date expireTime;
	/** 认证扫描件图片路径 */
	private String verifyImgPath;

	public void setVerifyEntityId(Integer verifyEntityId) 
	{
		this.verifyEntityId = verifyEntityId;
	}

	public Integer getVerifyEntityId() 
	{
		return verifyEntityId;
	}
	public void setVerifyType(Integer verifyType) 
	{
		this.verifyType = verifyType;
	}

	public Integer getVerifyType() 
	{
		return verifyType;
	}
	public void setVerifyEntity(String verifyEntity) 
	{
		this.verifyEntity = verifyEntity;
	}

	public String getVerifyEntity() 
	{
		return verifyEntity;
	}
	public void setVerifySubType(Integer verifySubType) 
	{
		this.verifySubType = verifySubType;
	}

	public Integer getVerifySubType() 
	{
		return verifySubType;
	}
	public void setChannel(Integer channel) 
	{
		this.channel = channel;
	}

	public Integer getChannel() 
	{
		return channel;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setVerifySummary(String verifySummary) 
	{
		this.verifySummary = verifySummary;
	}

	public String getVerifySummary() 
	{
		return verifySummary;
	}
	public void setVerifyMemo(String verifyMemo) 
	{
		this.verifyMemo = verifyMemo;
	}

	public String getVerifyMemo() 
	{
		return verifyMemo;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("verifyEntityId", getVerifyEntityId())
            .append("verifyType", getVerifyType())
            .append("verifyEntity", getVerifyEntity())
            .append("verifySubType", getVerifySubType())
            .append("channel", getChannel())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("verifySummary", getVerifySummary())
            .append("verifyMemo", getVerifyMemo())
            .toString();
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

	public String getVerifyImgPath() {
		return verifyImgPath;
	}

	public void setVerifyImgPath(String verifyImgPath) {
		this.verifyImgPath = verifyImgPath;
	}
    
    
}
