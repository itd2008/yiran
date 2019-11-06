package com.yiran.wechat.domain;

import java.io.Serializable;
import java.util.List;

import com.yiran.wechat.domain.ProductCatVO;

/**
 * 订单结果返回对象
 * @author pandaa
 *
 */
public class WeChatOrderResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5820969957856943916L;
	/** 自动编号 */
	private Integer orderId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 订单状态:0未付款,1已付款,2已发货,3已签收,4退货申请,5退货中,6已退货,7取消交易
	 */
	private String orderStatus;
	/** 商品数量:商品项目数量，不是商品 */
	private String productCount;
	/** 商品总价 */
	private String productAmountTotal;
	/**
	 * 产品列表
	 */
	private List<ProductCatVO> productCatList;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getProductCount() {
		return productCount;
	}
	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	public String getProductAmountTotal() {
		return productAmountTotal;
	}
	public void setProductAmountTotal(String productAmountTotal) {
		this.productAmountTotal = productAmountTotal;
	}
	public List<ProductCatVO> getProductCatList() {
		return productCatList;
	}
	public void setProductCatList(List<ProductCatVO> productCatList) {
		this.productCatList = productCatList;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
