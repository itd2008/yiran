package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 提供资金渠道目标机构表 tm_fund_channel_target_init
 * 
 * @author yiran
 * @date 2019-04-20
 */
public class TmFundChannelTargetInit extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 目标机构编码 */
	private String targetInstCode;
	/** 短名称 */
	private String shortName;
	/** 目标机构名称 */
	private String targetInstName;
	/** 目标机构描述 */
	private String targetInstDesc;
	/** 图标路径 */
	private String iconUrl;
	/** 描述 */
	private String amountLimitDesc;
	/** 创建时间 */
	private Date gmtCreate;
	/** 修改时间 */
	private Date gmtModified;

	public void setTargetInstCode(String targetInstCode) 
	{
		this.targetInstCode = targetInstCode;
	}

	public String getTargetInstCode() 
	{
		return targetInstCode;
	}
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public String getShortName() 
	{
		return shortName;
	}
	public void setTargetInstName(String targetInstName) 
	{
		this.targetInstName = targetInstName;
	}

	public String getTargetInstName() 
	{
		return targetInstName;
	}
	public void setTargetInstDesc(String targetInstDesc) 
	{
		this.targetInstDesc = targetInstDesc;
	}

	public String getTargetInstDesc() 
	{
		return targetInstDesc;
	}
	public void setIconUrl(String iconUrl) 
	{
		this.iconUrl = iconUrl;
	}

	public String getIconUrl() 
	{
		return iconUrl;
	}
	public void setAmountLimitDesc(String amountLimitDesc) 
	{
		this.amountLimitDesc = amountLimitDesc;
	}

	public String getAmountLimitDesc() 
	{
		return amountLimitDesc;
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
            .append("targetInstCode", getTargetInstCode())
            .append("shortName", getShortName())
            .append("targetInstName", getTargetInstName())
            .append("targetInstDesc", getTargetInstDesc())
            .append("iconUrl", getIconUrl())
            .append("amountLimitDesc", getAmountLimitDesc())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
