package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatOrderDetailMapper;
import com.yiran.wechat.domain.WechatOrderDetail;
import com.yiran.wechat.service.IWechatOrderDetailService;
import com.yiran.common.support.Convert;

/**
 * 订单商品详情 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatOrderDetailServiceImpl implements IWechatOrderDetailService 
{
	@Autowired
	private WechatOrderDetailMapper wechatOrderDetailMapper;

	/**
     * 查询订单商品详情信息
     * 
     * @param orderDetailId 订单商品详情ID
     * @return 订单商品详情信息
     */
    @Override
	public WechatOrderDetail selectWechatOrderDetailById(Integer orderDetailId)
	{
	    return wechatOrderDetailMapper.selectWechatOrderDetailById(orderDetailId);
	}
	
	/**
     * 查询订单商品详情列表
     * 
     * @param wechatOrderDetail 订单商品详情信息
     * @return 订单商品详情集合
     */
	@Override
	public List<WechatOrderDetail> selectWechatOrderDetailList(WechatOrderDetail wechatOrderDetail)
	{
	    return wechatOrderDetailMapper.selectWechatOrderDetailList(wechatOrderDetail);
	}
	
    /**
     * 新增订单商品详情
     * 
     * @param wechatOrderDetail 订单商品详情信息
     * @return 结果
     */
	@Override
	public int insertWechatOrderDetail(WechatOrderDetail wechatOrderDetail)
	{
	    return wechatOrderDetailMapper.insertWechatOrderDetail(wechatOrderDetail);
	}
	
	/**
     * 修改订单商品详情
     * 
     * @param wechatOrderDetail 订单商品详情信息
     * @return 结果
     */
	@Override
	public int updateWechatOrderDetail(WechatOrderDetail wechatOrderDetail)
	{
	    return wechatOrderDetailMapper.updateWechatOrderDetail(wechatOrderDetail);
	}

	/**
     * 删除订单商品详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatOrderDetailByIds(String ids)
	{
		return wechatOrderDetailMapper.deleteWechatOrderDetailByIds(Convert.toStrArray(ids));
	}

	@Override
	public int updateOrderDetilIsCommentStatus(String orderId,String productId) {
		return wechatOrderDetailMapper.updateOrderDetilIsCommentStatus(orderId,Integer.parseInt(productId));
	}
	
}
