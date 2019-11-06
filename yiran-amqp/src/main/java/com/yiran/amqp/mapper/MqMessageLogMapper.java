package com.yiran.amqp.mapper;

import com.yiran.amqp.domain.MqMessageLog;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * MQ消息记录 数据层
 * 
 * @author yiran
 * @date 2019-06-12
 */
public interface MqMessageLogMapper 
{
	/**
     * 查询MQ消息记录信息
     * 
     * @param messageId MQ消息记录ID
     * @return MQ消息记录信息
     */
	public MqMessageLog selectMqMessageLogById(String messageId);
	
	/**
     * 查询MQ消息记录列表
     * 
     * @param mqMessageLog MQ消息记录信息
     * @return MQ消息记录集合
     */
	public List<MqMessageLog> selectMqMessageLogList(MqMessageLog mqMessageLog);
	
	/**
     * 新增MQ消息记录
     * 
     * @param mqMessageLog MQ消息记录信息
     * @return 结果
     */
	public int insertMqMessageLog(MqMessageLog mqMessageLog);
	
	/**
     * 修改MQ消息记录
     * 
     * @param mqMessageLog MQ消息记录信息
     * @return 结果
     */
	public int updateMqMessageLog(MqMessageLog mqMessageLog);
	
	/**
     * 删除MQ消息记录
     * 
     * @param messageId MQ消息记录ID
     * @return 结果
     */
	public int deleteMqMessageLogById(String messageId);
	
	/**
     * 批量删除MQ消息记录
     * 
     * @param messageIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMqMessageLogByIds(String[] messageIds);

	/**
	 * 查询消息状态为0(发送中) 且已经超时的消息集合
	 * @return
	 */
	public List<MqMessageLog> query4StatusAndTimeoutMessage();

	/**
     * 重新发送统计count发送次数 +1
     * @param messageId
     * @param updateTime
     */
	public void update4ReSend(@Param("messageId")String messageId, @Param("updateTime")Date updateTime);
	/**
     * 更新最终消息发送结果 成功 or 失败
     * @param messageId
     * @param status
     * @param updateTime
     */
	public void changeMQMessageLogStatus(@Param("messageId")String messageId, @Param("status")String status, @Param("updateTime")Date updateTime);
	
}