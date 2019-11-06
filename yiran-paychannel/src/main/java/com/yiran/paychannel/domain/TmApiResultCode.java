package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * API结果编码表 tm_api_result_code
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmApiResultCode extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编码ID */
	private Integer apiResultCodeId;
	/** 资金源编码 */
	private String fundChannelCode;
	/** 接口类型 */
	private String apiType;
	/** 结果编码 */
	private String resultCode;
	/** 结果子编码 */
	private String resultSubCode;
	/** 判断表达式，作为编码映射的一个补充 */
	private String expression;
	/** 描述模板，如不为空则优先使用 */
	private String descriptionTemplate;
	/** 统一结果编码 */
	private String unityResultCode;
	/** 订单状态。S（成功），F（失败），A（处理中），W（未知） */
	private String orderStatus;
	/** 是否启用映射。Y（启用），N（不启用） */
	private String useMapping;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;

	
	public TmApiResultCode() {
	}

	/**
     * 通过关键参数构造
     * @param fundChannelCode
     * @param apiType
     * @param code
     * @param subCode
     */
    public TmApiResultCode(String fundChannelCode, String apiType, String resultCode,
                         String resultSubCode) {
        this.fundChannelCode = fundChannelCode;
        this.apiType = apiType;
        this.resultCode = resultCode;
        this.resultSubCode = resultSubCode;
    }
	public void setApiResultCodeId(Integer apiResultCodeId) 
	{
		this.apiResultCodeId = apiResultCodeId;
	}

	public Integer getApiResultCodeId() 
	{
		return apiResultCodeId;
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
	public void setResultCode(String resultCode) 
	{
		this.resultCode = resultCode;
	}

	public String getResultCode() 
	{
		return resultCode;
	}
	public void setResultSubCode(String resultSubCode) 
	{
		this.resultSubCode = resultSubCode;
	}

	public String getResultSubCode() 
	{
		return resultSubCode;
	}
	public void setExpression(String expression) 
	{
		this.expression = expression;
	}

	public String getExpression() 
	{
		return expression;
	}
	public void setDescriptionTemplate(String descriptionTemplate) 
	{
		this.descriptionTemplate = descriptionTemplate;
	}

	public String getDescriptionTemplate() 
	{
		return descriptionTemplate;
	}
	public void setUnityResultCode(String unityResultCode) 
	{
		this.unityResultCode = unityResultCode;
	}

	public String getUnityResultCode() 
	{
		return unityResultCode;
	}
	public void setOrderStatus(String orderStatus) 
	{
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() 
	{
		return orderStatus;
	}
	public void setUseMapping(String useMapping) 
	{
		this.useMapping = useMapping;
	}

	public String getUseMapping() 
	{
		return useMapping;
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
            .append("apiResultCodeId", getApiResultCodeId())
            .append("fundChannelCode", getFundChannelCode())
            .append("apiType", getApiType())
            .append("resultCode", getResultCode())
            .append("resultSubCode", getResultSubCode())
            .append("expression", getExpression())
            .append("descriptionTemplate", getDescriptionTemplate())
            .append("unityResultCode", getUnityResultCode())
            .append("orderStatus", getOrderStatus())
            .append("useMapping", getUseMapping())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .toString();
    }
}
