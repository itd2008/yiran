package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductStatisticsMapper;
import com.yiran.wechat.domain.WechatProductStatistics;
import com.yiran.wechat.service.IWechatProductStatisticsService;
import com.yiran.common.support.Convert;

/**
 * 商品统计 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductStatisticsServiceImpl implements IWechatProductStatisticsService 
{
	@Autowired
	private WechatProductStatisticsMapper wechatProductStatisticsMapper;

	/**
     * 查询商品统计信息
     * 
     * @param productId 商品统计ID
     * @return 商品统计信息
     */
    @Override
	public WechatProductStatistics selectWechatProductStatisticsById(Integer productId)
	{
	    return wechatProductStatisticsMapper.selectWechatProductStatisticsById(productId);
	}
	
	/**
     * 查询商品统计列表
     * 
     * @param wechatProductStatistics 商品统计信息
     * @return 商品统计集合
     */
	@Override
	public List<WechatProductStatistics> selectWechatProductStatisticsList(WechatProductStatistics wechatProductStatistics)
	{
	    return wechatProductStatisticsMapper.selectWechatProductStatisticsList(wechatProductStatistics);
	}
	
    /**
     * 新增商品统计
     * 
     * @param wechatProductStatistics 商品统计信息
     * @return 结果
     */
	@Override
	public int insertWechatProductStatistics(WechatProductStatistics wechatProductStatistics)
	{
	    return wechatProductStatisticsMapper.insertWechatProductStatistics(wechatProductStatistics);
	}
	
	/**
     * 修改商品统计
     * 
     * @param wechatProductStatistics 商品统计信息
     * @return 结果
     */
	@Override
	public int updateWechatProductStatistics(WechatProductStatistics wechatProductStatistics)
	{
	    return wechatProductStatisticsMapper.updateWechatProductStatistics(wechatProductStatistics);
	}

	/**
     * 删除商品统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductStatisticsByIds(String ids)
	{
		return wechatProductStatisticsMapper.deleteWechatProductStatisticsByIds(Convert.toStrArray(ids));
	}
	
}
