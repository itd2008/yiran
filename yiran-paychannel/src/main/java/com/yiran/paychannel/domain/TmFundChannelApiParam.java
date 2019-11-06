package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 渠道接口参数表 tm_fund_channel_api_param
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmFundChannelApiParam extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer paramId;
	/** 接口编码 */
	private String apiCode;
	/** 参数名称 */
	private String paramName;
	/** 参数字典标识 */
	private String paramCodeKey;
	/** 参数描述 */
	private String paramDesc;
	/** 是否需要校验 */
	private String nullAble;
	/** 是否使用 */
	private String enable;
	/** 是否需要保存数据 */
	private String isSave;
	/** IN,入;OUT,出 */
	private String isOrgin;
	/** 事件 */
	private String scene;
	/** 创建时间 */
	private Date gmtCreate;
	/** 修改时间 */
	private Date gmtModified;
	/** 该信息是否从渠道获取,如招行需要每天更新密钥信息 */
	private String isChannelTrans;
	/** 是用哪个交互编码查询到的数据 */
	private String transCode;
	/** 扩展信息 */
	private String extension;

	public void setParamId(Integer paramId) 
	{
		this.paramId = paramId;
	}

	public Integer getParamId() 
	{
		return paramId;
	}
	public void setApiCode(String apiCode) 
	{
		this.apiCode = apiCode;
	}

	public String getApiCode() 
	{
		return apiCode;
	}
	public void setParamName(String paramName) 
	{
		this.paramName = paramName;
	}

	public String getParamName() 
	{
		return paramName;
	}
	public void setParamCodeKey(String paramCodeKey) 
	{
		this.paramCodeKey = paramCodeKey;
	}

	public String getParamCodeKey() 
	{
		return paramCodeKey;
	}
	public void setParamDesc(String paramDesc) 
	{
		this.paramDesc = paramDesc;
	}

	public String getParamDesc() 
	{
		return paramDesc;
	}
	public void setNullAble(String nullAble) 
	{
		this.nullAble = nullAble;
	}

	public String getNullAble() 
	{
		return nullAble;
	}
	public void setEnable(String enable) 
	{
		this.enable = enable;
	}

	public String getEnable() 
	{
		return enable;
	}
	public void setIsSave(String isSave) 
	{
		this.isSave = isSave;
	}

	public String getIsSave() 
	{
		return isSave;
	}
	public void setIsOrgin(String isOrgin) 
	{
		this.isOrgin = isOrgin;
	}

	public String getIsOrgin() 
	{
		return isOrgin;
	}
	public void setScene(String scene) 
	{
		this.scene = scene;
	}

	public String getScene() 
	{
		return scene;
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
	public void setIsChannelTrans(String isChannelTrans) 
	{
		this.isChannelTrans = isChannelTrans;
	}

	public String getIsChannelTrans() 
	{
		return isChannelTrans;
	}
	public void setTransCode(String transCode) 
	{
		this.transCode = transCode;
	}

	public String getTransCode() 
	{
		return transCode;
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
            .append("paramId", getParamId())
            .append("apiCode", getApiCode())
            .append("paramName", getParamName())
            .append("paramCodeKey", getParamCodeKey())
            .append("paramDesc", getParamDesc())
            .append("nullAble", getNullAble())
            .append("enable", getEnable())
            .append("isSave", getIsSave())
            .append("isOrgin", getIsOrgin())
            .append("scene", getScene())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("isChannelTrans", getIsChannelTrans())
            .append("transCode", getTransCode())
            .append("extension", getExtension())
            .toString();
    }
}
