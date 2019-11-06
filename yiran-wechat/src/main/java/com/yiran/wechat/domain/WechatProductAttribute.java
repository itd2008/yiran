package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品属性表 wechat_product_attribute
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductAttribute extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productAttributeId;
	/** 商品类别编号:商品属性归属于商品类别 */
	private String productCategoryId;
	private String productCategoryName;
	/** 属性标题  */
	private String title;
	/** 属性全称 */
	private String name;
	/** 属性代码 */
	private String code;
	/** 属性描述  */
	private String description;
	/** 属性预设内容 */
	private String content;
	/** 属性默认值 */
	private String value;
	/** 排列次序 */
	private String orders;
	/** 状态 */
	private String status;
	/** 创建时间 */
	private Date createTime;

	public void setProductAttributeId(Integer productAttributeId) 
	{
		this.productAttributeId = productAttributeId;
	}

	public Integer getProductAttributeId() 
	{
		return productAttributeId;
	}
	public void setProductCategoryId(String productCategoryId) 
	{
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryId() 
	{
		return productCategoryId;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
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
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getDescription() 
	{
		return description;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}
	public void setValue(String value) 
	{
		this.value = value;
	}

	public String getValue() 
	{
		return value;
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
            .append("productAttributeId", getProductAttributeId())
            .append("productCategoryId", getProductCategoryId())
            .append("productCategoryName", getProductCategoryName())
            .append("title", getTitle())
            .append("name", getName())
            .append("code", getCode())
            .append("description", getDescription())
            .append("content", getContent())
            .append("value", getValue())
            .append("orders", getOrders())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
