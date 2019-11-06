package com.yiran.amqp.mapper;

import com.yiran.amqp.domain.MqExchanges;
import java.util.List;	

/**
 * RabbitMQ交换器 数据层
 * 
 * @author yiran
 * @date 2019-04-28
 */
public interface MqExchangesMapper 
{
	/**
     * 查询RabbitMQ交换器信息
     * 
     * @param exchangesId RabbitMQ交换器ID
     * @return RabbitMQ交换器信息
     */
	public MqExchanges selectMqExchangesById(Integer exchangesId);
	
	/**
     * 查询RabbitMQ交换器列表
     * 
     * @param mqExchanges RabbitMQ交换器信息
     * @return RabbitMQ交换器集合
     */
	public List<MqExchanges> selectMqExchangesList(MqExchanges mqExchanges);
	
	/**
     * 新增RabbitMQ交换器
     * 
     * @param mqExchanges RabbitMQ交换器信息
     * @return 结果
     */
	public int insertMqExchanges(MqExchanges mqExchanges);
	
	/**
     * 修改RabbitMQ交换器
     * 
     * @param mqExchanges RabbitMQ交换器信息
     * @return 结果
     */
	public int updateMqExchanges(MqExchanges mqExchanges);
	
	/**
     * 删除RabbitMQ交换器
     * 
     * @param exchangesId RabbitMQ交换器ID
     * @return 结果
     */
	public int deleteMqExchangesById(Integer exchangesId);
	
	/**
     * 批量删除RabbitMQ交换器
     * 
     * @param exchangesIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMqExchangesByIds(String[] exchangesIds);

	public List<MqExchanges> selectAllMqExchangesList();
	
}