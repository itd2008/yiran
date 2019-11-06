package com.yiran.payorder.domain;

import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.enums.YesNo;
import com.yiran.payorder.enums.CommunicateStatus;
import com.yiran.payorder.enums.InstOrderArchiveStatus;
import com.yiran.payorder.enums.InstOrderCommunicateType;
import com.yiran.payorder.enums.InstOrderReversalStatus;
import com.yiran.payorder.enums.OrderFlag;
import com.yiran.payorder.enums.OrderRiskStatus;

import java.util.Date;

/**
 * 提交机构订单表 pay_inst_order
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayInstOrder extends InstBaseOrder
{
	private static final long serialVersionUID = 1L;
	
	/** 机构订单ID */
	private Integer instOrderId;
	/** 订单类型 */
    protected BizType                  bizType;
    /** 支付方式**/
    protected PayMode                  payMode;
    /** 资金渠道API */
    private TmFundChannelApi             fundChannelApi;
    /** 支付编码 */
    protected String                   paymentCode;
    /** 补单次数 */
    private Integer                    retryTimes = Integer.valueOf(0);
    /** 下次补单时间 */
    private Date                       gmtNextRetry;
    /** 执行标志 */
    private OrderFlag                  flag;
    /** 货币类型 */
    protected CurrencyType             currency   = CurrencyType.CNY;
    /** 通讯类型 */
    protected InstOrderCommunicateType communicateType;
    /** 通讯状态 */
    protected CommunicateStatus        communicateStatus;
    /** 归档批次ID */
    protected Integer                     archiveBatchId;
    /** 归档状态 */
    protected InstOrderArchiveStatus   archiveStatus;
    /** 预计提交时间 */
    protected Date                     gmtBookingSubmit;
    /** 提交时间 */
    protected Date                     gmtSubmit;
    /** 风险订单状态 */
    private OrderRiskStatus            riskStatus;
    /** 归档模板ID */
    private Integer                       archiveTemplateId;
    /**路由版本 */
    private int                        routeVersion;
    /** 冲正状态 */
    private InstOrderReversalStatus    reversalStatus;
    /** cmf订单号 */
    private String                     paySeqNo;
    /** 是否拆分 */
    private YesNo                      isSplit;
    /** 期望到账时间   */
    private Date                       expectTime;
    
    /** 机构流水号 */
    private String                  instSeqNo;
    
    /** 支付流水号 */
	private String 					paymentSeqNo;

    /**
     * 克隆
     */
    public PayInstOrder clone() throws CloneNotSupportedException {
        return (PayInstOrder) super.clone();
    }

	public Integer getInstOrderId() {
		return instOrderId;
	}

	public void setInstOrderId(Integer instOrderId) {
		this.instOrderId = instOrderId;
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

	public TmFundChannelApi getFundChannelApi() {
		return fundChannelApi;
	}

	public void setFundChannelApi(TmFundChannelApi fundChannelApi) {
		this.fundChannelApi = fundChannelApi;
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

	public OrderFlag getFlag() {
		return flag;
	}

	public void setFlag(OrderFlag flag) {
		this.flag = flag;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}

	public InstOrderCommunicateType getCommunicateType() {
		return communicateType;
	}

	public void setCommunicateType(InstOrderCommunicateType communicateType) {
		this.communicateType = communicateType;
	}

	public CommunicateStatus getCommunicateStatus() {
		return communicateStatus;
	}

	public void setCommunicateStatus(CommunicateStatus communicateStatus) {
		this.communicateStatus = communicateStatus;
	}

	public Integer getArchiveBatchId() {
		return archiveBatchId;
	}

	public void setArchiveBatchId(Integer archiveBatchId) {
		this.archiveBatchId = archiveBatchId;
	}

	public InstOrderArchiveStatus getArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(InstOrderArchiveStatus archiveStatus) {
		this.archiveStatus = archiveStatus;
	}

	public Date getGmtBookingSubmit() {
		return gmtBookingSubmit;
	}

	public void setGmtBookingSubmit(Date gmtBookingSubmit) {
		this.gmtBookingSubmit = gmtBookingSubmit;
	}

	public Date getGmtSubmit() {
		return gmtSubmit;
	}

	public void setGmtSubmit(Date gmtSubmit) {
		this.gmtSubmit = gmtSubmit;
	}

	public OrderRiskStatus getRiskStatus() {
		return riskStatus;
	}

	public void setRiskStatus(OrderRiskStatus riskStatus) {
		this.riskStatus = riskStatus;
	}

	public Integer getArchiveTemplateId() {
		return archiveTemplateId;
	}

	public void setArchiveTemplateId(Integer archiveTemplateId) {
		this.archiveTemplateId = archiveTemplateId;
	}

	public int getRouteVersion() {
		return routeVersion;
	}

	public void setRouteVersion(int routeVersion) {
		this.routeVersion = routeVersion;
	}

	public InstOrderReversalStatus getReversalStatus() {
		return reversalStatus;
	}

	public void setReversalStatus(InstOrderReversalStatus reversalStatus) {
		this.reversalStatus = reversalStatus;
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

	public String getInstSeqNo() {
		return instSeqNo;
	}

	public void setInstSeqNo(String instSeqNo) {
		this.instSeqNo = instSeqNo;
	}

	public String getPaymentSeqNo() {
		return paymentSeqNo;
	}

	public void setPaymentSeqNo(String paymentSeqNo) {
		this.paymentSeqNo = paymentSeqNo;
	}
	
    
}
