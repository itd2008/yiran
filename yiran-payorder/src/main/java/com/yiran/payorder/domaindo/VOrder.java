package com.yiran.payorder.domaindo;
/**
 * 订单信息
 * @author pandaa
 *
 */
public class VOrder {
	
	/** 自动编号 */
	private Integer orderId;
	/**
	 * 客户昵称
	 */
	private String customerName;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 订单状态
	 */
	private String orderStatus;
	
	/**
	 * 支付状态
	 */
	private String payStatus;
	/**
	 * 商品数量
	 */
	private String productCount;
	/**
	 * 实付金额
	 */
	private String logisticsFee;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	private String instOrderId;
	/** 订单支付单号 */
	private String escrowTradeNo;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getEscrowTradeNo() {
		return escrowTradeNo;
	}
	public void setEscrowTradeNo(String escrowTradeNo) {
		this.escrowTradeNo = escrowTradeNo;
	}
	public String getInstOrderId() {
		return instOrderId;
	}
	public void setInstOrderId(String instOrderId) {
		this.instOrderId = instOrderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
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
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getProductCount() {
		return productCount;
	}
	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}
	public String getLogisticsFee() {
		return logisticsFee;
	}
	public void setLogisticsFee(String logisticsFee) {
		this.logisticsFee = logisticsFee;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
