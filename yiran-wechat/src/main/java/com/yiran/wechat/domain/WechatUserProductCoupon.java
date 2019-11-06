package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 用户优惠券关联表 wechat_user_product_coupon
 * 
 * @author yiran
 * @date 2019-09-08
 */
public class WechatUserProductCoupon extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 用户openId */
	private String openId;
	/** 优惠券序列号 */
	private String couponSerialNo;
	/** 状态：0未使用1已使用2已过期 */
	private Integer status;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOpenId(String openId) 
	{
		this.openId = openId;
	}

	public String getOpenId() 
	{
		return openId;
	}
	public void setCouponSerialNo(String couponSerialNo) 
	{
		this.couponSerialNo = couponSerialNo;
	}

	public String getCouponSerialNo() 
	{
		return couponSerialNo;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openId", getOpenId())
            .append("couponSerialNo", getCouponSerialNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
