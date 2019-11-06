package com.yiran.payorder.service;

import com.yiran.payorder.domain.PayResultNotifyLog;
import java.util.List;

/**
 * 支付结果通知日志 服务层
 * 
 * @author yiran
 * @date 2019-08-14
 */
public interface IPayResultNotifyLogService 
{
	/**
     * 查询支付结果通知日志信息
     * 
     * @param notifyRequestId 支付结果通知日志ID
     * @return 支付结果通知日志信息
     */
	public PayResultNotifyLog selectPayResultNotifyLogById(Integer notifyRequestId);
	
	/**
     * 查询支付结果通知日志列表
     * 
     * @param payResultNotifyLog 支付结果通知日志信息
     * @return 支付结果通知日志集合
     */
	public List<PayResultNotifyLog> selectPayResultNotifyLogList(PayResultNotifyLog payResultNotifyLog);
	
	/**
     * 新增支付结果通知日志
     * 
     * @param payResultNotifyLog 支付结果通知日志信息
     * @return 结果
     */
	public int insertPayResultNotifyLog(PayResultNotifyLog payResultNotifyLog);
	
	/**
     * 修改支付结果通知日志
     * 
     * @param payResultNotifyLog 支付结果通知日志信息
     * @return 结果
     */
	public int updatePayResultNotifyLog(PayResultNotifyLog payResultNotifyLog);
		
	/**
     * 删除支付结果通知日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayResultNotifyLogByIds(String ids);
	
}
