package com.yiran.message.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.message.mapper.NotifyMsgLogMapper;
import com.yiran.message.domain.NotifyMsgLog;
import com.yiran.message.service.INotifyMsgLogService;
import com.yiran.common.support.Convert;

/**
 * 消息通知日志 服务层实现
 * 
 * @author yiran
 * @date 2019-03-08
 */
@Service
public class NotifyMsgLogServiceImpl implements INotifyMsgLogService 
{
	@Autowired
	private NotifyMsgLogMapper notifyMsgLogMapper;

	/**
     * 查询消息通知日志信息
     * 
     * @param id 消息通知日志ID
     * @return 消息通知日志信息
     */
    @Override
	public NotifyMsgLog selectNotifyMsgLogById(Integer id)
	{
	    return notifyMsgLogMapper.selectNotifyMsgLogById(id);
	}
	
	/**
     * 查询消息通知日志列表
     * 
     * @param notifyMsgLog 消息通知日志信息
     * @return 消息通知日志集合
     */
	@Override
	public List<NotifyMsgLog> selectNotifyMsgLogList(NotifyMsgLog notifyMsgLog)
	{
	    return notifyMsgLogMapper.selectNotifyMsgLogList(notifyMsgLog);
	}
	
    /**
     * 新增消息通知日志
     * 
     * @param notifyMsgLog 消息通知日志信息
     * @return 结果
     */
	@Override
	public int insertNotifyMsgLog(NotifyMsgLog notifyMsgLog)
	{
		notifyMsgLog.setDelFlag("0");
	    return notifyMsgLogMapper.insertNotifyMsgLog(notifyMsgLog);
	}
	
	/**
     * 修改消息通知日志
     * 
     * @param notifyMsgLog 消息通知日志信息
     * @return 结果
     */
	@Override
	public int updateNotifyMsgLog(NotifyMsgLog notifyMsgLog)
	{
		notifyMsgLog.setResponseDate(new Date());
	    return notifyMsgLogMapper.updateNotifyMsgLog(notifyMsgLog);
	}

	/**
     * 删除消息通知日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteNotifyMsgLogByIds(String ids)
	{
		return notifyMsgLogMapper.deleteNotifyMsgLogByIds(Convert.toStrArray(ids));
	}

	@Override
	public NotifyMsgLog selectMsgLogByMsgOrderNo(String msgOrderNo) {
		return notifyMsgLogMapper.selectMsgLogByMsgOrderNo(msgOrderNo);
	}

	@Override
	public int getSendSmSCount(String beginDate, String endDate, String phone) {
		return notifyMsgLogMapper.getSendSmSCount(beginDate, endDate, phone);
	}
	
}
