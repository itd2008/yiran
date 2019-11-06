package com.yiran.amqp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.amqp.mapper.MqExchangesQueueMapper;
import com.yiran.amqp.domain.MqExchangesQueue;
import com.yiran.amqp.service.IMqExchangesQueueService;
import com.yiran.common.support.Convert;

/**
 * RabbitMQ交换器和队列关联 服务层实现
 * 
 * @author yiran
 * @date 2019-04-28
 */
@Service
public class MqExchangesQueueServiceImpl implements IMqExchangesQueueService 
{
	@Autowired
	private MqExchangesQueueMapper mqExchangesQueueMapper;

	/**
     * 查询RabbitMQ交换器和队列关联信息
     * 
     * @param exchangesId RabbitMQ交换器和队列关联ID
     * @return RabbitMQ交换器和队列关联信息
     */
    @Override
	public MqExchangesQueue selectMqExchangesQueueById(Integer exchangesId)
	{
	    return mqExchangesQueueMapper.selectMqExchangesQueueById(exchangesId);
	}
	
	/**
     * 查询RabbitMQ交换器和队列关联列表
     * 
     * @param mqExchangesQueue RabbitMQ交换器和队列关联信息
     * @return RabbitMQ交换器和队列关联集合
     */
	@Override
	public List<MqExchangesQueue> selectMqExchangesQueueList(MqExchangesQueue mqExchangesQueue)
	{
	    return mqExchangesQueueMapper.selectMqExchangesQueueList(mqExchangesQueue);
	}
	
    /**
     * 新增RabbitMQ交换器和队列关联
     * 
     * @param mqExchangesQueue RabbitMQ交换器和队列关联信息
     * @return 结果
     */
	@Override
	public int insertMqExchangesQueue(MqExchangesQueue mqExchangesQueue)
	{
	    return mqExchangesQueueMapper.insertMqExchangesQueue(mqExchangesQueue);
	}
	
	/**
     * 修改RabbitMQ交换器和队列关联
     * 
     * @param mqExchangesQueue RabbitMQ交换器和队列关联信息
     * @return 结果
     */
	@Override
	public int updateMqExchangesQueue(MqExchangesQueue mqExchangesQueue)
	{
	    return mqExchangesQueueMapper.updateMqExchangesQueue(mqExchangesQueue);
	}

	/**
     * 删除RabbitMQ交换器和队列关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMqExchangesQueueByIds(String ids)
	{
		return mqExchangesQueueMapper.deleteMqExchangesQueueByIds(Convert.toStrArray(ids));
	}

	@Override
	public void deleteMqExchangesQueue(MqExchangesQueue exchangesQueue) {

		mqExchangesQueueMapper.deleteMqExchangesQueue(exchangesQueue);
	}
	
}
