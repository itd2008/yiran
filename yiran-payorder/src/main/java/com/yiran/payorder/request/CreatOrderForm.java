package com.yiran.payorder.request;

import java.io.Serializable;
/**
 * 创建订单表单
 * @author pandaa
 *
 */
public class CreatOrderForm implements Serializable{
	/**
	 * 产品描述
	 */
	private String productDes;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 商品价格
	 */
	private String orderPrice;
	/**
	 * 交易类型
	 */
	private String tradeType;
	
	public String getProductDes() {
		return productDes;
	}
	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	@Override
	public String toString() {
		return "CreatOrderForm [productDes=" + productDes + ", orderNo=" + orderNo + ", orderPrice=" + orderPrice
				+ ", tradeType=" + tradeType + "]";
	}

	
}
