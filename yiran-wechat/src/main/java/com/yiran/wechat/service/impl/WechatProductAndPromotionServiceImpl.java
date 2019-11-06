package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductAndPromotionMapper;
import com.yiran.wechat.domain.WechatProductAndPromotion;
import com.yiran.wechat.service.IWechatProductAndPromotionService;
import com.yiran.common.support.Convert;

/**
 * 商品促销活动关联 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductAndPromotionServiceImpl implements IWechatProductAndPromotionService 
{
	@Autowired
	private WechatProductAndPromotionMapper wechatProductAndPromotionMapper;

	/**
     * 查询商品促销活动关联信息
     * 
     * @param productAndPromotionId 商品促销活动关联ID
     * @return 商品促销活动关联信息
     */
    @Override
	public WechatProductAndPromotion selectWechatProductAndPromotionById(Integer productAndPromotionId)
	{
	    return wechatProductAndPromotionMapper.selectWechatProductAndPromotionById(productAndPromotionId);
	}
	
	/**
     * 查询商品促销活动关联列表
     * 
     * @param wechatProductAndPromotion 商品促销活动关联信息
     * @return 商品促销活动关联集合
     */
	@Override
	public List<WechatProductAndPromotion> selectWechatProductAndPromotionList(WechatProductAndPromotion wechatProductAndPromotion)
	{
	    return wechatProductAndPromotionMapper.selectWechatProductAndPromotionList(wechatProductAndPromotion);
	}
	
    /**
     * 新增商品促销活动关联
     * 
     * @param wechatProductAndPromotion 商品促销活动关联信息
     * @return 结果
     */
	@Override
	public int insertWechatProductAndPromotion(WechatProductAndPromotion wechatProductAndPromotion)
	{
	    return wechatProductAndPromotionMapper.insertWechatProductAndPromotion(wechatProductAndPromotion);
	}
	
	/**
     * 修改商品促销活动关联
     * 
     * @param wechatProductAndPromotion 商品促销活动关联信息
     * @return 结果
     */
	@Override
	public int updateWechatProductAndPromotion(WechatProductAndPromotion wechatProductAndPromotion)
	{
	    return wechatProductAndPromotionMapper.updateWechatProductAndPromotion(wechatProductAndPromotion);
	}

	/**
     * 删除商品促销活动关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductAndPromotionByIds(String ids)
	{
		return wechatProductAndPromotionMapper.deleteWechatProductAndPromotionByIds(Convert.toStrArray(ids));
	}
	
}
