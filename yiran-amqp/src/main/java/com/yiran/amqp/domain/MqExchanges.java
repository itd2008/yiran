package com.yiran.amqp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * RabbitMQ交换器表 sys_mq_exchanges
 * 
 * @author yiran
 * @date 2019-04-28
 */
public class MqExchanges extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer exchangesId;
	/** 交换机名称 */
	private String name;
	/** 类型 */
	private String type;
	/** 是否持久化 */
	private String durability;
	/** 不再被使用时(没有任何绑定的情况下），是否由RabbitMQ自动删除 */
	private String autoDelete;
	/** 内部的 */
	private String internal;
	/** 被谁使用 */
	private String userWhoPerformedAction;
	/** 参数 */
	private String arguments;
	/** 虚拟主机 */
	private String vhost;
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
	 * 队列集合
	 */
	private List<MqQueue> queues;

	
	public List<MqQueue> getQueues() {
		return queues;
	}

	public void setQueues(List<MqQueue> queues) {
		this.queues = queues;
	}

	public void setExchangesId(Integer exchangesId) 
	{
		this.exchangesId = exchangesId;
	}

	public Integer getExchangesId() 
	{
		return exchangesId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
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
	public void setInternal(String internal) 
	{
		this.internal = internal;
	}

	public String getInternal() 
	{
		return internal;
	}
	public void setUserWhoPerformedAction(String userWhoPerformedAction) 
	{
		this.userWhoPerformedAction = userWhoPerformedAction;
	}

	public String getUserWhoPerformedAction() 
	{
		return userWhoPerformedAction;
	}
	public void setArguments(String arguments) 
	{
		this.arguments = arguments;
	}

	public String getArguments() 
	{
		return arguments;
	}
	public void setVhost(String vhost) 
	{
		this.vhost = vhost;
	}

	public String getVhost() 
	{
		return vhost;
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
            .append("exchangesId", getExchangesId())
            .append("name", getName())
            .append("type", getType())
            .append("durability", getDurability())
            .append("autoDelete", getAutoDelete())
            .append("internal", getInternal())
            .append("userWhoPerformedAction", getUserWhoPerformedAction())
            .append("arguments", getArguments())
            .append("vhost", getVhost())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
