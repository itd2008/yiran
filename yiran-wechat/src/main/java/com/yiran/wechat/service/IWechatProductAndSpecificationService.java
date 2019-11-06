package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductAndSpecification;
import java.util.List;

/**
 * 商品与商品规格关联 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatProductAndSpecificationService 
{
	/**
     * 查询商品与商品规格关联信息
     * 
     * @param productAndSpecificationId 商品与商品规格关联ID
     * @return 商品与商品规格关联信息
     */
	public WechatProductAndSpecification selectWechatProductAndSpecificationById(Integer productAndSpecificationId);
	
	/**
     * 查询商品与商品规格关联列表
     * 
     * @param wechatProductAndSpecification 商品与商品规格关联信息
     * @return 商品与商品规格关联集合
     */
	public List<WechatProductAndSpecification> selectWechatProductAndSpecificationList(WechatProductAndSpecification wechatProductAndSpecification);
	
	/**
     * 新增商品与商品规格关联
     * 
     * @param wechatProductAndSpecification 商品与商品规格关联信息
     * @return 结果
     */
	public int insertWechatProductAndSpecification(WechatProductAndSpecification wechatProductAndSpecification);
	
	/**
     * 修改商品与商品规格关联
     * 
     * @param wechatProductAndSpecification 商品与商品规格关联信息
     * @return 结果
     */
	public int updateWechatProductAndSpecification(WechatProductAndSpecification wechatProductAndSpecification);
		
	/**
     * 删除商品与商品规格关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductAndSpecificationByIds(String ids);

	public List<String[]> selectWechatProductAndSpecificationByProductId(Integer productId);

	public WechatProductAndSpecification selectWechatProductAndSpecificationByProductIdAndSpecificationId(
			Integer productId, String specificationIds);
	
}
