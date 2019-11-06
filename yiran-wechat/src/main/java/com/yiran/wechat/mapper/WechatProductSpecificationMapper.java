package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatProductBrand;
import com.yiran.wechat.domain.WechatProductSpecification;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 商品规格 数据层
 * 
 * @author yiran
 * @date 2019-06-25
 */
public interface WechatProductSpecificationMapper 
{
	/**
     * 查询商品规格信息
     * 
     * @param productSpecification 商品规格ID
     * @return 商品规格信息
     */
	public WechatProductSpecification selectWechatProductSpecificationById(Integer productSpecification);
	
	/**
     * 查询商品规格列表
     * 
     * @param wechatProductSpecification 商品规格信息
     * @return 商品规格集合
     */
	public List<WechatProductSpecification> selectWechatProductSpecificationList(WechatProductSpecification wechatProductSpecification);
	
	/**
     * 新增商品规格
     * 
     * @param wechatProductSpecification 商品规格信息
     * @return 结果
     */
	public int insertWechatProductSpecification(WechatProductSpecification wechatProductSpecification);
	
	/**
     * 修改商品规格
     * 
     * @param wechatProductSpecification 商品规格信息
     * @return 结果
     */
	public int updateWechatProductSpecification(WechatProductSpecification wechatProductSpecification);
	
	/**
     * 删除商品规格
     * 
     * @param productSpecification 商品规格ID
     * @return 结果
     */
	public int deleteWechatProductSpecificationById(Integer productSpecification);
	
	/**
     * 批量删除商品规格
     * 
     * @param productSpecifications 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductSpecificationByIds(String[] productSpecifications);

	public List<WechatProductSpecification> selectWechatProductSpecificationListByCategoryId(@Param("productCategoryId")Integer productCategoryId);

	/**
	 * 根据规格类型和类目查询这个规格类型下所有的规格值
	 * @param productCategoryId
	 * @param specificationType
	 * @return
	 */
	public List<WechatProductSpecification> selectSpecificationListByCategoryIdAndSpecificationType(@Param("productCategoryId")Integer productCategoryId,
			@Param("specificationCode")String specificationCode);

	public List<WechatProductSpecification> selectWechatProductSpecificationListByCategoryIdAndSpecifications(
			@Param("categoryId")Integer categoryId,
			@Param("specifications")List<String> specifications);
	
}