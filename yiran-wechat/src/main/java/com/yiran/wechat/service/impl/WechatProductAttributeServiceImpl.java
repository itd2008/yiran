package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductAttributeMapper;
import com.yiran.wechat.domain.WechatProductAttribute;
import com.yiran.wechat.domain.WechatProductBrand;
import com.yiran.wechat.service.IWechatProductAttributeService;
import com.yiran.common.support.Convert;

/**
 * 商品属性 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductAttributeServiceImpl implements IWechatProductAttributeService 
{
	@Autowired
	private WechatProductAttributeMapper wechatProductAttributeMapper;

	/**
     * 查询商品属性信息
     * 
     * @param productAttributeId 商品属性ID
     * @return 商品属性信息
     */
    @Override
	public WechatProductAttribute selectWechatProductAttributeById(Integer productAttributeId)
	{
	    return wechatProductAttributeMapper.selectWechatProductAttributeById(productAttributeId);
	}
	
	/**
     * 查询商品属性列表
     * 
     * @param wechatProductAttribute 商品属性信息
     * @return 商品属性集合
     */
	@Override
	public List<WechatProductAttribute> selectWechatProductAttributeList(WechatProductAttribute wechatProductAttribute)
	{
	    return wechatProductAttributeMapper.selectWechatProductAttributeList(wechatProductAttribute);
	}
	
    /**
     * 新增商品属性
     * 
     * @param wechatProductAttribute 商品属性信息
     * @return 结果
     */
	@Override
	public int insertWechatProductAttribute(WechatProductAttribute wechatProductAttribute)
	{
	    return wechatProductAttributeMapper.insertWechatProductAttribute(wechatProductAttribute);
	}
	
	/**
     * 修改商品属性
     * 
     * @param wechatProductAttribute 商品属性信息
     * @return 结果
     */
	@Override
	public int updateWechatProductAttribute(WechatProductAttribute wechatProductAttribute)
	{
	    return wechatProductAttributeMapper.updateWechatProductAttribute(wechatProductAttribute);
	}

	/**
     * 删除商品属性对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductAttributeByIds(String ids)
	{
		return wechatProductAttributeMapper.deleteWechatProductAttributeByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<WechatProductAttribute> selectWechatProductAttrdListByCategoryId(String categoryId) {
		return wechatProductAttributeMapper.selectWechatProductAttrdListByCategoryId(categoryId);
	}
	
}
