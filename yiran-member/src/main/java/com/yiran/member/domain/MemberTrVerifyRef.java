package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 认证关系表 member_tr_verify_ref
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTrVerifyRef extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer verifyId;
	/** 会员ID */
	private String memberId;
	/** 认证实体ID */
	private Integer verifyEntityId;
	/** 生效时间 */
	private Date verifiedTime;
	/** 失效时间 */
	private Date expireTime;
	/** 认证扫描件图片路径 */
	private String verifyImgPath;
	/** 状态(-1已解绑被删除 0未绑定 1已绑定) */
	private Integer status;
	/** 建立时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 备注信息 */
	private String memo;

	public void setVerifyId(Integer verifyId) 
	{
		this.verifyId = verifyId;
	}

	public Integer getVerifyId() 
	{
		return verifyId;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setVerifyEntityId(Integer verifyEntityId) 
	{
		this.verifyEntityId = verifyEntityId;
	}

	public Integer getVerifyEntityId() 
	{
		return verifyEntityId;
	}
	public void setVerifiedTime(Date verifiedTime) 
	{
		this.verifiedTime = verifiedTime;
	}

	public Date getVerifiedTime() 
	{
		return verifiedTime;
	}
	public void setExpireTime(Date expireTime) 
	{
		this.expireTime = expireTime;
	}

	public Date getExpireTime() 
	{
		return expireTime;
	}
	public void setVerifyImgPath(String verifyImgPath) 
	{
		this.verifyImgPath = verifyImgPath;
	}

	public String getVerifyImgPath() 
	{
		return verifyImgPath;
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
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("verifyId", getVerifyId())
            .append("memberId", getMemberId())
            .append("verifyEntityId", getVerifyEntityId())
            .append("verifiedTime", getVerifiedTime())
            .append("expireTime", getExpireTime())
            .append("verifyImgPath", getVerifyImgPath())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("memo", getMemo())
            .toString();
    }
}
