package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatShoppingCartMapper;
import com.yiran.wechat.domain.WechatShoppingCart;
import com.yiran.wechat.service.IWechatShoppingCartService;
import com.yiran.common.support.Convert;

/**
 * 购物车 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatShoppingCartServiceImpl implements IWechatShoppingCartService 
{
	@Autowired
	private WechatShoppingCartMapper wechatShoppingCartMapper;

	/**
     * 查询购物车信息
     * 
     * @param id 购物车ID
     * @return 购物车信息
     */
    @Override
	public WechatShoppingCart selectWechatShoppingCartById(Integer id)
	{
	    return wechatShoppingCartMapper.selectWechatShoppingCartById(id);
	}
	
	/**
     * 查询购物车列表
     * 
     * @param wechatShoppingCart 购物车信息
     * @return 购物车集合
     */
	@Override
	public List<WechatShoppingCart> selectWechatShoppingCartList(WechatShoppingCart wechatShoppingCart)
	{
	    return wechatShoppingCartMapper.selectWechatShoppingCartList(wechatShoppingCart);
	}
	
    /**
     * 新增购物车
     * 
     * @param wechatShoppingCart 购物车信息
     * @return 结果
     */
	@Override
	public int insertWechatShoppingCart(WechatShoppingCart wechatShoppingCart)
	{
	    return wechatShoppingCartMapper.insertWechatShoppingCart(wechatShoppingCart);
	}
	
	/**
     * 修改购物车
     * 
     * @param wechatShoppingCart 购物车信息
     * @return 结果
     */
	@Override
	public int updateWechatShoppingCart(WechatShoppingCart wechatShoppingCart)
	{
	    return wechatShoppingCartMapper.updateWechatShoppingCart(wechatShoppingCart);
	}

	/**
     * 删除购物车对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatShoppingCartByIds(String ids)
	{
		return wechatShoppingCartMapper.deleteWechatShoppingCartByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<WechatShoppingCart> selectWechatShoppingCartByOpenId(String openId) {
		
		return wechatShoppingCartMapper.selectWechatShoppingCartByOpenId(openId);
	}

	@Override
	public WechatShoppingCart selectWechatShoppingCartBypProductId(Integer productId) {
		return wechatShoppingCartMapper.selectWechatShoppingCartBypProductId(productId);
	}

	@Override
	public int deleteWechatShoppingByProductId(Integer productId) {
		return wechatShoppingCartMapper.deleteWechatShoppingByProductId(productId);
	}

	@Override
	public int deleteWechatShoppingCartByIdAndUserId(List<Integer> productIds, String userId) {
		return wechatShoppingCartMapper.deleteWechatShoppingCartByIdAndUserId(productIds,userId);
	}
	
}
