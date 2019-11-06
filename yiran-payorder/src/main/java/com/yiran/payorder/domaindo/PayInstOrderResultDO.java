package com.yiran.payorder.domaindo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 机构订单结果表 pay_inst_order_result
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayInstOrderResultDO extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 订单结果ID */
	private Integer resultId;
	/** 批次结果ID */
	private Integer batchResultId;
	/** 机构订单ID */
	private Integer instOrderId;
	/** 机构流水号 */
	private String instSeqNo;
	/** 订单类型 */
	private String orderType;
	/** 实际金额 */
	private Double realAmount;
	/** 实际币种 */
	private String realCurrency;
	/** 帐户名称 */
	private String accountName;
	/** 帐户帐号 */
	private String accountNo;
	/** 卡类型 */
	private String cardType;
	/** 原机构订单号 */
	private String orgiInstOrderNo;
	/** 对账状态  */
	private String compareStatus;
	/** 状态 */
	private String instStatus;
	/** 流水同步状态 */
	private String glideStatus;
	/** 统一结果编码 */
	private String instResultCode;
	/** 修改时间 */
	private Date gmtModified;
	/** 创建时间 */
	private Date gmtCreate;
	/** 备注 */
	private String memo;
	/** 批次类型 */
	private String batchType;
	/** 渠道编号 */
	private String fundChannelCode;
	/** 机构订单号 */
	private String instOrderNo;
	/** 该笔订单结果操作状态 */
	private String operateStatus;
	/** 差异信息 */
	private String diffMsg;
	/** 归档批次ID */
	private Integer archiveBatchId;
	/** 原入款订单号 */
	private String fundinOrgiInstOrderNo;
	/** 同步结果给渠道.S发送成功,F发送失败,I发送中 */
	private String syncChannelStatus;
	/** 通知BankOrder状态  */
	private String notifyBankorderStatus;
	/** 渠道API结果码 */
	private String apiResultCode;
	/** 渠道API结果子码 */
	private String apiResultSubCode;
	/** 接口类型 */
	private String apiType;
	/** 风险标识 */
	private String riskFlag;
	/** 扩展信息 */
	private String extension;

	public void setResultId(Integer resultId) 
	{
		this.resultId = resultId;
	}

	public Integer getResultId() 
	{
		return resultId;
	}
	public void setBatchResultId(Integer batchResultId) 
	{
		this.batchResultId = batchResultId;
	}

	public Integer getBatchResultId() 
	{
		return batchResultId;
	}
	public void setInstOrderId(Integer instOrderId) 
	{
		this.instOrderId = instOrderId;
	}

	public Integer getInstOrderId() 
	{
		return instOrderId;
	}
	public void setInstSeqNo(String instSeqNo) 
	{
		this.instSeqNo = instSeqNo;
	}

	public String getInstSeqNo() 
	{
		return instSeqNo;
	}
	public void setOrderType(String orderType) 
	{
		this.orderType = orderType;
	}

	public String getOrderType() 
	{
		return orderType;
	}
	public void setRealAmount(Double realAmount) 
	{
		this.realAmount = realAmount;
	}

	public Double getRealAmount() 
	{
		return realAmount;
	}
	public void setRealCurrency(String realCurrency) 
	{
		this.realCurrency = realCurrency;
	}

	public String getRealCurrency() 
	{
		return realCurrency;
	}
	public void setAccountName(String accountName) 
	{
		this.accountName = accountName;
	}

	public String getAccountName() 
	{
		return accountName;
	}
	public void setAccountNo(String accountNo) 
	{
		this.accountNo = accountNo;
	}

	public String getAccountNo() 
	{
		return accountNo;
	}
	public void setCardType(String cardType) 
	{
		this.cardType = cardType;
	}

	public String getCardType() 
	{
		return cardType;
	}
	public void setOrgiInstOrderNo(String orgiInstOrderNo) 
	{
		this.orgiInstOrderNo = orgiInstOrderNo;
	}

	public String getOrgiInstOrderNo() 
	{
		return orgiInstOrderNo;
	}
	public void setCompareStatus(String compareStatus) 
	{
		this.compareStatus = compareStatus;
	}

	public String getCompareStatus() 
	{
		return compareStatus;
	}
	public void setInstStatus(String instStatus) 
	{
		this.instStatus = instStatus;
	}

	public String getInstStatus() 
	{
		return instStatus;
	}
	public void setGlideStatus(String glideStatus) 
	{
		this.glideStatus = glideStatus;
	}

	public String getGlideStatus() 
	{
		return glideStatus;
	}
	public void setInstResultCode(String instResultCode) 
	{
		this.instResultCode = instResultCode;
	}

	public String getInstResultCode() 
	{
		return instResultCode;
	}
	public void setGmtModified(Date gmtModified) 
	{
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() 
	{
		return gmtModified;
	}
	public void setGmtCreate(Date gmtCreate) 
	{
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() 
	{
		return gmtCreate;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setBatchType(String batchType) 
	{
		this.batchType = batchType;
	}

	public String getBatchType() 
	{
		return batchType;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setInstOrderNo(String instOrderNo) 
	{
		this.instOrderNo = instOrderNo;
	}

	public String getInstOrderNo() 
	{
		return instOrderNo;
	}
	public void setOperateStatus(String operateStatus) 
	{
		this.operateStatus = operateStatus;
	}

	public String getOperateStatus() 
	{
		return operateStatus;
	}
	public void setDiffMsg(String diffMsg) 
	{
		this.diffMsg = diffMsg;
	}

	public String getDiffMsg() 
	{
		return diffMsg;
	}
	public void setArchiveBatchId(Integer archiveBatchId) 
	{
		this.archiveBatchId = archiveBatchId;
	}

	public Integer getArchiveBatchId() 
	{
		return archiveBatchId;
	}
	public void setFundinOrgiInstOrderNo(String fundinOrgiInstOrderNo) 
	{
		this.fundinOrgiInstOrderNo = fundinOrgiInstOrderNo;
	}

	public String getFundinOrgiInstOrderNo() 
	{
		return fundinOrgiInstOrderNo;
	}
	public void setSyncChannelStatus(String syncChannelStatus) 
	{
		this.syncChannelStatus = syncChannelStatus;
	}

	public String getSyncChannelStatus() 
	{
		return syncChannelStatus;
	}
	public void setNotifyBankorderStatus(String notifyBankorderStatus) 
	{
		this.notifyBankorderStatus = notifyBankorderStatus;
	}

	public String getNotifyBankorderStatus() 
	{
		return notifyBankorderStatus;
	}
	public void setApiResultCode(String apiResultCode) 
	{
		this.apiResultCode = apiResultCode;
	}

	public String getApiResultCode() 
	{
		return apiResultCode;
	}
	public void setApiResultSubCode(String apiResultSubCode) 
	{
		this.apiResultSubCode = apiResultSubCode;
	}

	public String getApiResultSubCode() 
	{
		return apiResultSubCode;
	}
	public void setApiType(String apiType) 
	{
		this.apiType = apiType;
	}

	public String getApiType() 
	{
		return apiType;
	}
	public void setRiskFlag(String riskFlag) 
	{
		this.riskFlag = riskFlag;
	}

	public String getRiskFlag() 
	{
		return riskFlag;
	}
	public void setExtension(String extension) 
	{
		this.extension = extension;
	}

	public String getExtension() 
	{
		return extension;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resultId", getResultId())
            .append("batchResultId", getBatchResultId())
            .append("instOrderId", getInstOrderId())
            .append("instSeqNo", getInstSeqNo())
            .append("orderType", getOrderType())
            .append("realAmount", getRealAmount())
            .append("realCurrency", getRealCurrency())
            .append("accountName", getAccountName())
            .append("accountNo", getAccountNo())
            .append("cardType", getCardType())
            .append("orgiInstOrderNo", getOrgiInstOrderNo())
            .append("compareStatus", getCompareStatus())
            .append("instStatus", getInstStatus())
            .append("glideStatus", getGlideStatus())
            .append("instResultCode", getInstResultCode())
            .append("gmtModified", getGmtModified())
            .append("gmtCreate", getGmtCreate())
            .append("memo", getMemo())
            .append("batchType", getBatchType())
            .append("fundChannelCode", getFundChannelCode())
            .append("instOrderNo", getInstOrderNo())
            .append("operateStatus", getOperateStatus())
            .append("diffMsg", getDiffMsg())
            .append("archiveBatchId", getArchiveBatchId())
            .append("fundinOrgiInstOrderNo", getFundinOrgiInstOrderNo())
            .append("syncChannelStatus", getSyncChannelStatus())
            .append("notifyBankorderStatus", getNotifyBankorderStatus())
            .append("apiResultCode", getApiResultCode())
            .append("apiResultSubCode", getApiResultSubCode())
            .append("apiType", getApiType())
            .append("riskFlag", getRiskFlag())
            .append("extension", getExtension())
            .toString();
    }
}
