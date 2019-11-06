package com.yiran.amqp.domain;

import java.util.Map;

/**
 * 发送请求信息
 * @author pandaa
 *
 */
public class AmqoRequrst {

	/**
	 * 交换器
	 */
	private String exchange; 
	/**
	 * 路由key
	 */
	private String routingKey;
	/**
	 * 发送数据
	 */
	private Map<String,Object> map;
	/**
	 * 队列名称
	 */
	private String queueName;
	/**
	 * MQ消息唯一ID
	 */
	private String messageId;
	/**
	 * 是否需要保存消息true保存false不保存
	 */
	private boolean isSave;
	
	public boolean isSave() {
		return isSave;
	}
	public void setSave(boolean isSave) {
		this.isSave = isSave;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
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
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	@Override
	public String toString() {
		return "AmqoRequrst [exchange=" + exchange + ", routingKey=" + routingKey + ", map=" + map + ", queueName="
				+ queueName + ", messageId=" + messageId + ", isSave=" + isSave + "]";
	}
	
	
}
