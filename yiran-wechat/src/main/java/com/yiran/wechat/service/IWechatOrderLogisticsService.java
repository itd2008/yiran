package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatOrderLogistics;
import java.util.List;

/**
 * 订单物流 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatOrderLogisticsService 
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
     * 删除订单物流信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatOrderLogisticsByIds(String ids);
	
}
