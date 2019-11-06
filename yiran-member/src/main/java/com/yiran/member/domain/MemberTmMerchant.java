package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商户表 member_tm_merchant
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTmMerchant extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键(由seq_merchant_id 生成,并以4开头) */
	private String merchantId;
	/** 会员编号 */
	private String memberId;
	/** 商户名,已注销的会被添加后缀 */
	private String merchantName;
	/** 商户类型 */
	private Integer merchantType;
	/** 微汇通行证 */
	private String ptid;
	/** 密钥类型 */
	private Integer keyType;
	/** 密钥 */
	private String keyData;
	/** 状态(0未激活 1正常  3注销) */
	private Integer status;
	/** 建立时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 建立人 */
	private String createUser;
	/** 更新人 */
	private String updateUser;
	/** 备注信息 */
	private String memo;

	public void setMerchantId(String merchantId) 
	{
		this.merchantId = merchantId;
	}

	public String getMerchantId() 
	{
		return merchantId;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setMerchantName(String merchantName) 
	{
		this.merchantName = merchantName;
	}

	public String getMerchantName() 
	{
		return merchantName;
	}
	public void setMerchantType(Integer merchantType) 
	{
		this.merchantType = merchantType;
	}

	public Integer getMerchantType() 
	{
		return merchantType;
	}
	public void setPtid(String ptid) 
	{
		this.ptid = ptid;
	}

	public String getPtid() 
	{
		return ptid;
	}
	public void setKeyType(Integer keyType) 
	{
		this.keyType = keyType;
	}

	public Integer getKeyType() 
	{
		return keyType;
	}
	public void setKeyData(String keyData) 
	{
		this.keyData = keyData;
	}

	public String getKeyData() 
	{
		return keyData;
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
	public void setCreateUser(String createUser) 
	{
		this.createUser = createUser;
	}

	public String getCreateUser() 
	{
		return createUser;
	}
	public void setUpdateUser(String updateUser) 
	{
		this.updateUser = updateUser;
	}

	public String getUpdateUser() 
	{
		return updateUser;
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
            .append("merchantId", getMerchantId())
            .append("memberId", getMemberId())
            .append("merchantName", getMerchantName())
            .append("merchantType", getMerchantType())
            .append("ptid", getPtid())
            .append("keyType", getKeyType())
            .append("keyData", getKeyData())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .toString();
    }
}
