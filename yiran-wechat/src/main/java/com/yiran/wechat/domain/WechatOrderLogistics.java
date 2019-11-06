package com.yiran.wechat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 订单物流表 wechat_order_logistics
 * 
 * @author yiran
 * @date 2019-06-13
 */
public class WechatOrderLogistics extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer orderlogisticsId;
	/** 订单编号 */
	private Integer orderId;
	/** 物流单号:发货快递单号 */
	private String expressNo;
	/** 收货人姓名: 收货地址表可能更新或删除，因此要在这里记录 */
	private String consigneeRealname;
	/** 联系电话:收货地址表可能更新或删除，因此要在这里记录 */
	private String consigneeTelphone;
	/** 备用联系电话:收货地址表可能更新或删除，因此要在这里记录 */
	private String consigneeTelphone2;
	/** 收货地址 */
	private String consigneeAddress;
	/** 邮政编码 */
	private String consigneeZip;
	/** 物流方式 */
	private String logisticsType;
	/** 物流商家编号:物流商家表自动编号 */
	private String logisticsId;
	/** 物流发货运费:显示给客户的订单运费 */
	private String logisticsFee;
	/** 快递代收货款费率: 快递公司代收货款费率，如货值的2%-5%，一般月结 */
	private String agencyFee;
	/** 物流成本金额:实际支付给物流公司的金额 */
	private String deliveryAmount;
	/** 物流状态 */
	private String orderlogisticsStatus;
	/** 物流结算状态 */
	private String logisticsSettlementStatus;
	/** 物流最后状态描述 */
	private String logisticsResultLast;
	/** 物流描述 */
	private String logisticsResult;
	/** 发货时间 */
	private Date logisticsCreateTime;
	/** 物流更新时间 */
	private Date logisticsUpdateTime;
	/** 物流结算时间 */
	private Date logisticsSettlementTime;
	/** 物流支付渠道 */
	private String payChannel;
	/** 物流支付单号 */
	private String escrowTradeNo;
	/** 物流公司已对账状态:0已对账,1未对账 */
	private String reconciliationStatus;
	/** 物流公司对账日期 */
	private Date reconciliationTime;

	public void setOrderlogisticsId(Integer orderlogisticsId) 
	{
		this.orderlogisticsId = orderlogisticsId;
	}

	public Integer getOrderlogisticsId() 
	{
		return orderlogisticsId;
	}
	public void setOrderId(Integer orderId) 
	{
		this.orderId = orderId;
	}

	public Integer getOrderId() 
	{
		return orderId;
	}
	public void setExpressNo(String expressNo) 
	{
		this.expressNo = expressNo;
	}

	public String getExpressNo() 
	{
		return expressNo;
	}
	public void setConsigneeRealname(String consigneeRealname) 
	{
		this.consigneeRealname = consigneeRealname;
	}

	public String getConsigneeRealname() 
	{
		return consigneeRealname;
	}
	public void setConsigneeTelphone(String consigneeTelphone) 
	{
		this.consigneeTelphone = consigneeTelphone;
	}

	public String getConsigneeTelphone() 
	{
		return consigneeTelphone;
	}
	public void setConsigneeTelphone2(String consigneeTelphone2) 
	{
		this.consigneeTelphone2 = consigneeTelphone2;
	}

	public String getConsigneeTelphone2() 
	{
		return consigneeTelphone2;
	}
	public void setConsigneeAddress(String consigneeAddress) 
	{
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeAddress() 
	{
		return consigneeAddress;
	}
	public void setConsigneeZip(String consigneeZip) 
	{
		this.consigneeZip = consigneeZip;
	}

	public String getConsigneeZip() 
	{
		return consigneeZip;
	}
	public void setLogisticsType(String logisticsType) 
	{
		this.logisticsType = logisticsType;
	}

	public String getLogisticsType() 
	{
		return logisticsType;
	}
	public void setLogisticsId(String logisticsId) 
	{
		this.logisticsId = logisticsId;
	}

	public String getLogisticsId() 
	{
		return logisticsId;
	}
	public void setLogisticsFee(String logisticsFee) 
	{
		this.logisticsFee = logisticsFee;
	}

	public String getLogisticsFee() 
	{
		return logisticsFee;
	}
	public void setAgencyFee(String agencyFee) 
	{
		this.agencyFee = agencyFee;
	}

	public String getAgencyFee() 
	{
		return agencyFee;
	}
	public void setDeliveryAmount(String deliveryAmount) 
	{
		this.deliveryAmount = deliveryAmount;
	}

	public String getDeliveryAmount() 
	{
		return deliveryAmount;
	}
	public void setOrderlogisticsStatus(String orderlogisticsStatus) 
	{
		this.orderlogisticsStatus = orderlogisticsStatus;
	}

	public String getOrderlogisticsStatus() 
	{
		return orderlogisticsStatus;
	}
	public void setLogisticsSettlementStatus(String logisticsSettlementStatus) 
	{
		this.logisticsSettlementStatus = logisticsSettlementStatus;
	}

	public String getLogisticsSettlementStatus() 
	{
		return logisticsSettlementStatus;
	}
	public void setLogisticsResultLast(String logisticsResultLast) 
	{
		this.logisticsResultLast = logisticsResultLast;
	}

	public String getLogisticsResultLast() 
	{
		return logisticsResultLast;
	}
	public void setLogisticsResult(String logisticsResult) 
	{
		this.logisticsResult = logisticsResult;
	}

	public String getLogisticsResult() 
	{
		return logisticsResult;
	}
	public void setLogisticsCreateTime(Date logisticsCreateTime) 
	{
		this.logisticsCreateTime = logisticsCreateTime;
	}

	public Date getLogisticsCreateTime() 
	{
		return logisticsCreateTime;
	}
	public void setLogisticsUpdateTime(Date logisticsUpdateTime) 
	{
		this.logisticsUpdateTime = logisticsUpdateTime;
	}

	public Date getLogisticsUpdateTime() 
	{
		return logisticsUpdateTime;
	}
	public void setLogisticsSettlementTime(Date logisticsSettlementTime) 
	{
		this.logisticsSettlementTime = logisticsSettlementTime;
	}

	public Date getLogisticsSettlementTime() 
	{
		return logisticsSettlementTime;
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
	public void setReconciliationStatus(String reconciliationStatus) 
	{
		this.reconciliationStatus = reconciliationStatus;
	}

	public String getReconciliationStatus() 
	{
		return reconciliationStatus;
	}
	public void setReconciliationTime(Date reconciliationTime) 
	{
		this.reconciliationTime = reconciliationTime;
	}

	public Date getReconciliationTime() 
	{
		return reconciliationTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderlogisticsId", getOrderlogisticsId())
            .append("orderId", getOrderId())
            .append("expressNo", getExpressNo())
            .append("consigneeRealname", getConsigneeRealname())
            .append("consigneeTelphone", getConsigneeTelphone())
            .append("consigneeTelphone2", getConsigneeTelphone2())
            .append("consigneeAddress", getConsigneeAddress())
            .append("consigneeZip", getConsigneeZip())
            .append("logisticsType", getLogisticsType())
            .append("logisticsId", getLogisticsId())
            .append("logisticsFee", getLogisticsFee())
            .append("agencyFee", getAgencyFee())
            .append("deliveryAmount", getDeliveryAmount())
            .append("orderlogisticsStatus", getOrderlogisticsStatus())
            .append("logisticsSettlementStatus", getLogisticsSettlementStatus())
            .append("logisticsResultLast", getLogisticsResultLast())
            .append("logisticsResult", getLogisticsResult())
            .append("logisticsCreateTime", getLogisticsCreateTime())
            .append("logisticsUpdateTime", getLogisticsUpdateTime())
            .append("logisticsSettlementTime", getLogisticsSettlementTime())
            .append("payChannel", getPayChannel())
            .append("escrowTradeNo", getEscrowTradeNo())
            .append("reconciliationStatus", getReconciliationStatus())
            .append("reconciliationTime", getReconciliationTime())
            .toString();
    }
}
