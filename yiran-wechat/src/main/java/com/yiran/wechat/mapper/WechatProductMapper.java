package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatProduct;
import java.util.List;	

/**
 * 商品 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatProductMapper 
{
	/**
     * 查询商品信息
     * 
     * @param productId 商品ID
     * @return 商品信息
     */
	public WechatProduct selectWechatProductById(Integer productId);
	
	/**
     * 查询商品列表
     * 
     * @param wechatProduct 商品信息
     * @return 商品集合
     */
	public List<WechatProduct> selectWechatProductList(WechatProduct wechatProduct);
	
	/**
     * 新增商品
     * 
     * @param wechatProduct 商品信息
     * @return 结果
     */
	public int insertWechatProduct(WechatProduct wechatProduct);
	
	/**
     * 修改商品
     * 
     * @param wechatProduct 商品信息
     * @return 结果
     */
	public int updateWechatProduct(WechatProduct wechatProduct);
	
	/**
     * 删除商品
     * 
     * @param productId 商品ID
     * @return 结果
     */
	public int deleteWechatProductById(Integer productId);
	
	/**
     * 批量删除商品
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductByIds(String[] productIds);
	
}