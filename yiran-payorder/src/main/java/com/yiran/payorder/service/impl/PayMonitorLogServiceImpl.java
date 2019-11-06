package com.yiran.payorder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.payorder.mapper.PayMonitorLogMapper;
import com.yiran.payorder.domain.PayMonitorLog;
import com.yiran.payorder.service.IPayMonitorLogService;
import com.yiran.common.support.Convert;

/**
 * 监控日志 服务层实现
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Service
public class PayMonitorLogServiceImpl implements IPayMonitorLogService 
{
	@Autowired
	private PayMonitorLogMapper payMonitorLogMapper;

	/**
     * 查询监控日志信息
     * 
     * @param logId 监控日志ID
     * @return 监控日志信息
     */
    @Override
	public PayMonitorLog selectPayMonitorLogById(Integer logId)
	{
	    return payMonitorLogMapper.selectPayMonitorLogById(logId);
	}
	
	/**
     * 查询监控日志列表
     * 
     * @param payMonitorLog 监控日志信息
     * @return 监控日志集合
     */
	@Override
	public List<PayMonitorLog> selectPayMonitorLogList(PayMonitorLog payMonitorLog)
	{
	    return payMonitorLogMapper.selectPayMonitorLogList(payMonitorLog);
	}
	
    /**
     * 新增监控日志
     * 
     * @param payMonitorLog 监控日志信息
     * @return 结果
     */
	@Override
	public int insertPayMonitorLog(PayMonitorLog payMonitorLog)
	{
	    return payMonitorLogMapper.insertPayMonitorLog(payMonitorLog);
	}
	
	/**
     * 修改监控日志
     * 
     * @param payMonitorLog 监控日志信息
     * @return 结果
     */
	@Override
	public int updatePayMonitorLog(PayMonitorLog payMonitorLog)
	{
	    return payMonitorLogMapper.updatePayMonitorLog(payMonitorLog);
	}

	/**
     * 删除监控日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayMonitorLogByIds(String ids)
	{
		return payMonitorLogMapper.deletePayMonitorLogByIds(Convert.toStrArray(ids));
	}
	
}
