package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatOrderLogistics;
import java.util.List;	

/**
 * 订单物流 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatOrderLogisticsMapper 
{
	/**
     * 查询订单物流信息
     * 
     * @param orderlogisticsId 订单物流ID
     * @return 订单物流信息
     */
	public WechatOrderLogistics selectWechatOrderLogisticsById(Integer orderlogisticsId);
	
	/**
     * 查询订单物流列表
     * 
     * @param wechatOrderLogistics 订单物流信息
     * @return 订单物流集合
     */
	public List<WechatOrderLogistics> selectWechatOrderLogisticsList(WechatOrderLogistics wechatOrderLogistics);
	
	/**
     * 新增订单物流
     * 
     * @param wechatOrderLogistics 订单物流信息
     * @return 结果
     */
	public int insertWechatOrderLogistics(WechatOrderLogistics wechatOrderLogistics);
	
	/**
     * 修改订单物流
     * 
     * @param wechatOrderLogistics 订单物流信息
     * @return 结果
     */
	public int updateWechatOrderLogistics(WechatOrderLogistics wechatOrderLogistics);
	
	/**
     * 删除订单物流
     * 
     * @param orderlogisticsId 订单物流ID
     * @return 结果
     */
	public int deleteWechatOrderLogisticsById(Integer orderlogisticsId);
	
	/**
     * 批量删除订单物流
     * 
     * @param orderlogisticsIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatOrderLogisticsByIds(String[] orderlogisticsIds);
	
}