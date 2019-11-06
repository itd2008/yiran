package com.yiran.amqp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * MQ消息记录表 sys_mq_message_log
 * 
 * @author yiran
 * @date 2019-06-12
 */
public class MqMessageLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 消息唯一ID */
	private String messageId;
	/** 消息内容 */
	private String message;
	/** 重试次数 */
	private Integer tryCount;
	/** 消息投递状态 0 投递中 1 投递成功 2 投递失败 */
	private String status;
	/** 下一次重试时间 或 超时时间 */
	private Date nextRetry;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/**
	 * 交换器
	 */
	private String exchange;
	/**
	 * 路由KEY
	 */
	private String routingKey;
	
	
	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public void setMessageId(String messageId) 
	{
		this.messageId = messageId;
	}

	public String getMessageId() 
	{
		return messageId;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}

	public String getMessage() 
	{
		return message;
	}
	public void setTryCount(Integer tryCount) 
	{
		this.tryCount = tryCount;
	}

	public Integer getTryCount() 
	{
		return tryCount;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setNextRetry(Date nextRetry) 
	{
		this.nextRetry = nextRetry;
	}

	public Date getNextRetry() 
	{
		return nextRetry;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("message", getMessage())
            .append("tryCount", getTryCount())
            .append("status", getStatus())
            .append("nextRetry", getNextRetry())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("exchange", getExchange())
            .append("routingKey", getRoutingKey())
            .toString();
    }
}
