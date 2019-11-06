package com.yiran.message.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 短信模板表 sys_sms_template
 * 
 * @author yiran
 * @date 2019-03-08
 */
public class SysSmsTemplate extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 模版名称 */
	private String name;
	/** 模版编码 */
	private String code;
	/** 业务类型 */
	private String businessType;
	/** 模版ID */
	private String templateId;
	/** 模版内容 */
	private String templateContent;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createDate;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateDate;
	/** 删除标记（0：正常；1：删除） */
	private String delFlag;
	/** 备注信息 */
	private String remarks;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
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
	public void setBusinessType(String businessType) 
	{
		this.businessType = businessType;
	}

	public String getBusinessType() 
	{
		return businessType;
	}
	public void setTemplateId(String templateId) 
	{
		this.templateId = templateId;
	}

	public String getTemplateId() 
	{
		return templateId;
	}
	public void setTemplateContent(String templateContent) 
	{
		this.templateContent = templateContent;
	}

	public String getTemplateContent() 
	{
		return templateContent;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() 
	{
		return updateDate;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("businessType", getBusinessType())
            .append("templateId", getTemplateId())
            .append("templateContent", getTemplateContent())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("delFlag", getDelFlag())
            .append("remarks", getRemarks())
            .toString();
    }
}
