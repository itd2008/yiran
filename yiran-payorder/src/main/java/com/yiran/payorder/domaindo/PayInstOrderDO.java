package com.yiran.payorder.domaindo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 提交机构订单表 pay_inst_order
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayInstOrderDO extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 机构订单ID */
	private Integer instOrderId;
	/** 机构编码 */
	private String instCode;
	/** 机构订单号 */
	private String instOrderNo;
	/** 订单类型：I（入款），B（退款），O（出款） */
	private String orderType;
	/** 币种 */
	private String currency;
	/** 金额 */
	private Double amount;
	/** 状态：I（处理中），S（成功），F（失败） */
	private String status;
	/** 通讯类型，S（单笔通信），B（批量通信） */
	private String communicateType;
	/** 通讯类型，类型为单笔时：A（等待指令发送），S（指令已经发送），R（数据已经返回），F（指令发送失败）批量时：A（等待数据归档），G（归档已经生成），S（归档数据已经提交），R（归档数据已经返回），F（归档数据提交失败 */
	private String communicateStatus;
	/** 归档批次ID */
	private Integer archiveBatchId;
	/** 提交时间 */
	private Date gmtSubmit;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 产品编号 */
	private String productCode;
	/** 支付code */
	private String paymentCode;
	/** 支付方式 */
	private String payMode;
	/** 渠道编号 */
	private String fundChannelCode;
	/** 渠道API */
	private String fundChannelApi;
	/** 归档模板ID */
	private Integer archiveTemplateId;
	/** 优先 */
	private Integer submitPriority;
	/** 预计提交时间 */
	private Date gmtBookingSubmit;
	/** 补单次数 */
	private Integer retryTimes;
	/** 下次补单时间 */
	private Date gmtNextRetry;
	/** 执行标志 */
	private String flag;
	/** I,等待审核结果;F,发送请求失败;P,审核通过;R,审核拒绝 */
	private String riskStatus;
	/** 路由版本 */
	private Integer routeVersion;
	/** 冲正状态 */
	private String reversalStatus;
	/** 渠道订单号 */
	private String paySeqNo;
	/** 是否拆分 */
	private String isSplit;
	/** 扩展信息 */
	private String extension;
	/** 期望到账时间  */
	private Date expectTime;
	/** 批量入款批量出款批次id */
	private Integer fundinOutBatchId;

	public void setInstOrderId(Integer instOrderId) 
	{
		this.instOrderId = instOrderId;
	}

	public Integer getInstOrderId() 
	{
		return instOrderId;
	}
	public void setInstCode(String instCode) 
	{
		this.instCode = instCode;
	}

	public String getInstCode() 
	{
		return instCode;
	}
	public void setInstOrderNo(String instOrderNo) 
	{
		this.instOrderNo = instOrderNo;
	}

	public String getInstOrderNo() 
	{
		return instOrderNo;
	}
	public void setOrderType(String orderType) 
	{
		this.orderType = orderType;
	}

	public String getOrderType() 
	{
		return orderType;
	}
	public void setCurrency(String currency) 
	{
		this.currency = currency;
	}

	public String getCurrency() 
	{
		return currency;
	}
	public void setAmount(Double amount) 
	{
		this.amount = amount;
	}

	public Double getAmount() 
	{
		return amount;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setCommunicateType(String communicateType) 
	{
		this.communicateType = communicateType;
	}

	public String getCommunicateType() 
	{
		return communicateType;
	}
	public void setCommunicateStatus(String communicateStatus) 
	{
		this.communicateStatus = communicateStatus;
	}

	public String getCommunicateStatus() 
	{
		return communicateStatus;
	}
	public void setArchiveBatchId(Integer archiveBatchId) 
	{
		this.archiveBatchId = archiveBatchId;
	}

	public Integer getArchiveBatchId() 
	{
		return archiveBatchId;
	}
	public void setGmtSubmit(Date gmtSubmit) 
	{
		this.gmtSubmit = gmtSubmit;
	}

	public Date getGmtSubmit() 
	{
		return gmtSubmit;
	}
	public void setGmtCreate(Date gmtCreate) 
	{
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() 
	{
		return gmtCreate;
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
	public void setPayMode(String payMode) 
	{
		this.payMode = payMode;
	}

	public String getPayMode() 
	{
		return payMode;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setFundChannelApi(String fundChannelApi) 
	{
		this.fundChannelApi = fundChannelApi;
	}

	public String getFundChannelApi() 
	{
		return fundChannelApi;
	}
	public void setArchiveTemplateId(Integer archiveTemplateId) 
	{
		this.archiveTemplateId = archiveTemplateId;
	}

	public Integer getArchiveTemplateId() 
	{
		return archiveTemplateId;
	}
	public void setSubmitPriority(Integer submitPriority) 
	{
		this.submitPriority = submitPriority;
	}

	public Integer getSubmitPriority() 
	{
		return submitPriority;
	}
	public void setGmtBookingSubmit(Date gmtBookingSubmit) 
	{
		this.gmtBookingSubmit = gmtBookingSubmit;
	}

	public Date getGmtBookingSubmit() 
	{
		return gmtBookingSubmit;
	}
	public void setRetryTimes(Integer retryTimes) 
	{
		this.retryTimes = retryTimes;
	}

	public Integer getRetryTimes() 
	{
		return retryTimes;
	}
	public void setGmtNextRetry(Date gmtNextRetry) 
	{
		this.gmtNextRetry = gmtNextRetry;
	}

	public Date getGmtNextRetry() 
	{
		return gmtNextRetry;
	}
	public void setFlag(String flag) 
	{
		this.flag = flag;
	}

	public String getFlag() 
	{
		return flag;
	}
	public void setRiskStatus(String riskStatus) 
	{
		this.riskStatus = riskStatus;
	}

	public String getRiskStatus() 
	{
		return riskStatus;
	}
	public void setRouteVersion(Integer routeVersion) 
	{
		this.routeVersion = routeVersion;
	}

	public Integer getRouteVersion() 
	{
		return routeVersion;
	}
	public void setReversalStatus(String reversalStatus) 
	{
		this.reversalStatus = reversalStatus;
	}

	public String getReversalStatus() 
	{
		return reversalStatus;
	}
	public void setPaySeqNo(String paySeqNo) 
	{
		this.paySeqNo = paySeqNo;
	}

	public String getPaySeqNo() 
	{
		return paySeqNo;
	}
	public void setIsSplit(String isSplit) 
	{
		this.isSplit = isSplit;
	}

	public String getIsSplit() 
	{
		return isSplit;
	}
	public void setExtension(String extension) 
	{
		this.extension = extension;
	}

	public String getExtension() 
	{
		return extension;
	}
	public void setExpectTime(Date expectTime) 
	{
		this.expectTime = expectTime;
	}

	public Date getExpectTime() 
	{
		return expectTime;
	}
	public void setFundinOutBatchId(Integer fundinOutBatchId) 
	{
		this.fundinOutBatchId = fundinOutBatchId;
	}

	public Integer getFundinOutBatchId() 
	{
		return fundinOutBatchId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("instOrderId", getInstOrderId())
            .append("instCode", getInstCode())
            .append("instOrderNo", getInstOrderNo())
            .append("orderType", getOrderType())
            .append("currency", getCurrency())
            .append("amount", getAmount())
            .append("status", getStatus())
            .append("communicateType", getCommunicateType())
            .append("communicateStatus", getCommunicateStatus())
            .append("archiveBatchId", getArchiveBatchId())
            .append("gmtSubmit", getGmtSubmit())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("productCode", getProductCode())
            .append("paymentCode", getPaymentCode())
            .append("payMode", getPayMode())
            .append("fundChannelCode", getFundChannelCode())
            .append("fundChannelApi", getFundChannelApi())
            .append("archiveTemplateId", getArchiveTemplateId())
            .append("submitPriority", getSubmitPriority())
            .append("gmtBookingSubmit", getGmtBookingSubmit())
            .append("retryTimes", getRetryTimes())
            .append("gmtNextRetry", getGmtNextRetry())
            .append("flag", getFlag())
            .append("riskStatus", getRiskStatus())
            .append("routeVersion", getRouteVersion())
            .append("reversalStatus", getReversalStatus())
            .append("paySeqNo", getPaySeqNo())
            .append("isSplit", getIsSplit())
            .append("extension", getExtension())
            .append("expectTime", getExpectTime())
            .append("fundinOutBatchId", getFundinOutBatchId())
            .toString();
    }
}
