package com.yiran.payorder.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 支付结果通知日志表 pay_result_notify_log
 * 
 * @author yiran
 * @date 2019-08-14
 */
public class PayResultNotifyLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 通知请求ID */
	private Integer notifyRequestId;
	/** 通知ID */
	private String notifyId;
	/** 通知返回数据 */
	private String notifyReturnData;
	/** 服务器IP */
	private String serverIdentity;
	/** 创建时间 */
	private Date gmtNotify;
	/** 修改时间 */
	private Date gmtReturn;

	public void setNotifyRequestId(Integer notifyRequestId) 
	{
		this.notifyRequestId = notifyRequestId;
	}

	public Integer getNotifyRequestId() 
	{
		return notifyRequestId;
	}
	public void setNotifyId(String notifyId) 
	{
		this.notifyId = notifyId;
	}

	public String getNotifyId() 
	{
		return notifyId;
	}
	public void setNotifyReturnData(String notifyReturnData) 
	{
		this.notifyReturnData = notifyReturnData;
	}

	public String getNotifyReturnData() 
	{
		return notifyReturnData;
	}
	public void setServerIdentity(String serverIdentity) 
	{
		this.serverIdentity = serverIdentity;
	}

	public String getServerIdentity() 
	{
		return serverIdentity;
	}
	public void setGmtNotify(Date gmtNotify) 
	{
		this.gmtNotify = gmtNotify;
	}

	public Date getGmtNotify() 
	{
		return gmtNotify;
	}
	public void setGmtReturn(Date gmtReturn) 
	{
		this.gmtReturn = gmtReturn;
	}

	public Date getGmtReturn() 
	{
		return gmtReturn;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("notifyRequestId", getNotifyRequestId())
            .append("notifyId", getNotifyId())
            .append("notifyReturnData", getNotifyReturnData())
            .append("serverIdentity", getServerIdentity())
            .append("gmtNotify", getGmtNotify())
            .append("gmtReturn", getGmtReturn())
            .toString();
    }
}
