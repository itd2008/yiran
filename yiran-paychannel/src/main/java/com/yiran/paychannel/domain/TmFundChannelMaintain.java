package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 资金渠道维护期表 tm_fund_channel_maintain
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmFundChannelMaintain extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 维护ID */
	private Integer maintainId;
	/** 资金源编码 */
	private String fundChannelCode;
	/** 资金渠道接口 */
	private String fundChannelApi;
	/** 描述 */
	private String description;
	/** 维护公告 */
	private String maintainContent;
	/** 维护开始时间 */
	private Date gmtBegin;
	/** 维护结束时间 */
	private Date gmtEnd;
	/** 备用资金源 */
	private String fundChannelBackup;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 机构代码 */
	private String instCode;

	public void setMaintainId(Integer maintainId) 
	{
		this.maintainId = maintainId;
	}

	public Integer getMaintainId() 
	{
		return maintainId;
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
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getDescription() 
	{
		return description;
	}
	public void setMaintainContent(String maintainContent) 
	{
		this.maintainContent = maintainContent;
	}

	public String getMaintainContent() 
	{
		return maintainContent;
	}
	public void setGmtBegin(Date gmtBegin) 
	{
		this.gmtBegin = gmtBegin;
	}

	public Date getGmtBegin() 
	{
		return gmtBegin;
	}
	public void setGmtEnd(Date gmtEnd) 
	{
		this.gmtEnd = gmtEnd;
	}

	public Date getGmtEnd() 
	{
		return gmtEnd;
	}
	public void setFundChannelBackup(String fundChannelBackup) 
	{
		this.fundChannelBackup = fundChannelBackup;
	}

	public String getFundChannelBackup() 
	{
		return fundChannelBackup;
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
	public void setInstCode(String instCode) 
	{
		this.instCode = instCode;
	}

	public String getInstCode() 
	{
		return instCode;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("maintainId", getMaintainId())
            .append("fundChannelCode", getFundChannelCode())
            .append("fundChannelApi", getFundChannelApi())
            .append("description", getDescription())
            .append("maintainContent", getMaintainContent())
            .append("gmtBegin", getGmtBegin())
            .append("gmtEnd", getGmtEnd())
            .append("fundChannelBackup", getFundChannelBackup())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("instCode", getInstCode())
            .toString();
    }
}
