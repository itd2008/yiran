package com.yiran.wechat.domain;
/**
 * 规格VO
 * @author pandaa
 *
 */
public class SpecificationsVO {

	/** 商品编号 */
	private String productId;
	/** 商品规格编号 */
	private String productSpecificationId;
	/** 商品库存 */
	private String stock;
	/** 商品价格 */
	private String price;
	/** 商品类别名 */
	private String productCategoryName;
	/** 规格类型 (颜色、尺码、包装等) */
	private String specificationType;
	private String specificationCode;
	/** 规格值 */
	private String specificationValue;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductSpecificationId() {
		return productSpecificationId;
	}
	public void setProductSpecificationId(String productSpecificationId) {
		this.productSpecificationId = productSpecificationId;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public String getSpecificationType() {
		return specificationType;
	}
	public void setSpecificationType(String specificationType) {
		this.specificationType = specificationType;
	}
	public String getSpecificationCode() {
		return specificationCode;
	}
	public void setSpecificationCode(String specificationCode) {
		this.specificationCode = specificationCode;
	}
	public String getSpecificationValue() {
		return specificationValue;
	}
	public void setSpecificationValue(String specificationValue) {
		this.specificationValue = specificationValue;
	}
	@Override
	public String toString() {
		return "SpecificationsVO [productId=" + productId + ", productSpecificationId=" + productSpecificationId
				+ ", stock=" + stock + ", price=" + price + ", productCategoryName=" + productCategoryName
				+ ", specificationType=" + specificationType + ", specificationCode=" + specificationCode
				+ ", specificationValue=" + specificationValue + "]";
	}
	
	
	
}
