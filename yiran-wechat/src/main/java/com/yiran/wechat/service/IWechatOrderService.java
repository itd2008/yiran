package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatOrder;
import java.util.List;

/**
 * 订单 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatOrderService 
{
	/**
     * 查询订单信息
     * 
     * @param orderId 订单ID
     * @return 订单信息
     */
	public WechatOrder selectWechatOrderById(Integer orderId);
	
	/**
     * 查询订单列表
     * 
     * @param wechatOrder 订单信息
     * @return 订单集合
     */
	public List<WechatOrder> selectWechatOrderList(WechatOrder wechatOrder);
	
	/**
     * 新增订单
     * 
     * @param wechatOrder 订单信息
     * @return 结果
     */
	public int insertWechatOrder(WechatOrder wechatOrder);
	
	/**
     * 修改订单
     * 
     * @param wechatOrder 订单信息
     * @return 结果
     */
	public int updateWechatOrder(WechatOrder wechatOrder);
		
	/**
     * 删除订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatOrderByIds(String ids);

	public int updateOrderStatustranSactionCompleted(String orderId);

	/**
	 * 更新订单状态
	 * @param orderNo
	 * @param openId
	 * @param orderStatus
	 * @return
	 */
	public int updateOrderStatus(String orderNo, String openId, String orderStatus);

	public WechatOrder selectOrderByOrderNo(String orderNo);

	public int getOrderNumsUnshipped();
	
}
