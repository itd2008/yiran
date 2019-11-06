package com.yiran.payorder.mapper;

import com.yiran.payorder.domain.PayRefundOrder;
import java.util.List;	

/**
 * 退款订单 数据层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface PayRefundOrderMapper 
{
	/**
     * 查询退款订单信息
     * 
     * @param instOrderId 退款订单ID
     * @return 退款订单信息
     */
	public PayRefundOrder selectPayRefundOrderById(Integer instOrderId);
	
	/**
     * 查询退款订单列表
     * 
     * @param payRefundOrder 退款订单信息
     * @return 退款订单集合
     */
	public List<PayRefundOrder> selectPayRefundOrderList(PayRefundOrder payRefundOrder);
	
	/**
     * 新增退款订单
     * 
     * @param payRefundOrder 退款订单信息
     * @return 结果
     */
	public int insertPayRefundOrder(PayRefundOrder payRefundOrder);
	
	/**
     * 修改退款订单
     * 
     * @param payRefundOrder 退款订单信息
     * @return 结果
     */
	public int updatePayRefundOrder(PayRefundOrder payRefundOrder);
	
	/**
     * 删除退款订单
     * 
     * @param instOrderId 退款订单ID
     * @return 结果
     */
	public int deletePayRefundOrderById(Integer instOrderId);
	
	/**
     * 批量删除退款订单
     * 
     * @param instOrderIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePayRefundOrderByIds(String[] instOrderIds);
	
}