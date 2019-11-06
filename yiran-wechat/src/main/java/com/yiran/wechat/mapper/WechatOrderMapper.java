package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatOrder;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 订单 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatOrderMapper 
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
     * 删除订单
     * 
     * @param orderId 订单ID
     * @return 结果
     */
	public int deleteWechatOrderById(Integer orderId);
	
	/**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatOrderByIds(String[] orderIds);

	public int updateOrderStatustranSactionCompleted(String productId);

	public int updateOrderStatus(
			@Param("orderNo")String orderNo, 
			@Param("openId")String openId, 
			@Param("orderStatus")String orderStatus);

	public WechatOrder selectOrderByOrderNo(String orderNo);

	public int getOrderNumsUnshipped();
	
}