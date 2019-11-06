package com.yiran.amqp.mapper;

import com.yiran.amqp.domain.MqQueue;
import java.util.List;	

/**
 * RabbitMQ队列 数据层
 * 
 * @author yiran
 * @date 2019-04-28
 */
public interface MqQueueMapper 
{
	/**
     * 查询RabbitMQ队列信息
     * 
     * @param queueId RabbitMQ队列ID
     * @return RabbitMQ队列信息
     */
	public MqQueue selectMqQueueById(Integer queueId);
	
	/**
     * 查询RabbitMQ队列列表
     * 
     * @param mqQueue RabbitMQ队列信息
     * @return RabbitMQ队列集合
     */
	public List<MqQueue> selectMqQueueList(MqQueue mqQueue);
	
	/**
     * 新增RabbitMQ队列
     * 
     * @param mqQueue RabbitMQ队列信息
     * @return 结果
     */
	public int insertMqQueue(MqQueue mqQueue);
	
	/**
     * 修改RabbitMQ队列
     * 
     * @param mqQueue RabbitMQ队列信息
     * @return 结果
     */
	public int updateMqQueue(MqQueue mqQueue);
	
	/**
     * 删除RabbitMQ队列
     * 
     * @param queueId RabbitMQ队列ID
     * @return 结果
     */
	public int deleteMqQueueById(Integer queueId);
	
	/**
     * 批量删除RabbitMQ队列
     * 
     * @param queueIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMqQueueByIds(String[] queueIds);
	
}