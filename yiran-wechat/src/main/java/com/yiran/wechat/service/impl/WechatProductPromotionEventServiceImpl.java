package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductPromotionEventMapper;
import com.yiran.wechat.domain.WechatProductPromotionEvent;
import com.yiran.wechat.service.IWechatProductPromotionEventService;
import com.yiran.common.support.Convert;

/**
 * 商品促销活动 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductPromotionEventServiceImpl implements IWechatProductPromotionEventService 
{
	@Autowired
	private WechatProductPromotionEventMapper wechatProductPromotionEventMapper;

	/**
     * 查询商品促销活动信息
     * 
     * @param productPromotionEventId 商品促销活动ID
     * @return 商品促销活动信息
     */
    @Override
	public WechatProductPromotionEvent selectWechatProductPromotionEventById(Integer productPromotionEventId)
	{
	    return wechatProductPromotionEventMapper.selectWechatProductPromotionEventById(productPromotionEventId);
	}
	
	/**
     * 查询商品促销活动列表
     * 
     * @param wechatProductPromotionEvent 商品促销活动信息
     * @return 商品促销活动集合
     */
	@Override
	public List<WechatProductPromotionEvent> selectWechatProductPromotionEventList(WechatProductPromotionEvent wechatProductPromotionEvent)
	{
	    return wechatProductPromotionEventMapper.selectWechatProductPromotionEventList(wechatProductPromotionEvent);
	}
	
    /**
     * 新增商品促销活动
     * 
     * @param wechatProductPromotionEvent 商品促销活动信息
     * @return 结果
     */
	@Override
	public int insertWechatProductPromotionEvent(WechatProductPromotionEvent wechatProductPromotionEvent)
	{
	    return wechatProductPromotionEventMapper.insertWechatProductPromotionEvent(wechatProductPromotionEvent);
	}
	
	/**
     * 修改商品促销活动
     * 
     * @param wechatProductPromotionEvent 商品促销活动信息
     * @return 结果
     */
	@Override
	public int updateWechatProductPromotionEvent(WechatProductPromotionEvent wechatProductPromotionEvent)
	{
	    return wechatProductPromotionEventMapper.updateWechatProductPromotionEvent(wechatProductPromotionEvent);
	}

	/**
     * 删除商品促销活动对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductPromotionEventByIds(String ids)
	{
		return wechatProductPromotionEventMapper.deleteWechatProductPromotionEventByIds(Convert.toStrArray(ids));
	}
	
}
