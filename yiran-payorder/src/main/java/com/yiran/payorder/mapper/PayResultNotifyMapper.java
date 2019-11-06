package com.yiran.payorder.mapper;

import com.yiran.payorder.domain.PayResultNotify;
import java.util.List;	

/**
 * 支付结果通知 数据层
 * 
 * @author yiran
 * @date 2019-08-14
 */
public interface PayResultNotifyMapper 
{
	/**
     * 查询支付结果通知信息
     * 
     * @param notifyId 支付结果通知ID
     * @return 支付结果通知信息
     */
	public PayResultNotify selectPayResultNotifyById(String notifyId);
	
	/**
     * 查询支付结果通知列表
     * 
     * @param payResultNotify 支付结果通知信息
     * @return 支付结果通知集合
     */
	public List<PayResultNotify> selectPayResultNotifyList(PayResultNotify payResultNotify);
	
	/**
     * 新增支付结果通知
     * 
     * @param payResultNotify 支付结果通知信息
     * @return 结果
     */
	public int insertPayResultNotify(PayResultNotify payResultNotify);
	
	/**
     * 修改支付结果通知
     * 
     * @param payResultNotify 支付结果通知信息
     * @return 结果
     */
	public int updatePayResultNotify(PayResultNotify payResultNotify);
	
	/**
     * 删除支付结果通知
     * 
     * @param notifyId 支付结果通知ID
     * @return 结果
     */
	public int deletePayResultNotifyById(String notifyId);
	
	/**
     * 批量删除支付结果通知
     * 
     * @param notifyIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePayResultNotifyByIds(String[] notifyIds);

	public PayResultNotify selectPayResultNotifyByPaymentSeqNo(String paymentSeqNo);

	public List<PayResultNotify> selectPayResultNotifyListIsFail();
	
}