package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 商品与属性关联表 wechat_product_and_attribute
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductAndAttribute extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productAndAttributeId;
	/** 商品编号 */
	private Integer productId;
	/** 属性编号 */
	private Integer productAttributeId;
	/** 属性值 */
	private String attrValue;

	public void setProductAndAttributeId(Integer productAndAttributeId) 
	{
		this.productAndAttributeId = productAndAttributeId;
	}

	public Integer getProductAndAttributeId() 
	{
		return productAndAttributeId;
	}
	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setProductAttributeId(Integer productAttributeId) 
	{
		this.productAttributeId = productAttributeId;
	}

	public Integer getProductAttributeId() 
	{
		return productAttributeId;
	}
	public void setAttrValue(String attrValue) 
	{
		this.attrValue = attrValue;
	}

	public String getAttrValue() 
	{
		return attrValue;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productAndAttributeId", getProductAndAttributeId())
            .append("productId", getProductId())
            .append("productAttributeId", getProductAttributeId())
            .append("attrValue", getAttrValue())
            .toString();
    }
}
