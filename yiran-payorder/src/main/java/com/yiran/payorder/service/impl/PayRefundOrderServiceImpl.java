package com.yiran.payorder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.payorder.mapper.PayRefundOrderMapper;
import com.yiran.payorder.domain.PayRefundOrder;
import com.yiran.payorder.service.IPayRefundOrderService;
import com.yiran.common.support.Convert;

/**
 * 退款订单 服务层实现
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Service
public class PayRefundOrderServiceImpl implements IPayRefundOrderService 
{
	@Autowired
	private PayRefundOrderMapper payRefundOrderMapper;

	/**
     * 查询退款订单信息
     * 
     * @param instOrderId 退款订单ID
     * @return 退款订单信息
     */
    @Override
	public PayRefundOrder selectPayRefundOrderById(Integer instOrderId)
	{
	    return payRefundOrderMapper.selectPayRefundOrderById(instOrderId);
	}
	
	/**
     * 查询退款订单列表
     * 
     * @param payRefundOrder 退款订单信息
     * @return 退款订单集合
     */
	@Override
	public List<PayRefundOrder> selectPayRefundOrderList(PayRefundOrder payRefundOrder)
	{
	    return payRefundOrderMapper.selectPayRefundOrderList(payRefundOrder);
	}
	
    /**
     * 新增退款订单
     * 
     * @param payRefundOrder 退款订单信息
     * @return 结果
     */
	@Override
	public int insertPayRefundOrder(PayRefundOrder payRefundOrder)
	{
	    return payRefundOrderMapper.insertPayRefundOrder(payRefundOrder);
	}
	
	/**
     * 修改退款订单
     * 
     * @param payRefundOrder 退款订单信息
     * @return 结果
     */
	@Override
	public int updatePayRefundOrder(PayRefundOrder payRefundOrder)
	{
	    return payRefundOrderMapper.updatePayRefundOrder(payRefundOrder);
	}

	/**
     * 删除退款订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayRefundOrderByIds(String ids)
	{
		return payRefundOrderMapper.deletePayRefundOrderByIds(Convert.toStrArray(ids));
	}
	
}
