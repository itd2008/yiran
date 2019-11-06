package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatOrderLogisticsMapper;
import com.yiran.wechat.domain.WechatOrderLogistics;
import com.yiran.wechat.service.IWechatOrderLogisticsService;
import com.yiran.common.support.Convert;

/**
 * 订单物流 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatOrderLogisticsServiceImpl implements IWechatOrderLogisticsService 
{
	@Autowired
	private WechatOrderLogisticsMapper wechatOrderLogisticsMapper;

	/**
     * 查询订单物流信息
     * 
     * @param orderlogisticsId 订单物流ID
     * @return 订单物流信息
     */
    @Override
	public WechatOrderLogistics selectWechatOrderLogisticsById(Integer orderlogisticsId)
	{
	    return wechatOrderLogisticsMapper.selectWechatOrderLogisticsById(orderlogisticsId);
	}
	
	/**
     * 查询订单物流列表
     * 
     * @param wechatOrderLogistics 订单物流信息
     * @return 订单物流集合
     */
	@Override
	public List<WechatOrderLogistics> selectWechatOrderLogisticsList(WechatOrderLogistics wechatOrderLogistics)
	{
	    return wechatOrderLogisticsMapper.selectWechatOrderLogisticsList(wechatOrderLogistics);
	}
	
    /**
     * 新增订单物流
     * 
     * @param wechatOrderLogistics 订单物流信息
     * @return 结果
     */
	@Override
	public int insertWechatOrderLogistics(WechatOrderLogistics wechatOrderLogistics)
	{
	    return wechatOrderLogisticsMapper.insertWechatOrderLogistics(wechatOrderLogistics);
	}
	
	/**
     * 修改订单物流
     * 
     * @param wechatOrderLogistics 订单物流信息
     * @return 结果
     */
	@Override
	public int updateWechatOrderLogistics(WechatOrderLogistics wechatOrderLogistics)
	{
	    return wechatOrderLogisticsMapper.updateWechatOrderLogistics(wechatOrderLogistics);
	}

	/**
     * 删除订单物流对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatOrderLogisticsByIds(String ids)
	{
		return wechatOrderLogisticsMapper.deleteWechatOrderLogisticsByIds(Convert.toStrArray(ids));
	}
	
}
