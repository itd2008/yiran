package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import com.yiran.paychannel.enums.ChannelTransInfoStatus;
import com.yiran.paychannel.enums.FundChannelApiType;

import java.util.Date;
import java.util.Map;

/**
 * 渠道交易需要用到的特定表 tm_channel_trans_info
 * 
 * @author yiran
 * @date 2019-07-26
 */
public class TmChannelTransInfo 
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer transId;
	private String                 fundChannelCode;

    private FundChannelApiType     apiType;

    private ChannelTransInfoStatus status;

    private Map<String, String>    extension;

    private String                 transCode;

    private String                 memo;

    private Date                   gmtCreate;

    private Date                   gmtModified;

	

    public Integer getTransId() {
		return transId;
	}



	public void setTransId(Integer transId) {
		this.transId = transId;
	}



	public String getFundChannelCode() {
		return fundChannelCode;
	}



	public void setFundChannelCode(String fundChannelCode) {
		this.fundChannelCode = fundChannelCode;
	}



	public FundChannelApiType getApiType() {
		return apiType;
	}



	public void setApiType(FundChannelApiType apiType) {
		this.apiType = apiType;
	}



	public ChannelTransInfoStatus getStatus() {
		return status;
	}



	public void setStatus(ChannelTransInfoStatus status) {
		this.status = status;
	}



	public Map<String, String> getExtension() {
		return extension;
	}



	public void setExtension(Map<String, String> extension) {
		this.extension = extension;
	}



	public String getTransCode() {
		return transCode;
	}



	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}



	public String getMemo() {
		return memo;
	}



	public void setMemo(String memo) {
		this.memo = memo;
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



	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("transId", getTransId())
            .append("fundChannelCode", getFundChannelCode())
            .append("apiType", getApiType())
            .append("transCode", getTransCode())
            .append("status", getStatus())
            .append("extension", getExtension())
            .append("memo", getMemo())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
