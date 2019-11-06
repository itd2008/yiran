package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatProductBrand;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 商品品牌 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatProductBrandMapper 
{
	/**
     * 查询商品品牌信息
     * 
     * @param productBrandId 商品品牌ID
     * @return 商品品牌信息
     */
	public WechatProductBrand selectWechatProductBrandById(Integer productBrandId);
	
	/**
     * 查询商品品牌列表
     * 
     * @param wechatProductBrand 商品品牌信息
     * @return 商品品牌集合
     */
	public List<WechatProductBrand> selectWechatProductBrandList(WechatProductBrand wechatProductBrand);
	
	/**
     * 新增商品品牌
     * 
     * @param wechatProductBrand 商品品牌信息
     * @return 结果
     */
	public int insertWechatProductBrand(WechatProductBrand wechatProductBrand);
	
	/**
     * 修改商品品牌
     * 
     * @param wechatProductBrand 商品品牌信息
     * @return 结果
     */
	public int updateWechatProductBrand(WechatProductBrand wechatProductBrand);
	
	/**
     * 删除商品品牌
     * 
     * @param productBrandId 商品品牌ID
     * @return 结果
     */
	public int deleteWechatProductBrandById(Integer productBrandId);
	
	/**
     * 批量删除商品品牌
     * 
     * @param productBrandIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductBrandByIds(String[] productBrandIds);
	
	public List<WechatProductBrand> selectWechatProductBrandListByCategoryId(@Param("categoryId")String categoryId);
	
}