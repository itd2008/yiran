package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品促销活动表 wechat_product_promotion_event
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductPromotionEvent extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productPromotionEventId;
	/** 活动名称：橱窗名称, 最新,热门,推荐,清仓,换季等 */
	private String name;
	/** 活动代码:英文唯一 */
	private String code;
	/** 封面图片url */
	private String imageUrl;
	/** 商品数量统计 */
	private String productCount;
	/** 备注 */
	private String remark;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 排列次序 */
	private String sort;
	/** 状态:0不可显示，1显示 */
	private String status;
	/** 创建时间 */
	private Date createTime;

	public void setProductPromotionEventId(Integer productPromotionEventId) 
	{
		this.productPromotionEventId = productPromotionEventId;
	}

	public Integer getProductPromotionEventId() 
	{
		return productPromotionEventId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setImageUrl(String imageUrl) 
	{
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() 
	{
		return imageUrl;
	}
	public void setProductCount(String productCount) 
	{
		this.productCount = productCount;
	}

	public String getProductCount() 
	{
		return productCount;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setStartTime(Date startTime) 
	{
		this.startTime = startTime;
	}

	public Date getStartTime() 
	{
		return startTime;
	}
	public void setEndTime(Date endTime) 
	{
		this.endTime = endTime;
	}

	public Date getEndTime() 
	{
		return endTime;
	}
	public void setSort(String sort) 
	{
		this.sort = sort;
	}

	public String getSort() 
	{
		return sort;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productPromotionEventId", getProductPromotionEventId())
            .append("name", getName())
            .append("code", getCode())
            .append("imageUrl", getImageUrl())
            .append("productCount", getProductCount())
            .append("remark", getRemark())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
