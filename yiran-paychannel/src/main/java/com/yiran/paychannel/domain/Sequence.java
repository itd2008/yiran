package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * 序列表 sequence
 * 
 * @author yiran
 * @date 2019-07-26
 */
public class Sequence 
{
	private static final long serialVersionUID = 1L;
	
	/** 序列名称 */
	private String name;
	/** 当前值 */
	private Integer currentValue;
	/** 增量 */
	private Integer increment;

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setCurrentValue(Integer currentValue) 
	{
		this.currentValue = currentValue;
	}

	public Integer getCurrentValue() 
	{
		return currentValue;
	}
	public void setIncrement(Integer increment) 
	{
		this.increment = increment;
	}

	public Integer getIncrement() 
	{
		return increment;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("name", getName())
            .append("currentValue", getCurrentValue())
            .append("increment", getIncrement())
            .toString();
    }
}
