package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 图标分类表 wechat_icon_sort
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatIconSort extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 图片id */
	private Integer id;
	/** 图标名称 */
	private String name;
	/**
	 * 图标连接地址
	 */
	private String urlLink;
	/** 是否是显示，0表示否，1表示是 */
	private Integer isShow;
	/** 图标排序 */
	private Integer orders;
	/** 图片地址 */
	private String iconPath;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;

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
	public void setIsShow(Integer isShow) 
	{
		this.isShow = isShow;
	}

	public Integer getIsShow() 
	{
		return isShow;
	}
	public void setOrders(Integer orders) 
	{
		this.orders = orders;
	}

	public Integer getOrders() 
	{
		return orders;
	}
	public void setIconPath(String iconPath) 
	{
		this.iconPath = iconPath;
	}

	public String getIconPath() 
	{
		return iconPath;
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

    public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("urlLink", getUrlLink())
            .append("isShow", getIsShow())
            .append("orders", getOrders())
            .append("iconPath", getIconPath())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
