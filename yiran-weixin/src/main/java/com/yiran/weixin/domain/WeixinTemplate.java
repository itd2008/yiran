package com.yiran.weixin.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信模板表 sys_weixin_template
 * 
 * @author yiran
 * @date 2018-10-27
 */
public class WeixinTemplate implements Serializable
{
	private static final long serialVersionUID = -8124131184134825496L;
	/** 编号 */
	private String id;
	/** 模板Id */
	private String templateId;
	/** 模板标题 */
	private String templateTitle;
	/** 模板内容 */
	private String templateContent;
	/** 模板KEY */
	private String templateKey;
	/** 模板行业 */
	private String templateIndustry;
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

	/**
	 * 设置：编号
	 */
	public void setId(String id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：编号
	 */
	public String getId() 
	{
		return id;
	}
	
	/**
	 * 设置：模板Id
	 */
	public void setTemplateId(String templateId) 
	{
		this.templateId = templateId;
	}
	
	/**
	 * 获取：模板Id
	 */
	public String getTemplateId() 
	{
		return templateId;
	}
	
	/**
	 * 设置：模板标题
	 */
	public void setTemplateTitle(String templateTitle) 
	{
		this.templateTitle = templateTitle;
	}
	
	/**
	 * 获取：模板标题
	 */
	public String getTemplateTitle() 
	{
		return templateTitle;
	}
	
	/**
	 * 设置：模板内容
	 */
	public void setTemplateContent(String templateContent) 
	{
		this.templateContent = templateContent;
	}
	
	/**
	 * 获取：模板内容
	 */
	public String getTemplateContent() 
	{
		return templateContent;
	}
	
	/**
	 * 设置：模板KEY
	 */
	public void setTemplateKey(String templateKey) 
	{
		this.templateKey = templateKey;
	}
	
	/**
	 * 获取：模板KEY
	 */
	public String getTemplateKey() 
	{
		return templateKey;
	}
	
	/**
	 * 设置：模板行业
	 */
	public void setTemplateIndustry(String templateIndustry) 
	{
		this.templateIndustry = templateIndustry;
	}
	
	/**
	 * 获取：模板行业
	 */
	public String getTemplateIndustry() 
	{
		return templateIndustry;
	}
	
	/**
	 * 设置：创建者
	 */
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}
	
	/**
	 * 获取：创建者
	 */
	public String getCreateBy() 
	{
		return createBy;
	}
	
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}
	
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() 
	{
		return createTime;
	}
	
	/**
	 * 设置：更新者
	 */
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}
	
	/**
	 * 获取：更新者
	 */
	public String getUpdateBy() 
	{
		return updateBy;
	}
	
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() 
	{
		return updateTime;
	}
	
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	
	/**
	 * 获取：备注
	 */
	public String getRemark() 
	{
		return remark;
	}
	
}
