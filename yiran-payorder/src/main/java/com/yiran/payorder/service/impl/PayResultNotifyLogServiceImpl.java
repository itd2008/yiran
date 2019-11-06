package com.yiran.payorder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.payorder.mapper.PayResultNotifyLogMapper;
import com.yiran.payorder.domain.PayResultNotifyLog;
import com.yiran.payorder.service.IPayResultNotifyLogService;
import com.yiran.common.support.Convert;

/**
 * 支付结果通知日志 服务层实现
 * 
 * @author yiran
 * @date 2019-08-14
 */
@Service
public class PayResultNotifyLogServiceImpl implements IPayResultNotifyLogService 
{
	@Autowired
	private PayResultNotifyLogMapper payResultNotifyLogMapper;

	/**
     * 查询支付结果通知日志信息
     * 
     * @param notifyRequestId 支付结果通知日志ID
     * @return 支付结果通知日志信息
     */
    @Override
	public PayResultNotifyLog selectPayResultNotifyLogById(Integer notifyRequestId)
	{
	    return payResultNotifyLogMapper.selectPayResultNotifyLogById(notifyRequestId);
	}
	
	/**
     * 查询支付结果通知日志列表
     * 
     * @param payResultNotifyLog 支付结果通知日志信息
     * @return 支付结果通知日志集合
     */
	@Override
	public List<PayResultNotifyLog> selectPayResultNotifyLogList(PayResultNotifyLog payResultNotifyLog)
	{
	    return payResultNotifyLogMapper.selectPayResultNotifyLogList(payResultNotifyLog);
	}
	
    /**
     * 新增支付结果通知日志
     * 
     * @param payResultNotifyLog 支付结果通知日志信息
     * @return 结果
     */
	@Override
	public int insertPayResultNotifyLog(PayResultNotifyLog payResultNotifyLog)
	{
	    return payResultNotifyLogMapper.insertPayResultNotifyLog(payResultNotifyLog);
	}
	
	/**
     * 修改支付结果通知日志
     * 
     * @param payResultNotifyLog 支付结果通知日志信息
     * @return 结果
     */
	@Override
	public int updatePayResultNotifyLog(PayResultNotifyLog payResultNotifyLog)
	{
	    return payResultNotifyLogMapper.updatePayResultNotifyLog(payResultNotifyLog);
	}

	/**
     * 删除支付结果通知日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayResultNotifyLogByIds(String ids)
	{
		return payResultNotifyLogMapper.deletePayResultNotifyLogByIds(Convert.toStrArray(ids));
	}
	
}
