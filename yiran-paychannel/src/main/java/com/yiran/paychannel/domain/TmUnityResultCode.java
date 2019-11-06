package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 统一结果代码表 tm_unity_result_code
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmUnityResultCode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 统一结果编码 */
	private String unityResultCode;
	/** 描述模板 */
	private String descriptionTemplate;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;

	public void setUnityResultCode(String unityResultCode) 
	{
		this.unityResultCode = unityResultCode;
	}

	public String getUnityResultCode() 
	{
		return unityResultCode;
	}
	public void setDescriptionTemplate(String descriptionTemplate) 
	{
		this.descriptionTemplate = descriptionTemplate;
	}

	public String getDescriptionTemplate() 
	{
		return descriptionTemplate;
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
            .append("unityResultCode", getUnityResultCode())
            .append("descriptionTemplate", getDescriptionTemplate())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .toString();
    }
}
