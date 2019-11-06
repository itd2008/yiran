package com.yiran.message.mapper;

import com.yiran.message.domain.NotifyMsgLog;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 消息通知日志 数据层
 * 
 * @author yiran
 * @date 2019-03-08
 */
public interface NotifyMsgLogMapper 
{
	/**
     * 查询消息通知日志信息
     * 
     * @param id 消息通知日志ID
     * @return 消息通知日志信息
     */
	public NotifyMsgLog selectNotifyMsgLogById(Integer id);
	
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
     * 删除消息通知日志
     * 
     * @param id 消息通知日志ID
     * @return 结果
     */
	public int deleteNotifyMsgLogById(Integer id);
	
	/**
     * 批量删除消息通知日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNotifyMsgLogByIds(String[] ids);
	
	/**
	 * 根据流水号查询对象
	 * @param msgOrderNo
	 * @return
	 */
	public NotifyMsgLog selectMsgLogByMsgOrderNo(String msgOrderNo);

	/**
	 * 获取某个手机号每天发送短信次数
	 * @param beginDate
	 * @param endDate
	 * @param phone
	 * @return
	 */
	public int getSendSmSCount(@Param("beginDate")String beginDate,@Param("endDate")String endDate, @Param("phone")String phone);
	
	
}