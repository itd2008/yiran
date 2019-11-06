package com.yiran.amqp.mapper;

import com.yiran.amqp.domain.MqExchangesQueue;

import java.util.List;	

/**
 * RabbitMQ交换器和队列关联 数据层
 * 
 * @author yiran
 * @date 2019-04-28
 */
public interface MqExchangesQueueMapper 
{
	/**
     * 查询RabbitMQ交换器和队列关联信息
     * 
     * @param exchangesId RabbitMQ交换器和队列关联ID
     * @return RabbitMQ交换器和队列关联信息
     */
	public MqExchangesQueue selectMqExchangesQueueById(Integer exchangesId);
	/**
	 * 
	 * @param queueId
	 * @return
	 */
	public MqExchangesQueue selectMqExchangesQueueByQueueId(Integer queueId);
	
	/**
     * 查询RabbitMQ交换器和队列关联列表
     * 
     * @param mqExchangesQueue RabbitMQ交换器和队列关联信息
     * @return RabbitMQ交换器和队列关联集合
     */
	public List<MqExchangesQueue> selectMqExchangesQueueList(MqExchangesQueue mqExchangesQueue);
	
	/**
     * 新增RabbitMQ交换器和队列关联
     * 
     * @param mqExchangesQueue RabbitMQ交换器和队列关联信息
     * @return 结果
     */
	public int insertMqExchangesQueue(MqExchangesQueue mqExchangesQueue);
	
	/**
     * 修改RabbitMQ交换器和队列关联
     * 
     * @param mqExchangesQueue RabbitMQ交换器和队列关联信息
     * @return 结果
     */
	public int updateMqExchangesQueue(MqExchangesQueue mqExchangesQueue);
	
	/**
     * 删除RabbitMQ交换器和队列关联
     * 
     * @param exchangesId RabbitMQ交换器和队列关联ID
     * @return 结果
     */
	public int deleteMqExchangesQueueById(Integer exchangesId);
	
	/**
     * 批量删除RabbitMQ交换器和队列关联
     * 
     * @param exchangesIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMqExchangesQueueByIds(String[] exchangesIds);
	
	/**
     * 批量RabbitMQ交换器和队列关联
     * 
     * @param roleDeptList RabbitMQ交换器和队列关联表
     * @return 结果
     */
    public int batchMqExchangesQueue(List<MqExchangesQueue> exchangesQueueList);
    
	public void deleteMqExchangesQueue(MqExchangesQueue exchangesQueue);
	
}