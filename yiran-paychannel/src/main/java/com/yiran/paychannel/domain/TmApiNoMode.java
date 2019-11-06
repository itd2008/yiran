package com.yiran.paychannel.domain;

import com.yiran.common.base.BaseEntity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 资金源接口订单号模式表 tm_api_no_mode
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmApiNoMode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 模式ID */
	private Integer apiNoModeId;
	/** 生成模式 */
	private String genPattern;
	/** 流水号名称 */
	private String seqName;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;

	public void setApiNoModeId(Integer apiNoModeId) 
	{
		this.apiNoModeId = apiNoModeId;
	}

	public Integer getApiNoModeId() 
	{
		return apiNoModeId;
	}
	public void setGenPattern(String genPattern) 
	{
		this.genPattern = genPattern;
	}

	public String getGenPattern() 
	{
		return genPattern;
	}
	public void setSeqName(String seqName) 
	{
		this.seqName = seqName;
	}

	public String getSeqName() 
	{
		return seqName;
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

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("apiNoModeId", getApiNoModeId())
            .append("genPattern", getGenPattern())
            .append("seqName", getSeqName())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .toString();
    }
}
