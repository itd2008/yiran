package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductDescriptionMapper;
import com.yiran.wechat.domain.WechatProductDescription;
import com.yiran.wechat.service.IWechatProductDescriptionService;
import com.yiran.common.support.Convert;

/**
 * 商品描述内容 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductDescriptionServiceImpl implements IWechatProductDescriptionService 
{
	@Autowired
	private WechatProductDescriptionMapper wechatProductDescriptionMapper;

	/**
     * 查询商品描述内容信息
     * 
     * @param productDescriptionId 商品描述内容ID
     * @return 商品描述内容信息
     */
    @Override
	public WechatProductDescription selectWechatProductDescriptionById(Integer productDescriptionId)
	{
	    return wechatProductDescriptionMapper.selectWechatProductDescriptionById(productDescriptionId);
	}
	
	/**
     * 查询商品描述内容列表
     * 
     * @param wechatProductDescription 商品描述内容信息
     * @return 商品描述内容集合
     */
	@Override
	public List<WechatProductDescription> selectWechatProductDescriptionList(WechatProductDescription wechatProductDescription)
	{
	    return wechatProductDescriptionMapper.selectWechatProductDescriptionList(wechatProductDescription);
	}
	
    /**
     * 新增商品描述内容
     * 
     * @param wechatProductDescription 商品描述内容信息
     * @return 结果
     */
	@Override
	public int insertWechatProductDescription(WechatProductDescription wechatProductDescription)
	{
	    return wechatProductDescriptionMapper.insertWechatProductDescription(wechatProductDescription);
	}
	
	/**
     * 修改商品描述内容
     * 
     * @param wechatProductDescription 商品描述内容信息
     * @return 结果
     */
	@Override
	public int updateWechatProductDescription(WechatProductDescription wechatProductDescription)
	{
	    return wechatProductDescriptionMapper.updateWechatProductDescription(wechatProductDescription);
	}

	/**
     * 删除商品描述内容对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductDescriptionByIds(String ids)
	{
		return wechatProductDescriptionMapper.deleteWechatProductDescriptionByIds(Convert.toStrArray(ids));
	}

	@Override
	public WechatProductDescription selectWechatProductDescriptionByProductId(Integer productId) {
		return wechatProductDescriptionMapper.selectWechatProductDescriptionByProductId(productId);
	}
	
}
