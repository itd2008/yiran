package com.yiran.reconciliation.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 对账批次表 reconciliation_account_check_batch
 * 
 * @author yiran
 * @date 2019-09-20
 */
public class ReconciliationAccountCheckBatch implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private int id;
	/** version */
	private Integer version;
	/** 创建时间 */
	private Date createTime;
	/** 修改者 */
	private String editor;
	/** 创建者 */
	private String creater;
	/** 最后修改时间 */
	private Date editTime;
	/** 状态 ACTIVE 激活  UNACTIVE冻结 */
	private String status;
	/**  */
	private String remark;
	/** 对账批次号 */
	private String batchNo;
	/** 账单时间(账单交易发生时间) */
	private Date billDate;
	/** 账单类型 */
	private String billType;
	/** 批次处理状态, S已处理, A未处理 */
	private String handleStatus;
	/** 银行类型 */
	private String bankType;
	/** 所有差错总单数 */
	private Integer mistakeCount;
	/** 待处理的差错总单数 */
	private Integer unhandleMistakeCount;
	/** 平台总交易单数 */
	private Integer tradeCount;
	/** 银行总交易单数 */
	private Integer bankTradeCount;
	/** 平台交易总金额 */
	private BigDecimal tradeAmount;
	/** 银行交易总金额 */
	private BigDecimal bankTradeAmount;
	/** 平台退款总金额 */
	private BigDecimal refundAmount;
	/** 银行退款总金额 */
	private BigDecimal bankRefundAmount;
	/** 平台总手续费 */
	private BigDecimal bankFee;
	/** 原始对账文件存放地址 */
	private String orgCheckFilePath;
	/** 解析后文件存放地址 */
	private String releaseCheckFilePath;
	/** 解析状态 */
	private String releaseStatus;
	/** 解析检查失败的描述信息  */
	private String checkFailMsg;
	/** 银行返回的错误信息 */
	private String bankErrMsg;

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
	public void setBillType(String billType) 
	{
		this.billType = billType;
	}

	public String getBillType() 
	{
		return billType;
	}
	public void setHandleStatus(String handleStatus) 
	{
		this.handleStatus = handleStatus;
	}

	public String getHandleStatus() 
	{
		return handleStatus;
	}
	public void setBankType(String bankType) 
	{
		this.bankType = bankType;
	}

	public String getBankType() 
	{
		return bankType;
	}
	public void setMistakeCount(Integer mistakeCount) 
	{
		this.mistakeCount = mistakeCount;
	}

	public Integer getMistakeCount() 
	{
		return mistakeCount;
	}
	public void setUnhandleMistakeCount(Integer unhandleMistakeCount) 
	{
		this.unhandleMistakeCount = unhandleMistakeCount;
	}

	public Integer getUnhandleMistakeCount() 
	{
		return unhandleMistakeCount;
	}
	public void setTradeCount(Integer tradeCount) 
	{
		this.tradeCount = tradeCount;
	}

	public Integer getTradeCount() 
	{
		return tradeCount;
	}
	public void setBankTradeCount(Integer bankTradeCount) 
	{
		this.bankTradeCount = bankTradeCount;
	}

	public Integer getBankTradeCount() 
	{
		return bankTradeCount;
	}
	public void setTradeAmount(BigDecimal tradeAmount) 
	{
		this.tradeAmount = tradeAmount;
	}

	public BigDecimal getTradeAmount() 
	{
		return tradeAmount;
	}
	public void setBankTradeAmount(BigDecimal bankTradeAmount) 
	{
		this.bankTradeAmount = bankTradeAmount;
	}

	public BigDecimal getBankTradeAmount() 
	{
		return bankTradeAmount;
	}
	public void setRefundAmount(BigDecimal refundAmount) 
	{
		this.refundAmount = refundAmount;
	}

	public BigDecimal getRefundAmount() 
	{
		return refundAmount;
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
	public void setOrgCheckFilePath(String orgCheckFilePath) 
	{
		this.orgCheckFilePath = orgCheckFilePath;
	}

	public String getOrgCheckFilePath() 
	{
		return orgCheckFilePath;
	}
	public void setReleaseCheckFilePath(String releaseCheckFilePath) 
	{
		this.releaseCheckFilePath = releaseCheckFilePath;
	}

	public String getReleaseCheckFilePath() 
	{
		return releaseCheckFilePath;
	}
	public void setReleaseStatus(String releaseStatus) 
	{
		this.releaseStatus = releaseStatus;
	}

	public String getReleaseStatus() 
	{
		return releaseStatus;
	}
	public void setCheckFailMsg(String checkFailMsg) 
	{
		this.checkFailMsg = checkFailMsg;
	}

	public String getCheckFailMsg() 
	{
		return checkFailMsg;
	}
	public void setBankErrMsg(String bankErrMsg) 
	{
		this.bankErrMsg = bankErrMsg;
	}

	public String getBankErrMsg() 
	{
		return bankErrMsg;
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
            .append("batchNo", getBatchNo())
            .append("billDate", getBillDate())
            .append("billType", getBillType())
            .append("handleStatus", getHandleStatus())
            .append("bankType", getBankType())
            .append("mistakeCount", getMistakeCount())
            .append("unhandleMistakeCount", getUnhandleMistakeCount())
            .append("tradeCount", getTradeCount())
            .append("bankTradeCount", getBankTradeCount())
            .append("tradeAmount", getTradeAmount())
            .append("bankTradeAmount", getBankTradeAmount())
            .append("refundAmount", getRefundAmount())
            .append("bankRefundAmount", getBankRefundAmount())
            .append("bankFee", getBankFee())
            .append("orgCheckFilePath", getOrgCheckFilePath())
            .append("releaseCheckFilePath", getReleaseCheckFilePath())
            .append("releaseStatus", getReleaseStatus())
            .append("checkFailMsg", getCheckFailMsg())
            .append("bankErrMsg", getBankErrMsg())
            .toString();
    }
}
