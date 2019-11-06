package com.yiran.payorder.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import com.yiran.payorder.enums.MonitorItem;
import com.yiran.payorder.enums.MonitorLogStatus;

import cn.hutool.system.SystemUtil;

import java.util.Date;

/**
 * 监控日志表 pay_monitor_log
 * 
 * @author yiran
 * @date 2019-07-13
 */
public class PayMonitorLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 日志ID */
	private Integer logId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/** IP地址 */
	private String ipAddress;
	/** 事件描述 */
	private String eventMessage;
	/** 监控事件ID */
	private String actionId;
	/** 报警级别: info, warn, error, fatal */
	private String alertLevel;
	/**
	 * 状态
	 */
	private String logStatus;
	/** 异常日志 */
	private String exceptionLog;
	/** 备注 */
	private String memo;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;

	
	public PayMonitorLog() {
		super();
	}

	public PayMonitorLog(String orderNo, MonitorItem monitorItem, String message, Throwable e) {
        super();
        this.orderNo = orderNo;
        this.eventMessage = message;
        this.ipAddress = SystemUtil.getHostInfo().getAddress();
        this.gmtCreate = new Date();
        this.logStatus = MonitorLogStatus.AWAITING.getCode(); //默认为待发送.
        this.actionId = monitorItem.getActionId();
        this.alertLevel = monitorItem.getMonitorLevel();
        this.exceptionLog = e.getMessage();
    }
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setLogId(Integer logId) 
	{
		this.logId = logId;
	}

	public Integer getLogId() 
	{
		return logId;
	}
	public void setIpAddress(String ipAddress) 
	{
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() 
	{
		return ipAddress;
	}
	public void setEventMessage(String eventMessage) 
	{
		this.eventMessage = eventMessage;
	}

	public String getEventMessage() 
	{
		return eventMessage;
	}
	public void setActionId(String actionId) 
	{
		this.actionId = actionId;
	}

	public String getActionId() 
	{
		return actionId;
	}
	public void setAlertLevel(String alertLevel) 
	{
		this.alertLevel = alertLevel;
	}

	public String getAlertLevel() 
	{
		return alertLevel;
	}
	public void setLogStatus(String logStatus) 
	{
		this.logStatus = logStatus;
	}

	public String getLogStatus() 
	{
		return logStatus;
	}
	public void setExceptionLog(String exceptionLog) 
	{
		this.exceptionLog = exceptionLog;
	}

	public String getExceptionLog() 
	{
		return exceptionLog;
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
            .append("logId", getLogId())
            .append("ipAddress", getIpAddress())
            .append("eventMessage", getEventMessage())
            .append("actionId", getActionId())
            .append("alertLevel", getAlertLevel())
            .append("logStatus", getLogStatus())
            .append("exceptionLog", getExceptionLog())
            .append("memo", getMemo())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .toString();
    }
}
