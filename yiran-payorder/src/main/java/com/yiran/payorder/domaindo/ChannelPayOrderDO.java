package com.yiran.payorder.domaindo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 渠道支付订单表 channel_pay_order
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class ChannelPayOrderDO extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 渠道流水号 */
	private String paySeqNo;
	/** 支付流水号 */
	private String paymentSeqNo;
	/** 请求批次号 */
	private String requestBatchNo;
	/** 订单类型：I（入款），B（退款），O（出款） */
	private String orderType;
	/** 产品码 */
	private String productCode;
	/** 支付编码 */
	private String paymentCode;
	/** 会员ID,现阶段是PT帐号 */
	private String memberId;
	/** 金额 */
	private Double amount;
	/** 币种 */
	private String currency;
	/** 机构编码 */
	private String instCode;
	/** 支付结果通知状态：S（通知成功），F（通知失败），N（不通知） */
	private String paymentNotifyStatus;
	/** 通信类型，S（单笔通信），B（批量通信） */
	private String communicateType;
	/** 状态：A（待处理），C（已撤销），I（处理中），S（成功），F（失败） */
	private String status;
	/** 操作员，默认SYSTEM */
	private String operator;
	/** 提交时间 */
	private Date gmtSubmit;
	/** 机构订单ID */
	private Integer instOrderId;
	/** 创建时间 */
	private Date gmtCreate;
	/** 业务发生时间 */
	private String bizDate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 提交状态 */
	private String confirmStatus;
	/** 支付方式 */
	private String payMode;
	/** 批次号 */
	private String submitBatchNo;
	/** 原始订单号 */
	private String orgiPaymentSeqNo;
	/** 期望时间 */
	private Date expectTime;
	/** 扩展参数 */
	private String extension;
	/** 结算id */
	private String settlementId;
	/** 原订单结算id */
	private String orgiSettlementId;

	public void setPaySeqNo(String paySeqNo) 
	{
		this.paySeqNo = paySeqNo;
	}

	public String getPaySeqNo() 
	{
		return paySeqNo;
	}
	public void setPaymentSeqNo(String paymentSeqNo) 
	{
		this.paymentSeqNo = paymentSeqNo;
	}

	public String getPaymentSeqNo() 
	{
		return paymentSeqNo;
	}
	public void setRequestBatchNo(String requestBatchNo) 
	{
		this.requestBatchNo = requestBatchNo;
	}

	public String getRequestBatchNo() 
	{
		return requestBatchNo;
	}
	public void setOrderType(String orderType) 
	{
		this.orderType = orderType;
	}

	public String getOrderType() 
	{
		return orderType;
	}
	public void setProductCode(String productCode) 
	{
		this.productCode = productCode;
	}

	public String getProductCode() 
	{
		return productCode;
	}
	public void setPaymentCode(String paymentCode) 
	{
		this.paymentCode = paymentCode;
	}

	public String getPaymentCode() 
	{
		return paymentCode;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setCurrency(String currency) 
	{
		this.currency = currency;
	}

	public String getCurrency() 
	{
		return currency;
	}
	public void setInstCode(String instCode) 
	{
		this.instCode = instCode;
	}

	public String getInstCode() 
	{
		return instCode;
	}
	public void setPaymentNotifyStatus(String paymentNotifyStatus) 
	{
		this.paymentNotifyStatus = paymentNotifyStatus;
	}

	public String getPaymentNotifyStatus() 
	{
		return paymentNotifyStatus;
	}
	public void setCommunicateType(String communicateType) 
	{
		this.communicateType = communicateType;
	}

	public String getCommunicateType() 
	{
		return communicateType;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setOperator(String operator) 
	{
		this.operator = operator;
	}

	public String getOperator() 
	{
		return operator;
	}
	public void setGmtSubmit(Date gmtSubmit) 
	{
		this.gmtSubmit = gmtSubmit;
	}

	public Date getGmtSubmit() 
	{
		return gmtSubmit;
	}
	public void setInstOrderId(Integer instOrderId) 
	{
		this.instOrderId = instOrderId;
	}

	public Integer getInstOrderId() 
	{
		return instOrderId;
	}
	public void setGmtCreate(Date gmtCreate) 
	{
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() 
	{
		return gmtCreate;
	}
	public void setBizDate(String bizDate) 
	{
		this.bizDate = bizDate;
	}

	public String getBizDate() 
	{
		return bizDate;
	}
	public void setGmtModified(Date gmtModified) 
	{
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() 
	{
		return gmtModified;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setConfirmStatus(String confirmStatus) 
	{
		this.confirmStatus = confirmStatus;
	}

	public String getConfirmStatus() 
	{
		return confirmStatus;
	}
	public void setPayMode(String payMode) 
	{
		this.payMode = payMode;
	}

	public String getPayMode() 
	{
		return payMode;
	}
	public void setSubmitBatchNo(String submitBatchNo) 
	{
		this.submitBatchNo = submitBatchNo;
	}

	public String getSubmitBatchNo() 
	{
		return submitBatchNo;
	}
	public void setOrgiPaymentSeqNo(String orgiPaymentSeqNo) 
	{
		this.orgiPaymentSeqNo = orgiPaymentSeqNo;
	}

	public String getOrgiPaymentSeqNo() 
	{
		return orgiPaymentSeqNo;
	}
	public void setExpectTime(Date expectTime) 
	{
		this.expectTime = expectTime;
	}

	public Date getExpectTime() 
	{
		return expectTime;
	}
	public void setExtension(String extension) 
	{
		this.extension = extension;
	}

	public String getExtension() 
	{
		return extension;
	}
	public void setSettlementId(String settlementId) 
	{
		this.settlementId = settlementId;
	}

	public String getSettlementId() 
	{
		return settlementId;
	}
	public void setOrgiSettlementId(String orgiSettlementId) 
	{
		this.orgiSettlementId = orgiSettlementId;
	}

	public String getOrgiSettlementId() 
	{
		return orgiSettlementId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paySeqNo", getPaySeqNo())
            .append("paymentSeqNo", getPaymentSeqNo())
            .append("requestBatchNo", getRequestBatchNo())
            .append("orderType", getOrderType())
            .append("productCode", getProductCode())
            .append("paymentCode", getPaymentCode())
            .append("memberId", getMemberId())
            .append("amount", getAmount())
            .append("currency", getCurrency())
            .append("instCode", getInstCode())
            .append("paymentNotifyStatus", getPaymentNotifyStatus())
            .append("communicateType", getCommunicateType())
            .append("status", getStatus())
            .append("operator", getOperator())
            .append("gmtSubmit", getGmtSubmit())
            .append("instOrderId", getInstOrderId())
            .append("gmtCreate", getGmtCreate())
            .append("bizDate", getBizDate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("confirmStatus", getConfirmStatus())
            .append("payMode", getPayMode())
            .append("submitBatchNo", getSubmitBatchNo())
            .append("orgiPaymentSeqNo", getOrgiPaymentSeqNo())
            .append("expectTime", getExpectTime())
            .append("extension", getExtension())
            .append("settlementId", getSettlementId())
            .append("orgiSettlementId", getOrgiSettlementId())
            .toString();
    }
}
