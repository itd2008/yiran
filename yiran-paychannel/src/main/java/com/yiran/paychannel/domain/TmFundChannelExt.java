package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 资金渠道特性表 tm_fund_channel_ext
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmFundChannelExt extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 扩展ID */
	private Integer extId;
	/** 资金源编码 */
	private String fundChannelCode;
	/** 属性名 */
	private String attrKey;
	/** 属性值 */
	private String attrValue;
	/** 属性类型：N（无特殊制定），F（流量） */
	private String attrType;
	/** 值类型 */
	private String valueType;
	/** 值分隔符，针对有多选属性使用 */
	private String valueSplit;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 属性字典标识 */
	private String attrCodeKey;
	/** api编码 */
	private String apiCode;
	/** 是否需要满足VALUE指定 */
	private String needMatch;
	/** 比较类型 */
	private String matchType;

	public void setExtId(Integer extId) 
	{
		this.extId = extId;
	}

	public Integer getExtId() 
	{
		return extId;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setAttrKey(String attrKey) 
	{
		this.attrKey = attrKey;
	}

	public String getAttrKey() 
	{
		return attrKey;
	}
	public void setAttrValue(String attrValue) 
	{
		this.attrValue = attrValue;
	}

	public String getAttrValue() 
	{
		return attrValue;
	}
	public void setAttrType(String attrType) 
	{
		this.attrType = attrType;
	}

	public String getAttrType() 
	{
		return attrType;
	}
	public void setValueType(String valueType) 
	{
		this.valueType = valueType;
	}

	public String getValueType() 
	{
		return valueType;
	}
	public void setValueSplit(String valueSplit) 
	{
		this.valueSplit = valueSplit;
	}

	public String getValueSplit() 
	{
		return valueSplit;
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
	public void setAttrCodeKey(String attrCodeKey) 
	{
		this.attrCodeKey = attrCodeKey;
	}

	public String getAttrCodeKey() 
	{
		return attrCodeKey;
	}
	public void setApiCode(String apiCode) 
	{
		this.apiCode = apiCode;
	}

	public String getApiCode() 
	{
		return apiCode;
	}
	public void setNeedMatch(String needMatch) 
	{
		this.needMatch = needMatch;
	}

	public String getNeedMatch() 
	{
		return needMatch;
	}
	public void setMatchType(String matchType) 
	{
		this.matchType = matchType;
	}

	public String getMatchType() 
	{
		return matchType;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("extId", getExtId())
            .append("fundChannelCode", getFundChannelCode())
            .append("attrKey", getAttrKey())
            .append("attrValue", getAttrValue())
            .append("attrType", getAttrType())
            .append("valueType", getValueType())
            .append("valueSplit", getValueSplit())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("attrCodeKey", getAttrCodeKey())
            .append("apiCode", getApiCode())
            .append("needMatch", getNeedMatch())
            .append("matchType", getMatchType())
            .toString();
    }
}
