package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductSpecification;
import java.util.List;

/**
 * 商品规格 服务层
 * 
 * @author yiran
 * @date 2019-06-25
 */
public interface IWechatProductSpecificationService 
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
     * 删除商品规格信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductSpecificationByIds(String ids);

	/**
	 * 根据类型分组
	 * @param categoryId
	 * @return
	 */
	public List<WechatProductSpecification> selectWechatProductSpecificationListByCategoryId(Integer categoryId);

	public List<WechatProductSpecification> selectSpecificationListByCategoryIdAndSpecificationType(Integer categoryId,
			String specificationCode);

	public List<WechatProductSpecification> selectWechatProductSpecificationListByCategoryIdAndSpecifications(Integer categoryId,
			List<String> ggList);
	
}
