package com.yiran.amqp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.amqp.mapper.MqExchangesMapper;
import com.alibaba.fastjson.JSON;
import com.yiran.amqp.domain.MqExchanges;
import com.yiran.amqp.enums.ExchangesType;
import com.yiran.amqp.service.IMqExchangesService;
import com.yiran.common.support.Convert;

import cn.hutool.json.JSONUtil;

/**
 * RabbitMQ交换器 服务层实现
 * 
 * @author yiran
 * @date 2019-04-28
 */
@Service
public class MqExchangesServiceImpl implements IMqExchangesService 
{
	private Logger        logger = LoggerFactory.getLogger(MqExchangesServiceImpl.class);
	@Autowired
	private MqExchangesMapper mqExchangesMapper;
	@Autowired
	private AmqpAdmin amqpAdmin;

	/**
     * 查询RabbitMQ交换器信息
     * 
     * @param exchangesId RabbitMQ交换器ID
     * @return RabbitMQ交换器信息
     */
    @Override
	public MqExchanges selectMqExchangesById(Integer exchangesId)
	{
	    return mqExchangesMapper.selectMqExchangesById(exchangesId);
	}
	
	/**
     * 查询RabbitMQ交换器列表
     * 
     * @param mqExchanges RabbitMQ交换器信息
     * @return RabbitMQ交换器集合
     */
	@Override
	public List<MqExchanges> selectMqExchangesList(MqExchanges mqExchanges)
	{
	    return mqExchangesMapper.selectMqExchangesList(mqExchanges);
	}
	
    /**
     * 新增RabbitMQ交换器
     * 
     * @param mqExchanges RabbitMQ交换器信息
     * @return 结果
     */
	@Override
	public int insertMqExchanges(MqExchanges mqExchanges)
	{
		Map<String, Object> arguments = new HashMap<String,Object>();
		if(!StringUtils.isBlank(mqExchanges.getArguments())){
			//json转map
			arguments = JSON.parseObject(mqExchanges.getArguments(), Map.class);
		}
		//添加exchanges到RabbitMQ
		Exchange exchange = null;
		if(ExchangesType.DIRECT.getCode().equals(mqExchanges.getType())){
			exchange = new DirectExchange(mqExchanges.getName(),true,false,arguments);
		}else if(ExchangesType.TOPIC.getCode().equals(mqExchanges.getType())){
			exchange = new TopicExchange(mqExchanges.getName(),true,false,arguments);
		}else if(ExchangesType.FANOUT.getCode().equals(mqExchanges.getType())){
			exchange = new FanoutExchange(mqExchanges.getName(),true,false,arguments);
		}else if(ExchangesType.HEADERS.getCode().equals(mqExchanges.getType())){
			exchange = new HeadersExchange(mqExchanges.getName(),true,false,arguments);
		}
		//RabbitMq添加数据
		amqpAdmin.declareExchange(exchange);
		logger.info("添加RabbitMQ 交换机类型【{}】,交换器名称【{}】成功",mqExchanges.getType(),exchange.getName());
	    return mqExchangesMapper.insertMqExchanges(mqExchanges);
	}
	
	/**
     * 修改RabbitMQ交换器
     * 
     * @param mqExchanges RabbitMQ交换器信息
     * @return 结果
     */
	@Override
	public int updateMqExchanges(MqExchanges mqExchanges)
	{
	    return mqExchangesMapper.updateMqExchanges(mqExchanges);
	}

	/**
     * 删除RabbitMQ交换器对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMqExchangesByIds(String ids)
	{
		String[] exchangeIds = Convert.toStrArray(ids);
		if(exchangeIds.length>0){
			for (int i = 0; i < exchangeIds.length; i++) {
				MqExchanges mqExchange = mqExchangesMapper.selectMqExchangesById(Integer.parseInt(exchangeIds[i]));
				amqpAdmin.deleteExchange(mqExchange.getName());
				logger.info("删除RabbitMQ 交换机类型【{}】,交换器名称【{}】成功",mqExchange.getType(),mqExchange.getName());
			}
		}
		return mqExchangesMapper.deleteMqExchangesByIds(exchangeIds);
	}

	@Override
	public List<MqExchanges> selectAllMqExchangesList() {
		return mqExchangesMapper.selectAllMqExchangesList();
	}
	
}
