package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatOrderMapper;
import com.yiran.wechat.domain.WechatOrder;
import com.yiran.wechat.service.IWechatOrderService;
import com.yiran.common.support.Convert;

/**
 * 订单 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatOrderServiceImpl implements IWechatOrderService 
{
	@Autowired
	private WechatOrderMapper wechatOrderMapper;

	/**
     * 查询订单信息
     * 
     * @param orderId 订单ID
     * @return 订单信息
     */
    @Override
	public WechatOrder selectWechatOrderById(Integer orderId)
	{
	    return wechatOrderMapper.selectWechatOrderById(orderId);
	}
	
	/**
     * 查询订单列表
     * 
     * @param wechatOrder 订单信息
     * @return 订单集合
     */
	@Override
	public List<WechatOrder> selectWechatOrderList(WechatOrder wechatOrder)
	{
	    return wechatOrderMapper.selectWechatOrderList(wechatOrder);
	}
	
    /**
     * 新增订单
     * 
     * @param wechatOrder 订单信息
     * @return 结果
     */
	@Override
	public int insertWechatOrder(WechatOrder wechatOrder)
	{
	    return wechatOrderMapper.insertWechatOrder(wechatOrder);
	}
	
	/**
     * 修改订单
     * 
     * @param wechatOrder 订单信息
     * @return 结果
     */
	@Override
	public int updateWechatOrder(WechatOrder wechatOrder)
	{
	    return wechatOrderMapper.updateWechatOrder(wechatOrder);
	}

	/**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatOrderByIds(String ids)
	{
		return wechatOrderMapper.deleteWechatOrderByIds(Convert.toStrArray(ids));
	}

	@Override
	public int updateOrderStatustranSactionCompleted(String orderId) {
		return wechatOrderMapper.updateOrderStatustranSactionCompleted(orderId);
	}

	@Override
	public int updateOrderStatus(String orderNo, String openId, String orderStatus) {
		return wechatOrderMapper.updateOrderStatus(orderNo, openId, orderStatus);
	}

	@Override
	public WechatOrder selectOrderByOrderNo(String orderNo) {
		return wechatOrderMapper.selectOrderByOrderNo(orderNo);
	}

	@Override
	public int getOrderNumsUnshipped() {
		return wechatOrderMapper.getOrderNumsUnshipped();
	}
	
}
