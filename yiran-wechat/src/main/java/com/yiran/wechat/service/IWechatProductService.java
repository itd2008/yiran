package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProduct;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 商品 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatProductService 
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
	public int insertWechatProduct(MultipartFile[] file,WechatProduct wechatProduct);
	
	/**
     * 修改商品
     * 
     * @param wechatProduct 商品信息
     * @return 结果
     */
	public int updateWechatProduct(WechatProduct wechatProduct);
		
	/**
     * 删除商品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductByIds(String ids);
	
}
