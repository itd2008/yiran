package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductAttribute;
import com.yiran.wechat.domain.WechatProductBrand;

import java.util.List;

/**
 * 商品属性 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatProductAttributeService 
{
	/**
     * 查询商品属性信息
     * 
     * @param productAttributeId 商品属性ID
     * @return 商品属性信息
     */
	public WechatProductAttribute selectWechatProductAttributeById(Integer productAttributeId);
	
	/**
     * 查询商品属性列表
     * 
     * @param wechatProductAttribute 商品属性信息
     * @return 商品属性集合
     */
	public List<WechatProductAttribute> selectWechatProductAttributeList(WechatProductAttribute wechatProductAttribute);
	
	/**
     * 新增商品属性
     * 
     * @param wechatProductAttribute 商品属性信息
     * @return 结果
     */
	public int insertWechatProductAttribute(WechatProductAttribute wechatProductAttribute);
	
	/**
     * 修改商品属性
     * 
     * @param wechatProductAttribute 商品属性信息
     * @return 结果
     */
	public int updateWechatProductAttribute(WechatProductAttribute wechatProductAttribute);
		
	/**
     * 删除商品属性信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductAttributeByIds(String ids);

	public List<WechatProductAttribute> selectWechatProductAttrdListByCategoryId(String categoryId);
	
}
