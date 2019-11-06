package com.yiran.payorder.domain;

import com.netfinworks.common.util.money.Money;
import com.yiran.common.base.BaseEntity;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.enums.RequestType;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.enums.NotifyStatus;
import com.yiran.payorder.enums.PayOrderConfirmStatus;
import com.yiran.payorder.enums.PayOrderStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 渠道支付订单表 channel_pay_order
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class ChannelPayOrder implements BasicConstant
{
	private static final long serialVersionUID = 1L;
	/** 资金渠道，兼容用 */
    private String  fundChannelCode;
	/** 渠道流水号  yyyymmdd+6位随机数*/
	private String paySeqNo;
	/** 支付流水号 */
	private String paymentSeqNo;
	/** 请求类型 */
    private RequestType  requestType;
	/** 业务类型 */
    private BizType bizType;
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
	private Money                 amount    = new Money(ZERO_MONEY_STRING);
	/** 币种 */
	private CurrencyType          currency  = CurrencyType.CNY;
	/** 机构编码 */
	private String instCode;
	/** 支付结果通知状态：S（通知成功），F（通知失败），N（不通知） */
	private NotifyStatus paymentNotifyStatus;
	/** 通信类型，S（单笔通信），B（批量通信） */
	private String communicateType;
	/** 状态：A（待处理），C（已撤销），I（处理中），S（成功），F（失败） */
	private PayOrderStatus status;
	/** 操作员，默认SYSTEM */
	private String operator;
	/** 提交时间 */
	private Date gmtSubmit;
	/** 机构订单ID */
	private Integer instOrderId;
	/** 创建时间 */
	private Date gmtCreate;
	/** 业务发生时间 */
	private Date bizDate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 提交状态 */
	private PayOrderConfirmStatus confirmStatus;
	/** 支付方式 */
	private PayMode payMode;
	/** 批次号 */
	private String submitBatchNo;
	/** 原始订单号 */
	private String orgiPaymentSeqNo;
	/** 期望时间 */
	private Date expectTime;
	/** 扩展信息 */
    private Map<String, String>   extension = new HashMap<String, String>();
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
	public void setAmount(Money amount) 
	{
		this.amount = amount;
	}

	public Money getAmount() 
	{
		return amount;
	}
	public void setCurrency(CurrencyType currency) 
	{
		this.currency = currency;
	}

	public CurrencyType getCurrency() 
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
	public void setPaymentNotifyStatus(NotifyStatus paymentNotifyStatus) 
	{
		this.paymentNotifyStatus = paymentNotifyStatus;
	}

	public NotifyStatus getPaymentNotifyStatus() 
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
	public void setStatus(PayOrderStatus status) 
	{
		this.status = status;
	}

	public PayOrderStatus getStatus() 
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
	public void setBizDate(Date bizDate) 
	{
		this.bizDate = bizDate;
	}

	public Date getBizDate() 
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
	public void setConfirmStatus(PayOrderConfirmStatus confirmStatus) 
	{
		this.confirmStatus = confirmStatus;
	}

	public PayOrderConfirmStatus getConfirmStatus() 
	{
		return confirmStatus;
	}
	public void setPayMode(PayMode payMode) 
	{
		this.payMode = payMode;
	}

	public PayMode getPayMode() 
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
	public void setExtension(Map<String, String> extension) 
	{
		this.extension = extension;
	}

	public Map<String, String> getExtension() 
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

    public BizType getBizType() {
		return bizType;
	}

	public void setBizType(BizType bizType) {
		this.bizType = bizType;
	}

	public String getFundChannelCode() {
		return fundChannelCode;
	}

	public void setFundChannelCode(String fundChannelCode) {
		this.fundChannelCode = fundChannelCode;
	}
	
	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fundChannelCode", getFundChannelCode())
            .append("paySeqNo", getPaySeqNo())
            .append("paymentSeqNo", getPaymentSeqNo())
            .append("requestBatchNo", getRequestBatchNo())
            .append("orderType", getOrderType())
            .append("bizType", getBizType().getCode())
            .append("requestType", getRequestType().getCode())
            .append("productCode", getProductCode())
            .append("paymentCode", getPaymentCode())
            .append("memberId", getMemberId())
            .append("amount", getAmount())
            .append("currency", getCurrency().getCode())
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
            .append("confirmStatus", getConfirmStatus().getCode())
            .append("payMode", getPayMode().getCode())
            .append("submitBatchNo", getSubmitBatchNo())
            .append("orgiPaymentSeqNo", getOrgiPaymentSeqNo())
            .append("expectTime", getExpectTime())
            .append("extension", getExtension())
            .append("settlementId", getSettlementId())
            .append("orgiSettlementId", getOrgiSettlementId())
            .toString();
    }
}
