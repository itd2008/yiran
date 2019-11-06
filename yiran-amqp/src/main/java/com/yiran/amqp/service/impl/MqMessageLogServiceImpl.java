package com.yiran.amqp.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiran.amqp.mapper.MqMessageLogMapper;
import com.yiran.amqp.domain.MqMessageLog;
import com.yiran.amqp.service.IMqMessageLogService;
import com.yiran.common.support.Convert;

/**
 * MQ消息记录 服务层实现
 * 
 * @author yiran
 * @date 2019-06-12
 */
@Service
public class MqMessageLogServiceImpl implements IMqMessageLogService 
{
	@Autowired
	private MqMessageLogMapper mqMessageLogMapper;

	/**
     * 查询MQ消息记录信息
     * 
     * @param messageId MQ消息记录ID
     * @return MQ消息记录信息
     */
    @Override
	public MqMessageLog selectMqMessageLogById(String messageId)
	{
	    return mqMessageLogMapper.selectMqMessageLogById(messageId);
	}
	
	/**
     * 查询MQ消息记录列表
     * 
     * @param mqMessageLog MQ消息记录信息
     * @return MQ消息记录集合
     */
	@Override
	public List<MqMessageLog> selectMqMessageLogList(MqMessageLog mqMessageLog)
	{
	    return mqMessageLogMapper.selectMqMessageLogList(mqMessageLog);
	}
	
    /**
     * 新增MQ消息记录
     * 
     * @param mqMessageLog MQ消息记录信息
     * @return 结果
     */
	@Transactional
	@Override
	public int insertMqMessageLog(MqMessageLog mqMessageLog)
	{
	    return mqMessageLogMapper.insertMqMessageLog(mqMessageLog);
	}
	
	/**
     * 修改MQ消息记录
     * 
     * @param mqMessageLog MQ消息记录信息
     * @return 结果
     */
	@Override
	public int updateMqMessageLog(MqMessageLog mqMessageLog)
	{
	    return mqMessageLogMapper.updateMqMessageLog(mqMessageLog);
	}

	/**
     * 删除MQ消息记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMqMessageLogByIds(String ids)
	{
		return mqMessageLogMapper.deleteMqMessageLogByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<MqMessageLog> query4StatusAndTimeoutMessage() {
		return mqMessageLogMapper.query4StatusAndTimeoutMessage();
	}

	@Transactional
	@Override
	public void update4ReSend(String messageId, Date updateTime) {
		mqMessageLogMapper.update4ReSend(messageId,updateTime);
	}

	@Transactional
	@Override
	public void changeMQMessageLogStatus(String messageId, String status, Date updateTime) {

		mqMessageLogMapper.changeMQMessageLogStatus(messageId, status, updateTime);
	}
	
}
