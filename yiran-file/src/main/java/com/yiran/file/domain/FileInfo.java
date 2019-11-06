package com.yiran.file.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 文件表 sys_file_info
 * 
 * @author yiran
 * @date 2019-03-27
 */
public class FileInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 新名字 */
	private String newName;
	/** 原文件名 */
	private String oldName;
	/** 文件大小 */
	private Long size;
	/** 文件后缀 */
	private String suffix;
	/** 样式 */
	private String cssStyle;
	/** 类型  1文档2图片4音乐3视频5其它 */
	private String type;
	/** 文件存储空间名 */
	private String bucketName;
	/** OSS文件路径 */
	private String ossUrl;
	/** 文件夹名称 */
	private String fileName;
	/** 标签 */
	private String lable;
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
	public void setNewName(String newName) 
	{
		this.newName = newName;
	}

	public String getNewName() 
	{
		return newName;
	}
	public void setOldName(String oldName) 
	{
		this.oldName = oldName;
	}

	public String getOldName() 
	{
		return oldName;
	}
	public void setSize(Long size) 
	{
		this.size = size;
	}

	public Long getSize() 
	{
		return size;
	}
	public void setSuffix(String suffix) 
	{
		this.suffix = suffix;
	}

	public String getSuffix() 
	{
		return suffix;
	}
	public void setCssStyle(String cssStyle) 
	{
		this.cssStyle = cssStyle;
	}

	public String getCssStyle() 
	{
		return cssStyle;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setBucketName(String bucketName) 
	{
		this.bucketName = bucketName;
	}

	public String getBucketName() 
	{
		return bucketName;
	}
	public void setOssUrl(String ossUrl) 
	{
		this.ossUrl = ossUrl;
	}

	public String getOssUrl() 
	{
		return ossUrl;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}
	public void setLable(String lable) 
	{
		this.lable = lable;
	}

	public String getLable() 
	{
		return lable;
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
            .append("newName", getNewName())
            .append("oldName", getOldName())
            .append("size", getSize())
            .append("suffix", getSuffix())
            .append("cssStyle", getCssStyle())
            .append("type", getType())
            .append("bucketName", getBucketName())
            .append("ossUrl", getOssUrl())
            .append("fileName", getFileName())
            .append("lable", getLable())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
