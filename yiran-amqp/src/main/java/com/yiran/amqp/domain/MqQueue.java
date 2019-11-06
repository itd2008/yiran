package com.yiran.amqp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * RabbitMQ队列表 sys_mq_queue
 * 
 * @author yiran
 * @date 2019-04-28
 */
public class MqQueue extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer queueId;
	/** 队列名称 */
	private String queueName;
	/** 是否持久化 */
	private String durability;
	/** 不再被使用时(没有任何绑定的情况下），是否由RabbitMQ自动删除 */
	private String autoDelete;
	/** 参数 */
	private String arguments;
	/** 应用名称(被那个系统使用） */
	private String appName;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;
	/**
	 * 交换器id
	 */
	private Integer exchangesId;
	/**
	 * 路由key
	 */
	private String routingKey;

	public void setQueueId(Integer queueId) 
	{
		this.queueId = queueId;
	}

	public Integer getQueueId() 
	{
		return queueId;
	}
	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public void setDurability(String durability) 
	{
		this.durability = durability;
	}

	public String getDurability() 
	{
		return durability;
	}
	public void setAutoDelete(String autoDelete) 
	{
		this.autoDelete = autoDelete;
	}

	public String getAutoDelete() 
	{
		return autoDelete;
	}
	public void setArguments(String arguments) 
	{
		this.arguments = arguments;
	}

	public String getArguments() 
	{
		return arguments;
	}
	public void setAppName(String appName) 
	{
		this.appName = appName;
	}

	public String getAppName() 
	{
		return appName;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("queueId", getQueueId())
            .append("name", getQueueName())
            .append("durability", getDurability())
            .append("autoDelete", getAutoDelete())
            .append("arguments", getArguments())
            .append("appName", getAppName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("exchangesId", getExchangesId())
            .append("routingKey", getRoutingKey())
            .toString();
    }

	public Integer getExchangesId() {
		return exchangesId;
	}

	public void setExchangesId(Integer exchangesId) {
		this.exchangesId = exchangesId;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
	
    
}
