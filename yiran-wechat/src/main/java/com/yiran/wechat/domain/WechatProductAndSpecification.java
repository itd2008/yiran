package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 商品与商品规格关联表 wechat_product_and_specification
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductAndSpecification extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productAndSpecificationId;
	/** 商品编号 */
	private String productId;
	/** 商品规格编号 */
	private String productSpecificationId;
	/** 商品库存 */
	private String stock;
	/** 商品价格 */
	private String price;
	/** 商品简介 */
	private String intro;

	public void setProductAndSpecificationId(Integer productAndSpecificationId) 
	{
		this.productAndSpecificationId = productAndSpecificationId;
	}

	public Integer getProductAndSpecificationId() 
	{
		return productAndSpecificationId;
	}
	public void setProductId(String productId) 
	{
		this.productId = productId;
	}

	public String getProductId() 
	{
		return productId;
	}
	public void setProductSpecificationId(String productSpecificationId) 
	{
		this.productSpecificationId = productSpecificationId;
	}

	public String getProductSpecificationId() 
	{
		return productSpecificationId;
	}
	public void setStock(String stock) 
	{
		this.stock = stock;
	}

	public String getStock() 
	{
		return stock;
	}
	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getPrice() 
	{
		return price;
	}
	public void setIntro(String intro) 
	{
		this.intro = intro;
	}

	public String getIntro() 
	{
		return intro;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productAndSpecificationId", getProductAndSpecificationId())
            .append("productId", getProductId())
            .append("productSpecificationId", getProductSpecificationId())
            .append("stock", getStock())
            .append("price", getPrice())
            .append("intro", getIntro())
            .toString();
    }
}
