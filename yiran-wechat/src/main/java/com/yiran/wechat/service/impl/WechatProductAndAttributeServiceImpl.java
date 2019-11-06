package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductAndAttributeMapper;
import com.yiran.wechat.domain.WechatProductAndAttribute;
import com.yiran.wechat.service.IWechatProductAndAttributeService;
import com.yiran.common.support.Convert;

/**
 * 商品与属性关联 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductAndAttributeServiceImpl implements IWechatProductAndAttributeService 
{
	@Autowired
	private WechatProductAndAttributeMapper wechatProductAndAttributeMapper;

	/**
     * 查询商品与属性关联信息
     * 
     * @param productAndAttributeId 商品与属性关联ID
     * @return 商品与属性关联信息
     */
    @Override
	public WechatProductAndAttribute selectWechatProductAndAttributeById(Integer productAndAttributeId)
	{
	    return wechatProductAndAttributeMapper.selectWechatProductAndAttributeById(productAndAttributeId);
	}
	
	/**
     * 查询商品与属性关联列表
     * 
     * @param wechatProductAndAttribute 商品与属性关联信息
     * @return 商品与属性关联集合
     */
	@Override
	public List<WechatProductAndAttribute> selectWechatProductAndAttributeList(WechatProductAndAttribute wechatProductAndAttribute)
	{
	    return wechatProductAndAttributeMapper.selectWechatProductAndAttributeList(wechatProductAndAttribute);
	}
	
    /**
     * 新增商品与属性关联
     * 
     * @param wechatProductAndAttribute 商品与属性关联信息
     * @return 结果
     */
	@Override
	public int insertWechatProductAndAttribute(WechatProductAndAttribute wechatProductAndAttribute)
	{
	    return wechatProductAndAttributeMapper.insertWechatProductAndAttribute(wechatProductAndAttribute);
	}
	
	/**
     * 修改商品与属性关联
     * 
     * @param wechatProductAndAttribute 商品与属性关联信息
     * @return 结果
     */
	@Override
	public int updateWechatProductAndAttribute(WechatProductAndAttribute wechatProductAndAttribute)
	{
	    return wechatProductAndAttributeMapper.updateWechatProductAndAttribute(wechatProductAndAttribute);
	}

	/**
     * 删除商品与属性关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductAndAttributeByIds(String ids)
	{
		return wechatProductAndAttributeMapper.deleteWechatProductAndAttributeByIds(Convert.toStrArray(ids));
	}
	
}
