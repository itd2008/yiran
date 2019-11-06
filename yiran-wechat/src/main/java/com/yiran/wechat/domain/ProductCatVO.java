package com.yiran.wechat.domain;
/**
 * 购物车商品信息
 * @author pandaa
 *
 */
public class ProductCatVO {

	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 单个商品购买总价
	 */
	private String totalPrice;
	/**
	 * 单个商品购买总数
	 */
	private String totalNum;
	/**
	 * 商品标题
	 */
	private String title;
	/**
	 * 商品规格属性
	 */
	private String attr;
	/**
	 * 商品图片路径
	 */
	private String pictureUrl;
	/**
	 * 是否评论 0 未评论 1已经评论
	 */
	private String isComment;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public String getIsComment() {
		return isComment;
	}
	public void setIsComment(String isComment) {
		this.isComment = isComment;
	}
	
	
}
