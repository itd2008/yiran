package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatShoppingCart;
import java.util.List;

/**
 * 购物车 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatShoppingCartService 
{
	/**
     * 查询购物车信息
     * 
     * @param id 购物车ID
     * @return 购物车信息
     */
	public WechatShoppingCart selectWechatShoppingCartById(Integer id);
	
	/**
     * 查询购物车列表
     * 
     * @param wechatShoppingCart 购物车信息
     * @return 购物车集合
     */
	public List<WechatShoppingCart> selectWechatShoppingCartList(WechatShoppingCart wechatShoppingCart);
	
	
	/**
     * 新增购物车
     * 
     * @param wechatShoppingCart 购物车信息
     * @return 结果
     */
	public int insertWechatShoppingCart(WechatShoppingCart wechatShoppingCart);
	
	/**
     * 修改购物车
     * 
     * @param wechatShoppingCart 购物车信息
     * @return 结果
     */
	public int updateWechatShoppingCart(WechatShoppingCart wechatShoppingCart);
		
	/**
     * 删除购物车信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatShoppingCartByIds(String ids);

	/**
	 * 查询某个用户购物车商品列表
	 * @param openId
	 * @return
	 */
	public List<WechatShoppingCart> selectWechatShoppingCartByOpenId(String openId);

	public WechatShoppingCart selectWechatShoppingCartBypProductId(Integer productId);

	public int deleteWechatShoppingByProductId(Integer productId);

	public int deleteWechatShoppingCartByIdAndUserId(List<Integer> productIds,String userId);
	
}
