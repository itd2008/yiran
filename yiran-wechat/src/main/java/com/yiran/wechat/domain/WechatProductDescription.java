package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 商品描述内容表 wechat_product_description
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductDescription extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productDescriptionId;
	/** 商品编号 */
	private Integer productId;
	/** 标题 */
	private String title;
	/** 代码 (code, 如:description, specification, aftersale) */
	private String code;
	/** 内容 */
	private String content;

	public void setProductDescriptionId(Integer productDescriptionId) 
	{
		this.productDescriptionId = productDescriptionId;
	}

	public Integer getProductDescriptionId() 
	{
		return productDescriptionId;
	}
	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productDescriptionId", getProductDescriptionId())
            .append("productId", getProductId())
            .append("title", getTitle())
            .append("code", getCode())
            .append("content", getContent())
            .toString();
    }
}
