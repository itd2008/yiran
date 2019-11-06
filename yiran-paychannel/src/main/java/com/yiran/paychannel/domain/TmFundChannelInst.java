package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 提供资金渠道的机构表 tm_fund_channel_inst
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmFundChannelInst extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 机构代码 */
	private String instCode;
	/** 机构类型：BANK（银行），EPAY（电子支付公司） */
	private String instType;
	/** 机构名称 */
	private String instName;
	/** 机构分支名称 */
	private String instBranchName;
	/** 联行号 */
	private String bankLineNo;
	/** 地区 */
	private String areaCode;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 扩展信息 */
	private String extension;

	public void setInstCode(String instCode) 
	{
		this.instCode = instCode;
	}

	public String getInstCode() 
	{
		return instCode;
	}
	public void setInstType(String instType) 
	{
		this.instType = instType;
	}

	public String getInstType() 
	{
		return instType;
	}
	public void setInstName(String instName) 
	{
		this.instName = instName;
	}

	public String getInstName() 
	{
		return instName;
	}
	public void setInstBranchName(String instBranchName) 
	{
		this.instBranchName = instBranchName;
	}

	public String getInstBranchName() 
	{
		return instBranchName;
	}
	public void setBankLineNo(String bankLineNo) 
	{
		this.bankLineNo = bankLineNo;
	}

	public String getBankLineNo() 
	{
		return bankLineNo;
	}
	public void setAreaCode(String areaCode) 
	{
		this.areaCode = areaCode;
	}

	public String getAreaCode() 
	{
		return areaCode;
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
            .append("instCode", getInstCode())
            .append("instType", getInstType())
            .append("instName", getInstName())
            .append("instBranchName", getInstBranchName())
            .append("bankLineNo", getBankLineNo())
            .append("areaCode", getAreaCode())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("extension", getExtension())
            .toString();
    }
}
