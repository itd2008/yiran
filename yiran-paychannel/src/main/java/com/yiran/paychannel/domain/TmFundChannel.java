package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.Status;
import com.yiran.paychannel.service.ChannelProperty;

import java.util.Date;
import java.util.List;

/**
 * 资金渠道表 tm_fund_channel
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmFundChannel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 渠道编码 */
	private String fundChannelCode;
	/** 渠道名称 */
	private String name;
	/** 描述 */
	private String description;
	/** 机构代码 */
	private String instCode;
	/** 业务类型：I（入款），O（出款） */
	private String bizType;
	/** 支付方式，如：网银，卡通，线下，手机充值卡，手机话费，微汇卡，积分。。。 */
	private String payMode;
	/** 签约公司：Y（益充），S（微汇） */
	private String signedCrop;
	/** 是否可用：VALID（可用），INVALID（不可用） */
	private String status;
	/** 启用时间 */
	private Date validFrom;
	/** 过期时间 */
	private Date validTo;
	/** 单笔最大金额 */
	private String maxAmount;
	/** 单笔最小金额 */
	private String minAmount;
	/** T+15M表示15分钟到帐，T+1D表示一天到帐，H小时 */
	private String expectArriveTime;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 渠道类型(网银01-07，出款01-04) */
	private String memo;
	/** 主键ID */
	private String channelMode;
	/** 此优先级在过滤，优选之后使用 */
	private Integer priority;
	/**
	 * API接口
	 */
	List<TmFundChannelApi> apiList;
	/**
	 * 渠道特性
	 */
	List<TmFundChannelExt> extList;
	/**
	 * 目标机构
	 */
	List<TrFcTargetInstRelation> instList;
	/**
	 * 维护期
	 */
	List<TmFundChannelMaintain> maintainList;
	
	/** 所属机构 */
    private TmFundChannelInst             institution;

    /** 结算信息 */
    private TmFundChannelSettle           settleInfo;
    
    /** 是否支持查询 */
    private boolean                     querySupported        = false;
    /** 是否支持退款 */
    private boolean                     refundSupported       = false;
    /** 是否支持手工退款 */
    private boolean                     manualRefundSupported = false;
    
    /** 渠道扩展属性 */
    private ChannelProperty             channelProperty;
    
    public TmFundChannelApi getApiByType(FundChannelApiType apiType) {
        if (null == this.apiList) {
            return null;
        }

        for (TmFundChannelApi api : getApiList()) {
            if (apiType.getCode().equals(api.getApiType())) {
                return api;
            }
        }
        return null;
    }
    
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getDescription() 
	{
		return description;
	}
	public void setInstCode(String instCode) 
	{
		this.instCode = instCode;
	}

	public String getInstCode() 
	{
		return instCode;
	}
	public void setBizType(String bizType) 
	{
		this.bizType = bizType;
	}

	public String getBizType() 
	{
		return bizType;
	}
	public void setPayMode(String payMode) 
	{
		this.payMode = payMode;
	}

	public String getPayMode() 
	{
		return payMode;
	}
	public void setSignedCrop(String signedCrop) 
	{
		this.signedCrop = signedCrop;
	}

	public String getSignedCrop() 
	{
		return signedCrop;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setValidFrom(Date validFrom) 
	{
		this.validFrom = validFrom;
	}

	public Date getValidFrom() 
	{
		return validFrom;
	}
	public void setValidTo(Date validTo) 
	{
		this.validTo = validTo;
	}

	public Date getValidTo() 
	{
		return validTo;
	}
	public void setMaxAmount(String maxAmount) 
	{
		this.maxAmount = maxAmount;
	}

	public String getMaxAmount() 
	{
		return maxAmount;
	}
	public void setMinAmount(String minAmount) 
	{
		this.minAmount = minAmount;
	}

	public String getMinAmount() 
	{
		return minAmount;
	}
	public void setExpectArriveTime(String expectArriveTime) 
	{
		this.expectArriveTime = expectArriveTime;
	}

	public String getExpectArriveTime() 
	{
		return expectArriveTime;
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
	public void setChannelMode(String channelMode) 
	{
		this.channelMode = channelMode;
	}

	public String getChannelMode() 
	{
		return channelMode;
	}
	public void setPriority(Integer priority) 
	{
		this.priority = priority;
	}

	public Integer getPriority() 
	{
		return priority;
	}
	

    public List<TmFundChannelApi> getApiList() {
		return apiList;
	}

	public void setApiList(List<TmFundChannelApi> apiList) {
		this.apiList = apiList;
	}

	public List<TmFundChannelExt> getExtList() {
		return extList;
	}

	public void setExtList(List<TmFundChannelExt> extList) {
		this.extList = extList;
	}

	public List<TrFcTargetInstRelation> getInstList() {
		return instList;
	}

	public void setInstList(List<TrFcTargetInstRelation> instList) {
		this.instList = instList;
	}

	public List<TmFundChannelMaintain> getMaintainList() {
		return maintainList;
	}

	public void setMaintainList(List<TmFundChannelMaintain> maintainList) {
		this.maintainList = maintainList;
	}
	

	public TmFundChannelInst getInstitution() {
		return institution;
	}

	public void setInstitution(TmFundChannelInst institution) {
		this.institution = institution;
	}

	
	public TmFundChannelSettle getSettleInfo() {
		return settleInfo;
	}

	public void setSettleInfo(TmFundChannelSettle settleInfo) {
		this.settleInfo = settleInfo;
	}

	
	public boolean isQuerySupported() {
		return querySupported;
	}

	public void setQuerySupported(boolean querySupported) {
		this.querySupported = querySupported;
	}

	public boolean isRefundSupported() {
		return refundSupported;
	}

	public void setRefundSupported(boolean refundSupported) {
		this.refundSupported = refundSupported;
	}

	public boolean isManualRefundSupported() {
		return manualRefundSupported;
	}

	public void setManualRefundSupported(boolean manualRefundSupported) {
		this.manualRefundSupported = manualRefundSupported;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fundChannelCode", getFundChannelCode())
            .append("name", getName())
            .append("description", getDescription())
            .append("instCode", getInstCode())
            .append("bizType", getBizType())
            .append("payMode", getPayMode())
            .append("signedCrop", getSignedCrop())
            .append("status", getStatus())
            .append("validFrom", getValidFrom())
            .append("validTo", getValidTo())
            .append("maxAmount", getMaxAmount())
            .append("minAmount", getMinAmount())
            .append("expectArriveTime", getExpectArriveTime())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("channelMode", getChannelMode())
            .append("priority", getPriority())
            .append("apiList", getApiList())
            .append("extList", getExtList())
            .append("instList", getInstList())
            .append("maintainList", getMaintainList())
            .append("institution", getInstitution())
            .append("settleInfo", getSettleInfo())
            .append("querySupported", isQuerySupported())
            .append("refundSupported", isRefundSupported())
            .append("manualRefundSupported", isManualRefundSupported())
            .toString();
    }
	
	public TmFundChannelApi getApi() {
        if (apiList != null && apiList.size() > 0) {
            return apiList.get(0);
        }
        return null;
    }

	public ChannelProperty getChannelProperty() {
		return channelProperty;
	}

	public void setChannelProperty(ChannelProperty channelProperty) {
		this.channelProperty = channelProperty;
	}
	 public boolean isAvailable() {
	        return isValid();
	    }
	    
	    public boolean isValid() {
	        Date curr = new Date(System.currentTimeMillis());
	        return Status.VALID.getCode().equals(this.status) && curr.before(this.validTo)
	               && curr.after(this.validFrom);
	    }
}
