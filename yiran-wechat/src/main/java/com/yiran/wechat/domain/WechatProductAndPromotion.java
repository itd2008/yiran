package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品促销活动关联表 wechat_product_and_promotion
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductAndPromotion extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productAndPromotionId;
	/** 商品编号 */
	private Integer productId;
	/** 促销活动编号:关联商品促销活动表 */
	private Integer windowId;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 活动价格 */
	private String price;
	/** 商品图片 */
	private String imageUrl;
	/** 商品简介 */
	private String intro;
	/** 排列次序 */
	private String sort;
	/** 创建时间 */
	private Date createTime;

	public void setProductAndPromotionId(Integer productAndPromotionId) 
	{
		this.productAndPromotionId = productAndPromotionId;
	}

	public Integer getProductAndPromotionId() 
	{
		return productAndPromotionId;
	}
	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setWindowId(Integer windowId) 
	{
		this.windowId = windowId;
	}

	public Integer getWindowId() 
	{
		return windowId;
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
	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getPrice() 
	{
		return price;
	}
	public void setImageUrl(String imageUrl) 
	{
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() 
	{
		return imageUrl;
	}
	public void setIntro(String intro) 
	{
		this.intro = intro;
	}

	public String getIntro() 
	{
		return intro;
	}
	public void setSort(String sort) 
	{
		this.sort = sort;
	}

	public String getSort() 
	{
		return sort;
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
            .append("productAndPromotionId", getProductAndPromotionId())
            .append("productId", getProductId())
            .append("windowId", getWindowId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("price", getPrice())
            .append("imageUrl", getImageUrl())
            .append("intro", getIntro())
            .append("sort", getSort())
            .append("createTime", getCreateTime())
            .toString();
    }
}
