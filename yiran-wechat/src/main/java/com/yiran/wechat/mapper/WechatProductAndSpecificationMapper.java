package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatProductAndSpecification;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 商品与商品规格关联 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatProductAndSpecificationMapper 
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
     * 删除商品与商品规格关联
     * 
     * @param productAndSpecificationId 商品与商品规格关联ID
     * @return 结果
     */
	public int deleteWechatProductAndSpecificationById(Integer productAndSpecificationId);
	
	/**
     * 批量删除商品与商品规格关联
     * 
     * @param productAndSpecificationIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductAndSpecificationByIds(String[] productAndSpecificationIds);

	public WechatProductAndSpecification selectWechatProductAndSpecificationByProductIdAndSpecificationId(
			@Param("productId")String productId, 
			@Param("specificationId")String specificationId);

	
}