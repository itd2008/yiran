package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 运费模板表 wechat_freight_template
 * 
 * @author yiran
 * @date 2019-07-06
 */
public class WechatFreightTemplate extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 模板ID */
	private Integer freightTemplateId;
	/** 模板名称 */
	private String templateName;
	/**  */
	private String templateType;
	/** 包邮区 */
	private String parcelPostArea;
	/** 不配送区域 */
	private String nonDistributionArea;
	/** 买家付邮费区域 */
	private String buyerPaymentArea;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;

	public void setFreightTemplateId(Integer freightTemplateId) 
	{
		this.freightTemplateId = freightTemplateId;
	}

	public Integer getFreightTemplateId() 
	{
		return freightTemplateId;
	}
	public void setTemplateName(String templateName) 
	{
		this.templateName = templateName;
	}

	public String getTemplateName() 
	{
		return templateName;
	}
	public void setTemplateType(String templateType) 
	{
		this.templateType = templateType;
	}

	public String getTemplateType() 
	{
		return templateType;
	}
	public void setParcelPostArea(String parcelPostArea) 
	{
		this.parcelPostArea = parcelPostArea;
	}

	public String getParcelPostArea() 
	{
		return parcelPostArea;
	}
	public void setNonDistributionArea(String nonDistributionArea) 
	{
		this.nonDistributionArea = nonDistributionArea;
	}

	public String getNonDistributionArea() 
	{
		return nonDistributionArea;
	}
	public void setBuyerPaymentArea(String buyerPaymentArea) 
	{
		this.buyerPaymentArea = buyerPaymentArea;
	}

	public String getBuyerPaymentArea() 
	{
		return buyerPaymentArea;
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
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("freightTemplateId", getFreightTemplateId())
            .append("templateName", getTemplateName())
            .append("templateType", getTemplateType())
            .append("parcelPostArea", getParcelPostArea())
            .append("nonDistributionArea", getNonDistributionArea())
            .append("buyerPaymentArea", getBuyerPaymentArea())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
