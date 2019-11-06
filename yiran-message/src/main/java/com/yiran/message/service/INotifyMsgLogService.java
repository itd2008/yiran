package com.yiran.message.service;

import com.yiran.message.domain.NotifyMsgLog;
import java.util.List;

/**
 * 消息通知日志 服务层
 * 
 * @author yiran
 * @date 2019-03-08
 */
public interface INotifyMsgLogService 
{
	/**
     * 查询消息通知日志信息
     * 
     * @param id 消息通知日志ID
     * @return 消息通知日志信息
     */
	public NotifyMsgLog selectNotifyMsgLogById(Integer id);
	
	/**
	 * 根据消息流水号查询
	 * @param msgOrderNo
	 * @return
	 */
	public NotifyMsgLog selectMsgLogByMsgOrderNo(String msgOrderNo);
	
	/**
     * 查询消息通知日志列表
     * 
     * @param notifyMsgLog 消息通知日志信息
     * @return 消息通知日志集合
     */
	public List<NotifyMsgLog> selectNotifyMsgLogList(NotifyMsgLog notifyMsgLog);
	
	/**
     * 新增消息通知日志
     * 
     * @param notifyMsgLog 消息通知日志信息
     * @return 结果
     */
	public int insertNotifyMsgLog(NotifyMsgLog notifyMsgLog);
	
	/**
     * 修改消息通知日志
     * 
     * @param notifyMsgLog 消息通知日志信息
     * @return 结果
     */
	public int updateNotifyMsgLog(NotifyMsgLog notifyMsgLog);
		
	/**
     * 删除消息通知日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNotifyMsgLogByIds(String ids);
	
	/**
	 * 获取这个手机号每天发送短信次数
	 * @param beginDate
	 * @param endDate
	 * @param phone
	 * @return
	 */
	public int getSendSmSCount(String beginDate,String endDate,String phone);
	
}
