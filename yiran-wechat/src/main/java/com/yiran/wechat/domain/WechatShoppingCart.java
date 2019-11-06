package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 购物车表 wechat_shopping_cart
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatShoppingCart extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer id;
	/** 用户编号 */
	private String userId;
	/**
	 * 微信昵称
	 */
	private String nickName;
	/** 商店编号 */
	private Integer shopId;
	/** 商品编号 */
	private Integer productId;
	/**
	 * 产品标题
	 */
	private String productTitle;
	/** 是否有效，0表示无效，1表示有效 */
	private Integer isProductExists;
	/** 购买数量 */
	private Integer number;
	/** 创建时间 */
	private Date createTime;
	/**
	 * 属性
	 */
	private String attr;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 商品信息
	 */
	private WechatProduct wechatProduct;
	

	public WechatProduct getWechatProduct() {
		return wechatProduct;
	}

	public void setWechatProduct(WechatProduct wechatProduct) {
		this.wechatProduct = wechatProduct;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setShopId(Integer shopId) 
	{
		this.shopId = shopId;
	}

	public Integer getShopId() 
	{
		return shopId;
	}
	public void setProductId(Integer productId) 
	{
		this.productId = productId;
	}

	public Integer getProductId() 
	{
		return productId;
	}
	public void setIsProductExists(Integer isProductExists) 
	{
		this.isProductExists = isProductExists;
	}

	public Integer getIsProductExists() 
	{
		return isProductExists;
	}
	public void setNumber(Integer number) 
	{
		this.number = number;
	}

	public Integer getNumber() 
	{
		return number;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	
	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("shopId", getShopId())
            .append("productId", getProductId())
            .append("isProductExists", getIsProductExists())
            .append("number", getNumber())
            .append("createTime", getCreateTime())
            .append("nickName", getNickName())
            .append("productTitle", getProductTitle())
            .append("attr", getAttr())
            .toString();
    }
}
