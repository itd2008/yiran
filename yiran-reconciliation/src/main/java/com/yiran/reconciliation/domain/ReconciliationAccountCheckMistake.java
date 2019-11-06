package com.yiran.reconciliation.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 对账差错表 reconciliation_account_check_mistake
 * 
 * @author yiran
 * @date 2019-09-20
 */
public class ReconciliationAccountCheckMistake implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 编码 */
	private int id;
	/** 版本 */
	private Integer version;
	/** 创建时间 */
	private Date createTime;
	/** 修改者 */
	private String editor;
	/** 创建者 */
	private String creater;
	/** 最后修改时间 */
	private Date editTime;
	/** 状态 */
	private String status;
	/** 备注 */
	private String remark;
	/** 对账批次号 */
	private String accountCheckBatchNo;
	/** 账单日期 */
	private Date billDate;
	/** 银行类型 */
	private String bankType;
	/** 下单时间  */
	private Date orderTime;
	/** merchantName */
	private String merchantName;
	/** 商家编号 */
	private String merchantNo;
	/** 商家订单号 */
	private String orderNo;
	/** 平台交易时间  */
	private Date tradeTime;
	/** 平台流水号 */
	private String trxNo;
	/** 平台交易金额 */
	private BigDecimal orderAmount;
	/** 平台退款金额  */
	private BigDecimal refundAmount;
	/** 平台交易状态 */
	private String tradeStatus;
	/** 平台手续费 */
	private BigDecimal fee;
	/** 银行交易时间 */
	private Date bankTradeTime;
	/** 银行订单号 */
	private String bankOrderNo;
	/** 银行流水号 */
	private String bankTrxNo;
	/** 银行交易状态 */
	private String bankTradeStatus;
	/** 银行交易金额 */
	private BigDecimal bankAmount;
	/** 银行退款金额 */
	private BigDecimal bankRefundAmount;
	/** 银行手续费 */
	private BigDecimal bankFee;
	/** 差错类型 */
	private String errType;
	/** handleStatus */
	private String handleStatus;
	/** 处理结果 */
	private String handleValue;
	/** 处理备注 */
	private String handleRemark;
	/** 操作人 */
	private String operatorName;
	/** 操作账户 */
	private String operatorAccountNo;

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getId() 
	{
		return id;
	}
	public void setVersion(Integer version) 
	{
		this.version = version;
	}

	public Integer getVersion() 
	{
		return version;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setEditor(String editor) 
	{
		this.editor = editor;
	}

	public String getEditor() 
	{
		return editor;
	}
	public void setCreater(String creater) 
	{
		this.creater = creater;
	}

	public String getCreater() 
	{
		return creater;
	}
	public void setEditTime(Date editTime) 
	{
		this.editTime = editTime;
	}

	public Date getEditTime() 
	{
		return editTime;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setAccountCheckBatchNo(String accountCheckBatchNo) 
	{
		this.accountCheckBatchNo = accountCheckBatchNo;
	}

	public String getAccountCheckBatchNo() 
	{
		return accountCheckBatchNo;
	}
	public void setBillDate(Date billDate) 
	{
		this.billDate = billDate;
	}

	public Date getBillDate() 
	{
		return billDate;
	}
	public void setBankType(String bankType) 
	{
		this.bankType = bankType;
	}

	public String getBankType() 
	{
		return bankType;
	}
	public void setOrderTime(Date orderTime) 
	{
		this.orderTime = orderTime;
	}

	public Date getOrderTime() 
	{
		return orderTime;
	}
	public void setMerchantName(String merchantName) 
	{
		this.merchantName = merchantName;
	}

	public String getMerchantName() 
	{
		return merchantName;
	}
	public void setMerchantNo(String merchantNo) 
	{
		this.merchantNo = merchantNo;
	}

	public String getMerchantNo() 
	{
		return merchantNo;
	}
	public void setOrderNo(String orderNo) 
	{
		this.orderNo = orderNo;
	}

	public String getOrderNo() 
	{
		return orderNo;
	}
	public void setTradeTime(Date tradeTime) 
	{
		this.tradeTime = tradeTime;
	}

	public Date getTradeTime() 
	{
		return tradeTime;
	}
	public void setTrxNo(String trxNo) 
	{
		this.trxNo = trxNo;
	}

	public String getTrxNo() 
	{
		return trxNo;
	}
	public void setOrderAmount(BigDecimal orderAmount) 
	{
		this.orderAmount = orderAmount;
	}

	public BigDecimal getOrderAmount() 
	{
		return orderAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) 
	{
		this.refundAmount = refundAmount;
	}

	public BigDecimal getRefundAmount() 
	{
		return refundAmount;
	}
	public void setTradeStatus(String tradeStatus) 
	{
		this.tradeStatus = tradeStatus;
	}

	public String getTradeStatus() 
	{
		return tradeStatus;
	}
	public void setFee(BigDecimal fee) 
	{
		this.fee = fee;
	}

	public BigDecimal getFee() 
	{
		return fee;
	}
	public void setBankTradeTime(Date bankTradeTime) 
	{
		this.bankTradeTime = bankTradeTime;
	}

	public Date getBankTradeTime() 
	{
		return bankTradeTime;
	}
	public void setBankOrderNo(String bankOrderNo) 
	{
		this.bankOrderNo = bankOrderNo;
	}

	public String getBankOrderNo() 
	{
		return bankOrderNo;
	}
	public void setBankTrxNo(String bankTrxNo) 
	{
		this.bankTrxNo = bankTrxNo;
	}

	public String getBankTrxNo() 
	{
		return bankTrxNo;
	}
	public void setBankTradeStatus(String bankTradeStatus) 
	{
		this.bankTradeStatus = bankTradeStatus;
	}

	public String getBankTradeStatus() 
	{
		return bankTradeStatus;
	}
	public void setBankAmount(BigDecimal bankAmount) 
	{
		this.bankAmount = bankAmount;
	}

	public BigDecimal getBankAmount() 
	{
		return bankAmount;
	}
	public void setBankRefundAmount(BigDecimal bankRefundAmount) 
	{
		this.bankRefundAmount = bankRefundAmount;
	}

	public BigDecimal getBankRefundAmount() 
	{
		return bankRefundAmount;
	}
	public void setBankFee(BigDecimal bankFee) 
	{
		this.bankFee = bankFee;
	}

	public BigDecimal getBankFee() 
	{
		return bankFee;
	}
	public void setErrType(String errType) 
	{
		this.errType = errType;
	}

	public String getErrType() 
	{
		return errType;
	}
	public void setHandleStatus(String handleStatus) 
	{
		this.handleStatus = handleStatus;
	}

	public String getHandleStatus() 
	{
		return handleStatus;
	}
	public void setHandleValue(String handleValue) 
	{
		this.handleValue = handleValue;
	}

	public String getHandleValue() 
	{
		return handleValue;
	}
	public void setHandleRemark(String handleRemark) 
	{
		this.handleRemark = handleRemark;
	}

	public String getHandleRemark() 
	{
		return handleRemark;
	}
	public void setOperatorName(String operatorName) 
	{
		this.operatorName = operatorName;
	}

	public String getOperatorName() 
	{
		return operatorName;
	}
	public void setOperatorAccountNo(String operatorAccountNo) 
	{
		this.operatorAccountNo = operatorAccountNo;
	}

	public String getOperatorAccountNo() 
	{
		return operatorAccountNo;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("version", getVersion())
            .append("createTime", getCreateTime())
            .append("editor", getEditor())
            .append("creater", getCreater())
            .append("editTime", getEditTime())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("accountCheckBatchNo", getAccountCheckBatchNo())
            .append("billDate", getBillDate())
            .append("bankType", getBankType())
            .append("orderTime", getOrderTime())
            .append("merchantName", getMerchantName())
            .append("merchantNo", getMerchantNo())
            .append("orderNo", getOrderNo())
            .append("tradeTime", getTradeTime())
            .append("trxNo", getTrxNo())
            .append("orderAmount", getOrderAmount())
            .append("refundAmount", getRefundAmount())
            .append("tradeStatus", getTradeStatus())
            .append("fee", getFee())
            .append("bankTradeTime", getBankTradeTime())
            .append("bankOrderNo", getBankOrderNo())
            .append("bankTrxNo", getBankTrxNo())
            .append("bankTradeStatus", getBankTradeStatus())
            .append("bankAmount", getBankAmount())
            .append("bankRefundAmount", getBankRefundAmount())
            .append("bankFee", getBankFee())
            .append("errType", getErrType())
            .append("handleStatus", getHandleStatus())
            .append("handleValue", getHandleValue())
            .append("handleRemark", getHandleRemark())
            .append("operatorName", getOperatorName())
            .append("operatorAccountNo", getOperatorAccountNo())
            .toString();
    }
}
