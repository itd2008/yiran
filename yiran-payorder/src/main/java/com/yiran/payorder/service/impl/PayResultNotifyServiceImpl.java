package com.yiran.payorder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.payorder.mapper.PayResultNotifyMapper;
import com.yiran.payorder.domain.PayResultNotify;
import com.yiran.payorder.service.IPayResultNotifyService;
import com.yiran.common.support.Convert;

/**
 * 支付结果通知 服务层实现
 * 
 * @author yiran
 * @date 2019-08-14
 */
@Service
public class PayResultNotifyServiceImpl implements IPayResultNotifyService 
{
	@Autowired
	private PayResultNotifyMapper payResultNotifyMapper;

	/**
     * 查询支付结果通知信息
     * 
     * @param notifyId 支付结果通知ID
     * @return 支付结果通知信息
     */
    @Override
	public PayResultNotify selectPayResultNotifyById(String notifyId)
	{
	    return payResultNotifyMapper.selectPayResultNotifyById(notifyId);
	}
	
	/**
     * 查询支付结果通知列表
     * 
     * @param payResultNotify 支付结果通知信息
     * @return 支付结果通知集合
     */
	@Override
	public List<PayResultNotify> selectPayResultNotifyList(PayResultNotify payResultNotify)
	{
	    return payResultNotifyMapper.selectPayResultNotifyList(payResultNotify);
	}
	
    /**
     * 新增支付结果通知
     * 
     * @param payResultNotify 支付结果通知信息
     * @return 结果
     */
	@Override
	public int insertPayResultNotify(PayResultNotify payResultNotify)
	{
	    return payResultNotifyMapper.insertPayResultNotify(payResultNotify);
	}
	
	/**
     * 修改支付结果通知
     * 
     * @param payResultNotify 支付结果通知信息
     * @return 结果
     */
	@Override
	public int updatePayResultNotify(PayResultNotify payResultNotify)
	{
	    return payResultNotifyMapper.updatePayResultNotify(payResultNotify);
	}

	/**
     * 删除支付结果通知对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayResultNotifyByIds(String ids)
	{
		return payResultNotifyMapper.deletePayResultNotifyByIds(Convert.toStrArray(ids));
	}

	@Override
	public PayResultNotify selectPayResultNotifyByPaymentSeqNo(String paymentSeqNo) {
		return payResultNotifyMapper.selectPayResultNotifyByPaymentSeqNo(paymentSeqNo);
	}

	@Override
	public List<PayResultNotify> selectPayResultNotifyListIsFail() {
		return payResultNotifyMapper.selectPayResultNotifyListIsFail();
	}
	
}
