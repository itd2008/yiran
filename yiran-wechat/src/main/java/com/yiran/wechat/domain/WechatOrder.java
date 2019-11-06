package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import com.yiran.weixin.domain.WeixinUser;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表 wechat_order
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatOrder implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer orderId;
	/** 订单单号 */
	private String orderNo;
	/** 商店编号 */
	private String shopId;
	/** 订单状态:未付款,已付款,已发货,已签收,退货申请,退货中,已退货,取消交易 */
	private String orderStatus;
	/**
	 * 支付状态 I处理中、S成功、F失败
	 */
	private String payStatus;
	/** 商品数量:商品项目数量，不是商品 */
	private String productCount;
	/** 商品总价 */
	private String productAmountTotal;
	/** 订单金额:实际付款金额 */
	private String logisticsFee;
	/** 是否开箱验货  0否 1是 */
	private String isUnpackingInspection;
	/** 是否开票  0否 1是*/
	private String isOpenInvoice;
	/** 发票编号 */
	private String invoiceNo;
	/** 收货地址编号 */
	private String addressId;
	/** 订单物流编号 */
	private String orderlogisticsId;
	/**
	 * 快递公司名称
	 */
	private String delivername;
	/** 订单支付渠道 */
	private String payChannel;
	/** 订单支付单号 */
	private String escrowTradeNo;
	/** 付款时间 */
	private Date payTime;
	/** 发货时间 */
	private Date deliveryTime;
	/** 客户编号 */
	private String userId;
	/** 客户备注 */
	private String userRemark;
	/** 订单结算状态:货到付款、分期付款会用到 */
	private String orderSettlementStatus;
	/** 订单结算时间 */
	private String orderSettlementTime;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	
	private String extend;
	/**
	 * 微信用户
	 */
	private WeixinUser weixinUser;

	

	public String getDelivername() {
		return delivername;
	}

	public void setDelivername(String delivername) {
		this.delivername = delivername;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public void setOrderId(Integer orderId) 
	{
		this.orderId = orderId;
	}

	public Integer getOrderId() 
	{
		return orderId;
	}
	public void setOrderNo(String orderNo) 
	{
		this.orderNo = orderNo;
	}

	public String getOrderNo() 
	{
		return orderNo;
	}
	public void setShopId(String shopId) 
	{
		this.shopId = shopId;
	}

	public String getShopId() 
	{
		return shopId;
	}
	public void setOrderStatus(String orderStatus) 
	{
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() 
	{
		return orderStatus;
	}
	public void setProductCount(String productCount) 
	{
		this.productCount = productCount;
	}

	public String getProductCount() 
	{
		return productCount;
	}
	public void setProductAmountTotal(String productAmountTotal) 
	{
		this.productAmountTotal = productAmountTotal;
	}

	public String getProductAmountTotal() 
	{
		return productAmountTotal;
	}
	public void setLogisticsFee(String logisticsFee) 
	{
		this.logisticsFee = logisticsFee;
	}

	public String getLogisticsFee() 
	{
		return logisticsFee;
	}
	public void setIsUnpackingInspection(String isUnpackingInspection) 
	{
		this.isUnpackingInspection = isUnpackingInspection;
	}

	public String getIsUnpackingInspection() 
	{
		return isUnpackingInspection;
	}
	public void setIsOpenInvoice(String isOpenInvoice) 
	{
		this.isOpenInvoice = isOpenInvoice;
	}

	public String getIsOpenInvoice() 
	{
		return isOpenInvoice;
	}
	public void setInvoiceNo(String invoiceNo) 
	{
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceNo() 
	{
		return invoiceNo;
	}
	public void setAddressId(String addressId) 
	{
		this.addressId = addressId;
	}

	public String getAddressId() 
	{
		return addressId;
	}
	public void setOrderlogisticsId(String orderlogisticsId) 
	{
		this.orderlogisticsId = orderlogisticsId;
	}

	public String getOrderlogisticsId() 
	{
		return orderlogisticsId;
	}
	public void setPayChannel(String payChannel) 
	{
		this.payChannel = payChannel;
	}

	public String getPayChannel() 
	{
		return payChannel;
	}
	public void setEscrowTradeNo(String escrowTradeNo) 
	{
		this.escrowTradeNo = escrowTradeNo;
	}

	public String getEscrowTradeNo() 
	{
		return escrowTradeNo;
	}
	public void setPayTime(Date payTime) 
	{
		this.payTime = payTime;
	}

	public Date getPayTime() 
	{
		return payTime;
	}
	public void setDeliveryTime(Date deliveryTime) 
	{
		this.deliveryTime = deliveryTime;
	}

	public Date getDeliveryTime() 
	{
		return deliveryTime;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}
	public void setUserRemark(String userRemark) 
	{
		this.userRemark = userRemark;
	}

	public String getUserRemark() 
	{
		return userRemark;
	}
	public void setOrderSettlementStatus(String orderSettlementStatus) 
	{
		this.orderSettlementStatus = orderSettlementStatus;
	}

	public String getOrderSettlementStatus() 
	{
		return orderSettlementStatus;
	}
	public void setOrderSettlementTime(String orderSettlementTime) 
	{
		this.orderSettlementTime = orderSettlementTime;
	}

	public String getOrderSettlementTime() 
	{
		return orderSettlementTime;
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

	
    public WeixinUser getWeixinUser() {
		return weixinUser;
	}

	public void setWeixinUser(WeixinUser weixinUser) {
		this.weixinUser = weixinUser;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("orderNo", getOrderNo())
            .append("shopId", getShopId())
            .append("orderStatus", getOrderStatus())
            .append("productCount", getProductCount())
            .append("productAmountTotal", getProductAmountTotal())
            .append("logisticsFee", getLogisticsFee())
            .append("isUnpackingInspection", getIsUnpackingInspection())
            .append("isOpenInvoice", getIsOpenInvoice())
            .append("invoiceNo", getInvoiceNo())
            .append("addressId", getAddressId())
            .append("orderlogisticsId", getOrderlogisticsId())
            .append("payChannel", getPayChannel())
            .append("escrowTradeNo", getEscrowTradeNo())
            .append("payTime", getPayTime())
            .append("deliveryTime", getDeliveryTime())
            .append("userId", getUserId())
            .append("userRemark", getUserRemark())
            .append("orderSettlementStatus", getOrderSettlementStatus())
            .append("orderSettlementTime", getOrderSettlementTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
