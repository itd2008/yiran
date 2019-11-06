package com.yiran.payorder.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 支付结果通知表 pay_result_notify
 * 
 * @author yiran
 * @date 2019-08-14
 */
public class PayResultNotify extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 通知ID */
	private String notifyId;
	/** 机构订单号 */
	private String instOrderId;
	/** 通知事件编码 */
	private String eventCode;
	/** 通知地址 */
	private String notifyTarget;
	/** 通知数据 */
	private String notifyData;
	/** 通知状态 */
	private String notifyStatus;
	/** 字符集编码 */
	private String inputCharset;
	/** 签名类型 */
	private String signatureType;
	/** 合作方ID */
	private String partnerId;
	/** 客户端ID */
	private String clientId;
	/** 服务器标识 */
	private String serverIdentity;
	/** 重试中标识 */
	private String retryingFlag;
	/** 通知次数 */
	private Integer retryCount;
	/** 重试时间 */
	private Date gmtRetry;
	/** 系统备注 */
	private String memo;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;

	public void setNotifyId(String notifyId) 
	{
		this.notifyId = notifyId;
	}

	public String getNotifyId() 
	{
		return notifyId;
	}
	public void setInstOrderId(String instOrderId) 
	{
		this.instOrderId = instOrderId;
	}

	public String getInstOrderId() 
	{
		return instOrderId;
	}
	public void setEventCode(String eventCode) 
	{
		this.eventCode = eventCode;
	}

	public String getEventCode() 
	{
		return eventCode;
	}
	public void setNotifyTarget(String notifyTarget) 
	{
		this.notifyTarget = notifyTarget;
	}

	public String getNotifyTarget() 
	{
		return notifyTarget;
	}
	public void setNotifyData(String notifyData) 
	{
		this.notifyData = notifyData;
	}

	public String getNotifyData() 
	{
		return notifyData;
	}
	public void setNotifyStatus(String notifyStatus) 
	{
		this.notifyStatus = notifyStatus;
	}

	public String getNotifyStatus() 
	{
		return notifyStatus;
	}
	public void setInputCharset(String inputCharset) 
	{
		this.inputCharset = inputCharset;
	}

	public String getInputCharset() 
	{
		return inputCharset;
	}
	public void setSignatureType(String signatureType) 
	{
		this.signatureType = signatureType;
	}

	public String getSignatureType() 
	{
		return signatureType;
	}
	public void setPartnerId(String partnerId) 
	{
		this.partnerId = partnerId;
	}

	public String getPartnerId() 
	{
		return partnerId;
	}
	public void setClientId(String clientId) 
	{
		this.clientId = clientId;
	}

	public String getClientId() 
	{
		return clientId;
	}
	public void setServerIdentity(String serverIdentity) 
	{
		this.serverIdentity = serverIdentity;
	}

	public String getServerIdentity() 
	{
		return serverIdentity;
	}
	public void setRetryingFlag(String retryingFlag) 
	{
		this.retryingFlag = retryingFlag;
	}

	public String getRetryingFlag() 
	{
		return retryingFlag;
	}
	public void setRetryCount(Integer retryCount) 
	{
		this.retryCount = retryCount;
	}

	public Integer getRetryCount() 
	{
		return retryCount;
	}
	public void setGmtRetry(Date gmtRetry) 
	{
		this.gmtRetry = gmtRetry;
	}

	public Date getGmtRetry() 
	{
		return gmtRetry;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
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
            .append("notifyId", getNotifyId())
            .append("instOrderId", getInstOrderId())
            .append("eventCode", getEventCode())
            .append("notifyTarget", getNotifyTarget())
            .append("notifyData", getNotifyData())
            .append("notifyStatus", getNotifyStatus())
            .append("inputCharset", getInputCharset())
            .append("signatureType", getSignatureType())
            .append("partnerId", getPartnerId())
            .append("clientId", getClientId())
            .append("serverIdentity", getServerIdentity())
            .append("retryingFlag", getRetryingFlag())
            .append("retryCount", getRetryCount())
            .append("gmtRetry", getGmtRetry())
            .append("memo", getMemo())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
