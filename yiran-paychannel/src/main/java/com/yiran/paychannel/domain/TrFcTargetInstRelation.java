package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 目标机构表 tr_fc_target_inst_relation
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TrFcTargetInstRelation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 渠道编码 */
	private String fundChannelCode;
	/** 目标机构编码 */
	private String targetInstCode;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setTargetInstCode(String targetInstCode) 
	{
		this.targetInstCode = targetInstCode;
	}

	public String getTargetInstCode() 
	{
		return targetInstCode;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fundChannelCode", getFundChannelCode())
            .append("targetInstCode", getTargetInstCode())
            .toString();
    }
}
