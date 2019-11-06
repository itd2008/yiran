package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductInventoryMapper;
import com.yiran.wechat.domain.WechatProductInventory;
import com.yiran.wechat.service.IWechatProductInventoryService;
import com.yiran.common.support.Convert;

/**
 * 商品进销存 服务层实现
 * 
 * @author yiran
 * @date 2019-06-25
 */
@Service
public class WechatProductInventoryServiceImpl implements IWechatProductInventoryService 
{
	@Autowired
	private WechatProductInventoryMapper wechatProductInventoryMapper;

	/**
     * 查询商品进销存信息
     * 
     * @param productInventoryId 商品进销存ID
     * @return 商品进销存信息
     */
    @Override
	public WechatProductInventory selectWechatProductInventoryById(Integer productInventoryId)
	{
	    return wechatProductInventoryMapper.selectWechatProductInventoryById(productInventoryId);
	}
	
	/**
     * 查询商品进销存列表
     * 
     * @param wechatProductInventory 商品进销存信息
     * @return 商品进销存集合
     */
	@Override
	public List<WechatProductInventory> selectWechatProductInventoryList(WechatProductInventory wechatProductInventory)
	{
	    return wechatProductInventoryMapper.selectWechatProductInventoryList(wechatProductInventory);
	}
	
    /**
     * 新增商品进销存
     * 
     * @param wechatProductInventory 商品进销存信息
     * @return 结果
     */
	@Override
	public int insertWechatProductInventory(WechatProductInventory wechatProductInventory)
	{
	    return wechatProductInventoryMapper.insertWechatProductInventory(wechatProductInventory);
	}
	
	/**
     * 修改商品进销存
     * 
     * @param wechatProductInventory 商品进销存信息
     * @return 结果
     */
	@Override
	public int updateWechatProductInventory(WechatProductInventory wechatProductInventory)
	{
	    return wechatProductInventoryMapper.updateWechatProductInventory(wechatProductInventory);
	}

	/**
     * 删除商品进销存对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductInventoryByIds(String ids)
	{
		return wechatProductInventoryMapper.deleteWechatProductInventoryByIds(Convert.toStrArray(ids));
	}
	
}
