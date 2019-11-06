package com.yiran.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 加密表 sys_ues_en_data
 * 
 * @author yiran
 * @date 2019-03-20
 */
public class UesEnData extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	/**
	 * 原文
	 */
	private String originalText;
	/** 编号 */
	private Integer id;
	/** 票据 */
	private String ticket;
	/** 加密密码 */
	private String encryptKey;
	/** 加密密文 */
	private String encryptData;
	/** 版本 */
	private String version;
	/** 加密类型 */
	private String encryptType;
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

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTicket(String ticket) 
	{
		this.ticket = ticket;
	}

	public String getTicket() 
	{
		return ticket;
	}
	public void setEncryptKey(String encryptKey) 
	{
		this.encryptKey = encryptKey;
	}

	public String getEncryptKey() 
	{
		return encryptKey;
	}
	public void setEncryptData(String encryptData) 
	{
		this.encryptData = encryptData;
	}

	public String getEncryptData() 
	{
		return encryptData;
	}
	public void setVersion(String version) 
	{
		this.version = version;
	}

	public String getVersion() 
	{
		return version;
	}
	public void setEncryptType(String encryptType) 
	{
		this.encryptType = encryptType;
	}

	public String getEncryptType() 
	{
		return encryptType;
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
        	.append("id", getId())
            .append("originalText", getOriginalText())
            .append("ticket", getTicket())
            .append("encryptKey", getEncryptKey())
            .append("encryptData", getEncryptData())
            .append("version", getVersion())
            .append("encryptType", getEncryptType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
    
    
}
