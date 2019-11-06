package com.yiran.payorder.service;

import com.yiran.payorder.domain.PayRefundOrder;
import java.util.List;

/**
 * 退款订单 服务层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface IPayRefundOrderService 
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
     * 删除退款订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayRefundOrderByIds(String ids);
	
}
