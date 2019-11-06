package com.yiran.payorder.service;

import com.yiran.payorder.domain.PayMonitorLog;
import java.util.List;

/**
 * 监控日志 服务层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface IPayMonitorLogService 
{
	/**
     * 查询监控日志信息
     * 
     * @param logId 监控日志ID
     * @return 监控日志信息
     */
	public PayMonitorLog selectPayMonitorLogById(Integer logId);
	
	/**
     * 查询监控日志列表
     * 
     * @param payMonitorLog 监控日志信息
     * @return 监控日志集合
     */
	public List<PayMonitorLog> selectPayMonitorLogList(PayMonitorLog payMonitorLog);
	
	/**
     * 新增监控日志
     * 
     * @param payMonitorLog 监控日志信息
     * @return 结果
     */
	public int insertPayMonitorLog(PayMonitorLog payMonitorLog);
	
	/**
     * 修改监控日志
     * 
     * @param payMonitorLog 监控日志信息
     * @return 结果
     */
	public int updatePayMonitorLog(PayMonitorLog payMonitorLog);
		
	/**
     * 删除监控日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayMonitorLogByIds(String ids);
	
}
