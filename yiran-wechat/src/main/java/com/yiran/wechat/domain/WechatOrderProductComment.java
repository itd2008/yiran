package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 订单评论表 wechat_order_product_comment
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatOrderProductComment extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 用户昵称 */
	private String userNick;
	/** 订单号 */
	private String orderId;
	/** 产品编号 */
	private String productId;
	/** 是否显示，0表示否，1表示是 */
	private Integer isShow;
	/** 评论内容 */
	private String comment;
	/** 追加评论 */
	private String appendComment;
	/** 评论分数 */
	private String commentaryScore;
	/** 评论回复 */
	private String commentReply;
	/** 评论图片 */
	private String imagePath;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/**
	 * 微信openId
	 */
	private String openId;

	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserNick(String userNick) 
	{
		this.userNick = userNick;
	}

	public String getUserNick() 
	{
		return userNick;
	}
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}

	public String getOrderId() 
	{
		return orderId;
	}
	public void setProductId(String productId) 
	{
		this.productId = productId;
	}

	public String getProductId() 
	{
		return productId;
	}
	public void setIsShow(Integer isShow) 
	{
		this.isShow = isShow;
	}

	public Integer getIsShow() 
	{
		return isShow;
	}
	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment() 
	{
		return comment;
	}
	public void setAppendComment(String appendComment) 
	{
		this.appendComment = appendComment;
	}

	public String getAppendComment() 
	{
		return appendComment;
	}
	public void setCommentaryScore(String commentaryScore) 
	{
		this.commentaryScore = commentaryScore;
	}

	public String getCommentaryScore() 
	{
		return commentaryScore;
	}
	public void setCommentReply(String commentReply) 
	{
		this.commentReply = commentReply;
	}

	public String getCommentReply() 
	{
		return commentReply;
	}
	public void setImagePath(String imagePath) 
	{
		this.imagePath = imagePath;
	}

	public String getImagePath() 
	{
		return imagePath;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userNick", getUserNick())
            .append("orderId", getOrderId())
            .append("productId", getProductId())
            .append("isShow", getIsShow())
            .append("comment", getComment())
            .append("appendComment", getAppendComment())
            .append("commentaryScore", getCommentaryScore())
            .append("commentReply", getCommentReply())
            .append("imagePath", getImagePath())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
