package com.yiran.payorder.domain;

import java.io.Serializable;
import java.util.Date;

import com.netfinworks.common.domain.Extension;
import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.enums.YesNo;

/**
 * <p>提交到机构订单</p>
 */
public class OrderVO  implements Serializable {
    
	private static final long serialVersionUID = -7612156145936986435L;

	/** 机构订单ID */
    protected Integer instOrderId;

    /** 机构订单号 */
    protected String instOrderNo;
    /** 订单类型 */
    protected BizType bizType;
    /** 支付方式**/
    protected PayMode payMode;
    /** 机构编码 目标机构 */
    protected String instCode;

    /** 资金源ID */
    protected String fundChannelCode;

    private String   apiCode;

    /** 产品码 */
    protected String productCode;
    /** 支付编码 */
    protected String paymentCode;
    /** 补单次数 */
    private Integer retryTimes;
    /** 下次补单时间 */
    private Date gmtNextRetry;


    /** 金额 */
    protected Money amount = new Money(0.0);
    /** 货币类型 */
    protected CurrencyType currency = CurrencyType.CNY;
    /** 状态 */
    protected String status;
    /** 通讯状态 */
    protected String communicateStatus;
    /** 归档批次ID */
    protected Long archiveBatchId;
    /** 提交时间 */
    protected Date gmtSubmit;
    /** 创建时间 */
    protected Date gmtCreate;
    /** 最后修改时间 */
    protected Date gmtModified;
    /**备注 */
    protected String memo;

    /** 风险订单状态 */
    private String riskStatus;

    private Long archiveTemplateId;

    /**路由版本 */
    private int routeVersion;
    
    private String reversalStatus;

    /** 渠道对象 */
    protected Extension extension = new Extension();

    /** cmf订单号 */
    private String paySeqNo;
    
    /** 是否拆分 */
    private YesNo isSplit;

    /** 期望到账时间   */
    private Date expectTime;

	public Integer getInstOrderId() {
		return instOrderId;
	}

	public void setInstOrderId(Integer instOrderId) {
		this.instOrderId = instOrderId;
	}

	public String getInstOrderNo() {
		return instOrderNo;
	}

	public void setInstOrderNo(String instOrderNo) {
		this.instOrderNo = instOrderNo;
	}

	public BizType getBizType() {
		return bizType;
	}

	public void setBizType(BizType bizType) {
		this.bizType = bizType;
	}

	public PayMode getPayMode() {
		return payMode;
	}

	public void setPayMode(PayMode payMode) {
		this.payMode = payMode;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public String getFundChannelCode() {
		return fundChannelCode;
	}

	public void setFundChannelCode(String fundChannelCode) {
		this.fundChannelCode = fundChannelCode;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public Integer getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}

	public Date getGmtNextRetry() {
		return gmtNextRetry;
	}

	public void setGmtNextRetry(Date gmtNextRetry) {
		this.gmtNextRetry = gmtNextRetry;
	}

	public Money getAmount() {
		return amount;
	}

	public void setAmount(Money amount) {
		this.amount = amount;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommunicateStatus() {
		return communicateStatus;
	}

	public void setCommunicateStatus(String communicateStatus) {
		this.communicateStatus = communicateStatus;
	}

	public Long getArchiveBatchId() {
		return archiveBatchId;
	}

	public void setArchiveBatchId(Long archiveBatchId) {
		this.archiveBatchId = archiveBatchId;
	}

	public Date getGmtSubmit() {
		return gmtSubmit;
	}

	public void setGmtSubmit(Date gmtSubmit) {
		this.gmtSubmit = gmtSubmit;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRiskStatus() {
		return riskStatus;
	}

	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}

	public Long getArchiveTemplateId() {
		return archiveTemplateId;
	}

	public void setArchiveTemplateId(Long archiveTemplateId) {
		this.archiveTemplateId = archiveTemplateId;
	}

	public int getRouteVersion() {
		return routeVersion;
	}

	public void setRouteVersion(int routeVersion) {
		this.routeVersion = routeVersion;
	}

	public String getReversalStatus() {
		return reversalStatus;
	}

	public void setReversalStatus(String reversalStatus) {
		this.reversalStatus = reversalStatus;
	}

	public Extension getExtension() {
		return extension;
	}

	public void setExtension(Extension extension) {
		this.extension = extension;
	}

	public String getPaySeqNo() {
		return paySeqNo;
	}

	public void setPaySeqNo(String paySeqNo) {
		this.paySeqNo = paySeqNo;
	}

	public YesNo getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(YesNo isSplit) {
		this.isSplit = isSplit;
	}

	public Date getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Date expectTime) {
		this.expectTime = expectTime;
	}

    
    
}
