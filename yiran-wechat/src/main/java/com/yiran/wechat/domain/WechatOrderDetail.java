package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单商品详情表 wechat_order_detail
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatOrderDetail implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer orderDetailId;
	/** 订单编号 */
	private String orderId;
	/** 商品编号 */
	private Integer productId;
	/** 商品名称:商品可能删除,所以这里要记录，不能直接读商品表 */
	private String productName;
	/** 商品价格:商品可能删除,所以这里要记录 */
	private String productPrice;
	/** 商品型号:前台展示给客户 */
	private String productMarque;
	/** 商品条码:商品仓库条码 */
	private String productStoreBarcode;
	/** 商品型号信息:记录详细商品型号，如颜色、规格、包装等 */
	private String productModeDesc;
	/** 商品型号参数:JSON格式，记录单位编号、颜色编号、规格编号等 */
	private String productModeParams;
	/** 折扣比例 */
	private String discountRate;
	/** 折扣金额 */
	private String discountAmount;
	/** 购买数量 */
	private String number;
	/** 小计金额 */
	private String subtotal;
	/** 商品是否有效:0无效1有效 */
	private Integer isProductExists;
	/** 客户备注 */
	private String userRemark;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	
	private String imgUrl;
	/**
	 * 是否评论
	 */
	private String isComment;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setOrderDetailId(Integer orderDetailId) 
	{
		this.orderDetailId = orderDetailId;
	}

	public Integer getOrderDetailId() 
	{
		return orderDetailId;
	}
	public void setOrderId(String orderId) 
	{
		this.orderId = orderId;
	}

	public String getOrderId() 
	{
		return orderId;
	}
	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	public String getProductName() 
	{
		return productName;
	}
	public void setProductPrice(String productPrice) 
	{
		this.productPrice = productPrice;
	}

	public String getProductPrice() 
	{
		return productPrice;
	}
	public void setProductMarque(String productMarque) 
	{
		this.productMarque = productMarque;
	}

	public String getProductMarque() 
	{
		return productMarque;
	}
	public void setProductStoreBarcode(String productStoreBarcode) 
	{
		this.productStoreBarcode = productStoreBarcode;
	}

	public String getProductStoreBarcode() 
	{
		return productStoreBarcode;
	}
	public void setProductModeDesc(String productModeDesc) 
	{
		this.productModeDesc = productModeDesc;
	}

	public String getProductModeDesc() 
	{
		return productModeDesc;
	}
	public void setProductModeParams(String productModeParams) 
	{
		this.productModeParams = productModeParams;
	}

	public String getProductModeParams() 
	{
		return productModeParams;
	}
	public void setDiscountRate(String discountRate) 
	{
		this.discountRate = discountRate;
	}

	public String getDiscountRate() 
	{
		return discountRate;
	}
	public void setDiscountAmount(String discountAmount) 
	{
		this.discountAmount = discountAmount;
	}

	public String getDiscountAmount() 
	{
		return discountAmount;
	}
	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getNumber() 
	{
		return number;
	}
	public void setSubtotal(String subtotal) 
	{
		this.subtotal = subtotal;
	}

	public String getSubtotal() 
	{
		return subtotal;
	}
	public void setIsProductExists(Integer isProductExists) 
	{
		this.isProductExists = isProductExists;
	}

	public Integer getIsProductExists() 
	{
		return isProductExists;
	}
	public void setUserRemark(String userRemark) 
	{
		this.userRemark = userRemark;
	}

	public String getUserRemark() 
	{
		return userRemark;
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
	
	

    public String getIsComment() {
		return isComment;
	}

	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderDetailId", getOrderDetailId())
            .append("orderId", getOrderId())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("productPrice", getProductPrice())
            .append("productMarque", getProductMarque())
            .append("productStoreBarcode", getProductStoreBarcode())
            .append("productModeDesc", getProductModeDesc())
            .append("productModeParams", getProductModeParams())
            .append("discountRate", getDiscountRate())
            .append("discountAmount", getDiscountAmount())
            .append("number", getNumber())
            .append("subtotal", getSubtotal())
            .append("isProductExists", getIsProductExists())
            .append("userRemark", getUserRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
