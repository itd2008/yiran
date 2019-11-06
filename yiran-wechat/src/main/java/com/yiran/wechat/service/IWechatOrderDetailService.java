package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatOrderDetail;
import java.util.List;

/**
 * 订单商品详情 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatOrderDetailService 
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
     * 删除订单商品详情信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatOrderDetailByIds(String ids);

	public int updateOrderDetilIsCommentStatus(String orderId,String productId);
	
}
