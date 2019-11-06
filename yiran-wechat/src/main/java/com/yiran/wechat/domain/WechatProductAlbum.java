package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品相册表 wechat_product_album
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatProductAlbum extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer productAlbumId;
	/** 商品编号 */
	private Integer productId;
	/** 图片名称 */
	private String name;
	/** 图片url */
	private String imageUrl;
	/** 图片大小 */
	private String imageSize;
	/** 图片宽度 */
	private String width;
	/** 图片高度 */
	private String height;
	/** 图片介绍 */
	private String intro;
	/** 排列次序 */
	private String sort;
	/** 状态:0不可显示，1显示 */
	private String status;
	/** 创建时间 */
	private Date createTime;

	public void setProductAlbumId(Integer productAlbumId) 
	{
		this.productAlbumId = productAlbumId;
	}

	public Integer getProductAlbumId() 
	{
		return productAlbumId;
	}
	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setImageUrl(String imageUrl) 
	{
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() 
	{
		return imageUrl;
	}
	public void setImageSize(String imageSize) 
	{
		this.imageSize = imageSize;
	}

	public String getImageSize() 
	{
		return imageSize;
	}
	public void setWidth(String width) 
	{
		this.width = width;
	}

	public String getWidth() 
	{
		return width;
	}
	public void setHeight(String height) 
	{
		this.height = height;
	}

	public String getHeight() 
	{
		return height;
	}
	public void setIntro(String intro) 
	{
		this.intro = intro;
	}

	public String getIntro() 
	{
		return intro;
	}
	public void setSort(String sort) 
	{
		this.sort = sort;
	}

	public String getSort() 
	{
		return sort;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productAlbumId", getProductAlbumId())
            .append("productId", getProductId())
            .append("name", getName())
            .append("imageUrl", getImageUrl())
            .append("imageSize", getImageSize())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("intro", getIntro())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .toString();
    }
}
