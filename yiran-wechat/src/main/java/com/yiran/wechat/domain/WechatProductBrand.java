package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品品牌表 wechat_product_brand
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductBrand extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productBrandId;
	/** 商品类别编号:商品属性归属于商品类别 */
	private String productCategoryId;
	/**商品类目名*/
	private String productCategoryName;
	/** 品牌名称  */
	private String name;
	/** 图片url */
	private String imageUrl;
	/** 排列次序 */
	private String orders;
	/** 状态 */
	private String status;
	/** 创建时间 */
	private Date createTime;

	public void setProductBrandId(Integer productBrandId) 
	{
		this.productBrandId = productBrandId;
	}

	public Integer getProductBrandId() 
	{
		return productBrandId;
	}
	public void setProductCategoryId(String productCategoryId) 
	{
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryId() 
	{
		return productCategoryId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setImageUrl(String imageUrl) 
	{
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() 
	{
		return imageUrl;
	}
	public void setOrders(String orders) 
	{
		this.orders = orders;
	}

	public String getOrders() 
	{
		return orders;
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

    public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productBrandId", getProductBrandId())
            .append("productCategoryId", getProductCategoryId())
            .append("productCategoryName", getProductCategoryName())
            .append("name", getName())
            .append("imageUrl", getImageUrl())
            .append("orders", getOrders())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
