package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 渠道交易需要用到的特定表 tm_channel_trans_info
 * 
 * @author yiran
 * @date 2019-07-26
 */
public class TmChannelTransInfoDO 
{
	private static final long serialVersionUID = 1L;
	
	/** 自动编号 */
	private Integer transId;
	/** 资金渠道编码 */
	private String fundChannelCode;
	/** api类型 */
	private String apiType;
	/** 交易代码 */
	private String transCode;
	/** 状态 */
	private String status;
	/** 扩展信息 */
	private String extension;
	/** 备注 */
	private String memo;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;

	public void setTransId(Integer transId) 
	{
		this.transId = transId;
	}

	public Integer getTransId() 
	{
		return transId;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setApiType(String apiType) 
	{
		this.apiType = apiType;
	}

	public String getApiType() 
	{
		return apiType;
	}
	public void setTransCode(String transCode) 
	{
		this.transCode = transCode;
	}

	public String getTransCode() 
	{
		return transCode;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setExtension(String extension) 
	{
		this.extension = extension;
	}

	public String getExtension() 
	{
		return extension;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
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
