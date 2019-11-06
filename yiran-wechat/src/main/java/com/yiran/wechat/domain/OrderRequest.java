package com.yiran.wechat.domain;

import java.util.List;

/**
 * 创建订单和支付请求参数
 * @author pandaa
 *
 */
public class OrderRequest {
	/**
	 * 收件人地址ID
	 */
	private Integer addressId;
	/**
	 * 优惠券面值金额
	 */
	private String couponValue;
	/**
	 * 产品列表
	 */
	private List<ProductCatVO> productCatList;
	/**
	 * 用户openId
	 */
	private String openId;
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	public List<ProductCatVO> getProductCatList() {
		return productCatList;
	}
	public void setProductCatList(List<ProductCatVO> productCatList) {
		this.productCatList = productCatList;
	}
	
	

}
