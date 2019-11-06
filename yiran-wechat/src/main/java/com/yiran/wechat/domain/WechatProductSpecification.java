package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 商品规格表 wechat_product_specification
 * 
 * @author yiran
 * @date 2019-06-25
 */
public class WechatProductSpecification extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer productSpecification;
	/** 商品类别编号 */
	private Integer productCategoryId;
	/** 商品类别名 */
	private String productCategoryName;
	/** 规格类型 (颜色、尺码、包装等) */
	private String specificationType;
	private String specificationCode;
	/** 规格值 */
	private String specificationValue;

	public void setProductSpecification(Integer productSpecification) 
	{
		this.productSpecification = productSpecification;
	}

	public Integer getProductSpecification() 
	{
		return productSpecification;
	}
	public void setProductCategoryId(Integer productCategoryId) 
	{
		this.productCategoryId = productCategoryId;
	}

	public Integer getProductCategoryId() 
	{
		return productCategoryId;
	}
	public void setProductCategoryName(String productCategoryName) 
	{
		this.productCategoryName = productCategoryName;
	}

	public String getProductCategoryName() 
	{
		return productCategoryName;
	}
	public void setSpecificationType(String specificationType) 
	{
		this.specificationType = specificationType;
	}

	public String getSpecificationType() 
	{
		return specificationType;
	}
	public void setSpecificationValue(String specificationValue) 
	{
		this.specificationValue = specificationValue;
	}

	public String getSpecificationValue() 
	{
		return specificationValue;
	}

    public String getSpecificationCode() {
		return specificationCode;
	}

	public void setSpecificationCode(String specificationCode) {
		this.specificationCode = specificationCode;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productSpecification", getProductSpecification())
            .append("productCategoryId", getProductCategoryId())
            .append("productCategoryName", getProductCategoryName())
            .append("specificationType", getSpecificationType())
            .append("specificationCode", getSpecificationCode())
            .append("specificationValue", getSpecificationValue())
            .toString();
    }
}
