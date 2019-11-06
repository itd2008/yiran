package com.yiran.wechat.domain;

public class PriceAndQuantity implements Comparable<PriceAndQuantity>{
	/**
	 * 规格1
	 */
	private String specification;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 折扣
	 */
	private String discount;
	/**
	 * 折扣价
	 */
	private Double discountPrice;
	/**
	 * 库存
	 */
	private Integer inventory;
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	@Override
	public String toString() {
		return "PriceAndQuantity [specification=" + specification + ", price=" + price + ", discount=" + discount
				+ ", discountPrice=" + discountPrice + ", inventory=" + inventory + "]";
	}
	@Override
	public int compareTo(PriceAndQuantity o) {
		
		return this.price.compareTo(o.price);
	}

	
}
