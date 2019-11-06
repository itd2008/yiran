package com.yiran.amqp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.amqp.mapper.MqExchangesMapper;
import com.yiran.amqp.mapper.MqExchangesQueueMapper;
import com.yiran.amqp.mapper.MqQueueMapper;
import com.alibaba.fastjson.JSON;
import com.yiran.amqp.domain.MqExchanges;
import com.yiran.amqp.domain.MqExchangesQueue;
import com.yiran.amqp.domain.MqQueue;
import com.yiran.amqp.service.IMqQueueService;
import com.yiran.common.support.Convert;

/**
 * RabbitMQ队列 服务层实现
 * 
 * @author yiran
 * @date 2019-04-28
 */
@Service
public class MqQueueServiceImpl implements IMqQueueService 
{
	private Logger        logger = LoggerFactory.getLogger(MqQueueServiceImpl.class);
	@Autowired
	private MqQueueMapper mqQueueMapper;
	@Autowired
	private MqExchangesMapper mqExchangesMapper;
	
	@Autowired
	private MqExchangesQueueMapper mqExchangesQueueMapper;
	
	@Autowired
	private AmqpAdmin amqpAdmin;

	/**
     * 查询RabbitMQ队列信息
     * 
     * @param queueId RabbitMQ队列ID
     * @return RabbitMQ队列信息
     */
    @Override
	public MqQueue selectMqQueueById(Integer queueId)
	{
	    return mqQueueMapper.selectMqQueueById(queueId);
	}
	
	/**
     * 查询RabbitMQ队列列表
     * 
     * @param mqQueue RabbitMQ队列信息
     * @return RabbitMQ队列集合
     */
	@Override
	public List<MqQueue> selectMqQueueList(MqQueue mqQueue)
	{
	    return mqQueueMapper.selectMqQueueList(mqQueue);
	}
	
    /**
     * 新增RabbitMQ队列
     * 
     * @param mqQueue RabbitMQ队列信息
     * @return 结果
     */
	@Override
	public int insertMqQueue(MqQueue mqQueue)
	{
		Map<String, Object> arguments = new HashMap<String,Object>();
		if(!StringUtils.isBlank(mqQueue.getArguments())){
			//json转map
			arguments = JSON.parseObject(mqQueue.getArguments(), Map.class);
		}
		int flag = mqQueueMapper.insertMqQueue(mqQueue);
		//1.创建Queue
		amqpAdmin.declareQueue(new Queue(mqQueue.getQueueName()));
		logger.info("添加RabbitMQ 队列【{}】成功",mqQueue.getQueueName());
		//2.Queue绑定到指定的Exchanges
		//2.1 根据exchangesId获取exchanges的名称
		MqExchanges exchange = mqExchangesMapper.selectMqExchangesById(mqQueue.getExchangesId());

		amqpAdmin.declareBinding(new Binding(mqQueue.getQueueName(),DestinationType.QUEUE,exchange.getName(),mqQueue.getRoutingKey(),arguments));
		logger.info("队列【{}】绑定到【{}】交换器,成功!路由Key是【{}】",mqQueue.getQueueName(),exchange.getName(),mqQueue.getRoutingKey());
		//3.将exchangesId和queueId插入到中间表
		MqExchangesQueue mqExchangesQueue = new MqExchangesQueue();
		mqExchangesQueue.setExchangesId(mqQueue.getExchangesId());
		mqExchangesQueue.setQueueId(mqQueue.getQueueId());
		mqExchangesQueueMapper.insertMqExchangesQueue(mqExchangesQueue);
		
	    return flag;
	}
	
	/**
     * 修改RabbitMQ队列
     * 
     * @param mqQueue RabbitMQ队列信息
     * @return 结果
     */
	@Override
	public int updateMqQueue(MqQueue mqQueue)
	{
	    return mqQueueMapper.updateMqQueue(mqQueue);
	}

	/**
     * 删除RabbitMQ队列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMqQueueByIds(String ids)
	{
		String[] queueIds = Convert.toStrArray(ids);
		if(queueIds.length>0){
			for (int i = 0; i < queueIds.length; i++) {
				MqQueue mqQueue = mqQueueMapper.selectMqQueueById(Integer.parseInt(queueIds[i]));
				MqExchangesQueue exchangesQueue = mqExchangesQueueMapper.selectMqExchangesQueueByQueueId(mqQueue.getQueueId());
				MqExchanges mqExchanges = mqExchangesMapper.selectMqExchangesById(exchangesQueue.getExchangesId());
				//1.解绑交换器上绑定的queue
				Map<String, Object> arguments = new HashMap<String,Object>();
				if(!StringUtils.isBlank(mqQueue.getArguments())){
					//json转map
					arguments = JSON.parseObject(mqQueue.getArguments(), Map.class);
				}
				amqpAdmin.removeBinding(new Binding(mqQueue.getQueueName(), DestinationType.QUEUE,
						mqExchanges.getName(), mqQueue.getRoutingKey(), arguments));
				//删除queue
				amqpAdmin.deleteQueue(mqQueue.getQueueName());
				
				//删除关联表
				mqExchangesQueueMapper.deleteMqExchangesQueueById(mqExchanges.getExchangesId());
				
				
				logger.info("删除RabbitMQ 消息队列【{}】成功",mqQueue.getQueueName());
			}
		}
		return mqQueueMapper.deleteMqQueueByIds(Convert.toStrArray(ids));
	}
	
}
