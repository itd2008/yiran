package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductAndPromotion;
import java.util.List;

/**
 * 商品促销活动关联 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatProductAndPromotionService 
{
	/**
     * 查询商品促销活动关联信息
     * 
     * @param productAndPromotionId 商品促销活动关联ID
     * @return 商品促销活动关联信息
     */
	public WechatProductAndPromotion selectWechatProductAndPromotionById(Integer productAndPromotionId);
	
	/**
     * 查询商品促销活动关联列表
     * 
     * @param wechatProductAndPromotion 商品促销活动关联信息
     * @return 商品促销活动关联集合
     */
	public List<WechatProductAndPromotion> selectWechatProductAndPromotionList(WechatProductAndPromotion wechatProductAndPromotion);
	
	/**
     * 新增商品促销活动关联
     * 
     * @param wechatProductAndPromotion 商品促销活动关联信息
     * @return 结果
     */
	public int insertWechatProductAndPromotion(WechatProductAndPromotion wechatProductAndPromotion);
	
	/**
     * 修改商品促销活动关联
     * 
     * @param wechatProductAndPromotion 商品促销活动关联信息
     * @return 结果
     */
	public int updateWechatProductAndPromotion(WechatProductAndPromotion wechatProductAndPromotion);
		
	/**
     * 删除商品促销活动关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductAndPromotionByIds(String ids);
	
}
