package com.yiran.amqp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;

/**
 * RabbitMQ交换器和队列关联表 sys_mq_exchanges_queue
 * 
 * @author yiran
 * @date 2019-04-28
 */
public class MqExchangesQueue extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 交换器ID */
	private Integer exchangesId;
	/** 队列ID */
	private Integer queueId;

	public void setExchangesId(Integer exchangesId) 
	{
		this.exchangesId = exchangesId;
	}

	public Integer getExchangesId() 
	{
		return exchangesId;
	}
	public void setQueueId(Integer queueId) 
	{
		this.queueId = queueId;
	}

	public Integer getQueueId() 
	{
		return queueId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("exchangesId", getExchangesId())
            .append("queueId", getQueueId())
            .toString();
    }
}
