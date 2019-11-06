package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductBrand;
import java.util.List;

/**
 * 商品品牌 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatProductBrandService 
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
     * 删除商品品牌信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductBrandByIds(String ids);


	public List<WechatProductBrand> selectWechatProductBrandListByCategoryId(String categoryId);
	
}
