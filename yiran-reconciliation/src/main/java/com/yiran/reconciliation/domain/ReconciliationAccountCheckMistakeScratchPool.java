package com.yiran.reconciliation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 差错暂存池表 reconciliation_account_check_mistake_scratch_pool
 * 
 * @author yiran
 * @date 2019-09-20
 */
public class ReconciliationAccountCheckMistakeScratchPool extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
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
	/** 商品名称 */
	private String productName;
	/** 商户订单号 */
	private String merchantOrderNo;
	/** 支付流水号 */
	private String trxNo;
	/** 银行订单号 */
	private String bankOrderNo;
	/** 银行流水号 */
	private String bankTrxNo;
	/** 订单金额 */
	private BigDecimal orderAmount;
	/** 平台收入 */
	private BigDecimal platIncome;
	/** 费率 */
	private BigDecimal feeRate;
	/** 平台成本 */
	private BigDecimal platCost;
	/** 平台利润 */
	private BigDecimal platProfit;
	/** 状态(参考枚举:paymentrecordstatusenum) */
	private String status;
	/** 支付通道编号 */
	private String fundChannelCode;
	/** 支付通道名称 */
	private String fundChannelName;
	/** 支付成功时间 */
	private Date paySuccessTime;
	/** 完成时间 */
	private Date completeTime;
	/** 是否退款(100:是,101:否,默认值为:101) */
	private String isRefund;
	/** 退款次数(默认值为:0) */
	private Integer refundTimes;
	/** 成功退款总金额 */
	private BigDecimal successRefundAmount;
	/** 备注 */
	private String remark;
	/** 对账批次号 */
	private String batchNo;
	/** 对账时间 */
	private Date billDate;

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
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}

	public String getProductName() 
	{
		return productName;
	}
	public void setMerchantOrderNo(String merchantOrderNo) 
	{
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getMerchantOrderNo() 
	{
		return merchantOrderNo;
	}
	public void setTrxNo(String trxNo) 
	{
		this.trxNo = trxNo;
	}

	public String getTrxNo() 
	{
		return trxNo;
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
	public void setOrderAmount(BigDecimal orderAmount) 
	{
		this.orderAmount = orderAmount;
	}

	public BigDecimal getOrderAmount() 
	{
		return orderAmount;
	}
	public void setPlatIncome(BigDecimal platIncome) 
	{
		this.platIncome = platIncome;
	}

	public BigDecimal getPlatIncome() 
	{
		return platIncome;
	}
	public void setFeeRate(BigDecimal feeRate) 
	{
		this.feeRate = feeRate;
	}

	public BigDecimal getFeeRate() 
	{
		return feeRate;
	}
	public void setPlatCost(BigDecimal platCost) 
	{
		this.platCost = platCost;
	}

	public BigDecimal getPlatCost() 
	{
		return platCost;
	}
	public void setPlatProfit(BigDecimal platProfit) 
	{
		this.platProfit = platProfit;
	}

	public BigDecimal getPlatProfit() 
	{
		return platProfit;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setFundChannelName(String fundChannelName) 
	{
		this.fundChannelName = fundChannelName;
	}

	public String getFundChannelName() 
	{
		return fundChannelName;
	}
	public void setPaySuccessTime(Date paySuccessTime) 
	{
		this.paySuccessTime = paySuccessTime;
	}

	public Date getPaySuccessTime() 
	{
		return paySuccessTime;
	}
	public void setCompleteTime(Date completeTime) 
	{
		this.completeTime = completeTime;
	}

	public Date getCompleteTime() 
	{
		return completeTime;
	}
	public void setIsRefund(String isRefund) 
	{
		this.isRefund = isRefund;
	}

	public String getIsRefund() 
	{
		return isRefund;
	}
	public void setRefundTimes(Integer refundTimes) 
	{
		this.refundTimes = refundTimes;
	}

	public Integer getRefundTimes() 
	{
		return refundTimes;
	}
	public void setSuccessRefundAmount(BigDecimal successRefundAmount) 
	{
		this.successRefundAmount = successRefundAmount;
	}

	public BigDecimal getSuccessRefundAmount() 
	{
		return successRefundAmount;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo() 
	{
		return batchNo;
	}
	public void setBillDate(Date billDate) 
	{
		this.billDate = billDate;
	}

	public Date getBillDate() 
	{
		return billDate;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("version", getVersion())
            .append("createTime", getCreateTime())
            .append("editor", getEditor())
            .append("creater", getCreater())
            .append("editTime", getEditTime())
            .append("productName", getProductName())
            .append("merchantOrderNo", getMerchantOrderNo())
            .append("trxNo", getTrxNo())
            .append("bankOrderNo", getBankOrderNo())
            .append("bankTrxNo", getBankTrxNo())
            .append("orderAmount", getOrderAmount())
            .append("platIncome", getPlatIncome())
            .append("feeRate", getFeeRate())
            .append("platCost", getPlatCost())
            .append("platProfit", getPlatProfit())
            .append("status", getStatus())
            .append("fundChannelCode", getFundChannelCode())
            .append("fundChannelName", getFundChannelName())
            .append("paySuccessTime", getPaySuccessTime())
            .append("completeTime", getCompleteTime())
            .append("isRefund", getIsRefund())
            .append("refundTimes", getRefundTimes())
            .append("successRefundAmount", getSuccessRefundAmount())
            .append("remark", getRemark())
            .append("batchNo", getBatchNo())
            .append("billDate", getBillDate())
            .toString();
    }
}
