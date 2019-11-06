package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatProductInventory;
import java.util.List;

/**
 * 商品进销存 服务层
 * 
 * @author yiran
 * @date 2019-06-25
 */
public interface IWechatProductInventoryService 
{
	/**
     * 查询商品进销存信息
     * 
     * @param productInventoryId 商品进销存ID
     * @return 商品进销存信息
     */
	public WechatProductInventory selectWechatProductInventoryById(Integer productInventoryId);
	
	/**
     * 查询商品进销存列表
     * 
     * @param wechatProductInventory 商品进销存信息
     * @return 商品进销存集合
     */
	public List<WechatProductInventory> selectWechatProductInventoryList(WechatProductInventory wechatProductInventory);
	
	/**
     * 新增商品进销存
     * 
     * @param wechatProductInventory 商品进销存信息
     * @return 结果
     */
	public int insertWechatProductInventory(WechatProductInventory wechatProductInventory);
	
	/**
     * 修改商品进销存
     * 
     * @param wechatProductInventory 商品进销存信息
     * @return 结果
     */
	public int updateWechatProductInventory(WechatProductInventory wechatProductInventory);
		
	/**
     * 删除商品进销存信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatProductInventoryByIds(String ids);
	
}
