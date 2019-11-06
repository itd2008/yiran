package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatOrderDetail;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 订单商品详情 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatOrderDetailMapper 
{
	/**
     * 查询订单商品详情信息
     * 
     * @param orderDetailId 订单商品详情ID
     * @return 订单商品详情信息
     */
	public WechatOrderDetail selectWechatOrderDetailById(Integer orderDetailId);
	
	/**
     * 查询订单商品详情列表
     * 
     * @param wechatOrderDetail 订单商品详情信息
     * @return 订单商品详情集合
     */
	public List<WechatOrderDetail> selectWechatOrderDetailList(WechatOrderDetail wechatOrderDetail);
	
	/**
     * 新增订单商品详情
     * 
     * @param wechatOrderDetail 订单商品详情信息
     * @return 结果
     */
	public int insertWechatOrderDetail(WechatOrderDetail wechatOrderDetail);
	
	/**
     * 修改订单商品详情
     * 
     * @param wechatOrderDetail 订单商品详情信息
     * @return 结果
     */
	public int updateWechatOrderDetail(WechatOrderDetail wechatOrderDetail);
	
	/**
     * 删除订单商品详情
     * 
     * @param orderDetailId 订单商品详情ID
     * @return 结果
     */
	public int deleteWechatOrderDetailById(Integer orderDetailId);
	
	/**
     * 批量删除订单商品详情
     * 
     * @param orderDetailIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatOrderDetailByIds(String[] orderDetailIds);

	public int updateOrderDetilIsCommentStatus(
			@Param("orderId")String orderId,
			@Param("productId") int productId);
	
}