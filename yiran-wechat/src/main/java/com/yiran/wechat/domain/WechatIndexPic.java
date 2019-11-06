package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 首页宣传图片表 wechat_index_pic
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatIndexPic extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Integer id;
	/** 创建时间 */
	private Date createDate;
	/** 首页的链接类型 0表示网内链接 1表示往外的链接 */
	private Integer linkType;
	/** 图片url地址 */
	private String linkUrl;
	/**  图片的状态，0表示停用，1表示启动 */
	private Integer status;
	/** 子标题 */
	private String subTitle;
	/** 标题 */
	private String title;
	/** 图片的新名称 */
	private String newName;
	/** 图片的原始名称 */
	private String oldName;
	/** 位置 */
	private Integer pos;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setLinkType(Integer linkType) 
	{
		this.linkType = linkType;
	}

	public Integer getLinkType() 
	{
		return linkType;
	}
	public void setLinkUrl(String linkUrl) 
	{
		this.linkUrl = linkUrl;
	}

	public String getLinkUrl() 
	{
		return linkUrl;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setSubTitle(String subTitle) 
	{
		this.subTitle = subTitle;
	}

	public String getSubTitle() 
	{
		return subTitle;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
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
	public void setPos(Integer pos) 
	{
		this.pos = pos;
	}

	public Integer getPos() 
	{
		return pos;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createDate", getCreateDate())
            .append("linkType", getLinkType())
            .append("linkUrl", getLinkUrl())
            .append("status", getStatus())
            .append("subTitle", getSubTitle())
            .append("title", getTitle())
            .append("newName", getNewName())
            .append("oldName", getOldName())
            .append("pos", getPos())
            .toString();
    }
}
