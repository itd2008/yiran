package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatProductPromotionEvent;
import java.util.List;	

/**
 * 商品促销活动 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatProductPromotionEventMapper 
{
	/**
     * 查询商品促销活动信息
     * 
     * @param productPromotionEventId 商品促销活动ID
     * @return 商品促销活动信息
     */
	public WechatProductPromotionEvent selectWechatProductPromotionEventById(Integer productPromotionEventId);
	
	/**
     * 查询商品促销活动列表
     * 
     * @param wechatProductPromotionEvent 商品促销活动信息
     * @return 商品促销活动集合
     */
	public List<WechatProductPromotionEvent> selectWechatProductPromotionEventList(WechatProductPromotionEvent wechatProductPromotionEvent);
	
	/**
     * 新增商品促销活动
     * 
     * @param wechatProductPromotionEvent 商品促销活动信息
     * @return 结果
     */
	public int insertWechatProductPromotionEvent(WechatProductPromotionEvent wechatProductPromotionEvent);
	
	/**
     * 修改商品促销活动
     * 
     * @param wechatProductPromotionEvent 商品促销活动信息
     * @return 结果
     */
	public int updateWechatProductPromotionEvent(WechatProductPromotionEvent wechatProductPromotionEvent);
	
	/**
     * 删除商品促销活动
     * 
     * @param productPromotionEventId 商品促销活动ID
     * @return 结果
     */
	public int deleteWechatProductPromotionEventById(Integer productPromotionEventId);
	
	/**
     * 批量删除商品促销活动
     * 
     * @param productPromotionEventIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductPromotionEventByIds(String[] productPromotionEventIds);
	
}