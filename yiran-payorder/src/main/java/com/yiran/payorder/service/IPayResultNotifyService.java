package com.yiran.payorder.service;

import com.yiran.payorder.domain.PayResultNotify;
import java.util.List;

/**
 * 支付结果通知 服务层
 * 
 * @author yiran
 * @date 2019-08-14
 */
public interface IPayResultNotifyService 
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
     * 删除支付结果通知信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayResultNotifyByIds(String ids);

	/**
	 * 根据支付流水号查询通知结果
	 * @param paymentSeqNo
	 * @return
	 */
	public PayResultNotify selectPayResultNotifyByPaymentSeqNo(String paymentSeqNo);

	public List<PayResultNotify> selectPayResultNotifyListIsFail();
	
}
