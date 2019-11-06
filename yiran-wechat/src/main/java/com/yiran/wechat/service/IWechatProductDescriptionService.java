package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductDescription;
import java.util.List;

/**
 * 商品描述内容 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatProductDescriptionService 
{
	/**
     * 查询商品描述内容信息
     * 
     * @param productDescriptionId 商品描述内容ID
     * @return 商品描述内容信息
     */
	public WechatProductDescription selectWechatProductDescriptionById(Integer productDescriptionId);
	
	/**
     * 查询商品描述内容列表
     * 
     * @param wechatProductDescription 商品描述内容信息
     * @return 商品描述内容集合
     */
	public List<WechatProductDescription> selectWechatProductDescriptionList(WechatProductDescription wechatProductDescription);
	
	/**
     * 新增商品描述内容
     * 
     * @param wechatProductDescription 商品描述内容信息
     * @return 结果
     */
	public int insertWechatProductDescription(WechatProductDescription wechatProductDescription);
	
	/**
     * 修改商品描述内容
     * 
     * @param wechatProductDescription 商品描述内容信息
     * @return 结果
     */
	public int updateWechatProductDescription(WechatProductDescription wechatProductDescription);
		
	/**
     * 删除商品描述内容信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductDescriptionByIds(String ids);

	/**
	 * 根据产品ID获取产品详情
	 * @param productId
	 * @return
	 */
	public WechatProductDescription selectWechatProductDescriptionByProductId(Integer productId);
	
}
