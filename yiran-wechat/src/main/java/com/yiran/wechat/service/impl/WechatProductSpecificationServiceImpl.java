package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductSpecificationMapper;
import com.yiran.wechat.domain.WechatProductBrand;
import com.yiran.wechat.domain.WechatProductSpecification;
import com.yiran.wechat.service.IWechatProductSpecificationService;
import com.yiran.common.support.Convert;

/**
 * 商品规格 服务层实现
 * 
 * @author yiran
 * @date 2019-06-25
 */
@Service
public class WechatProductSpecificationServiceImpl implements IWechatProductSpecificationService 
{
	@Autowired
	private WechatProductSpecificationMapper wechatProductSpecificationMapper;

	/**
     * 查询商品规格信息
     * 
     * @param productSpecification 商品规格ID
     * @return 商品规格信息
     */
    @Override
	public WechatProductSpecification selectWechatProductSpecificationById(Integer productSpecification)
	{
	    return wechatProductSpecificationMapper.selectWechatProductSpecificationById(productSpecification);
	}
	
	/**
     * 查询商品规格列表
     * 
     * @param wechatProductSpecification 商品规格信息
     * @return 商品规格集合
     */
	@Override
	public List<WechatProductSpecification> selectWechatProductSpecificationList(WechatProductSpecification wechatProductSpecification)
	{
	    return wechatProductSpecificationMapper.selectWechatProductSpecificationList(wechatProductSpecification);
	}
	
    /**
     * 新增商品规格
     * 
     * @param wechatProductSpecification 商品规格信息
     * @return 结果
     */
	@Override
	public int insertWechatProductSpecification(WechatProductSpecification wechatProductSpecification)
	{
	    return wechatProductSpecificationMapper.insertWechatProductSpecification(wechatProductSpecification);
	}
	
	/**
     * 修改商品规格
     * 
     * @param wechatProductSpecification 商品规格信息
     * @return 结果
     */
	@Override
	public int updateWechatProductSpecification(WechatProductSpecification wechatProductSpecification)
	{
	    return wechatProductSpecificationMapper.updateWechatProductSpecification(wechatProductSpecification);
	}

	/**
     * 删除商品规格对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductSpecificationByIds(String ids)
	{
		return wechatProductSpecificationMapper.deleteWechatProductSpecificationByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<WechatProductSpecification> selectWechatProductSpecificationListByCategoryId(Integer categoryId) {
		
		return wechatProductSpecificationMapper.selectWechatProductSpecificationListByCategoryId(categoryId);
	}

	@Override
	public List<WechatProductSpecification> selectSpecificationListByCategoryIdAndSpecificationType(Integer categoryId,
			String specificationCode) {
		return wechatProductSpecificationMapper.selectSpecificationListByCategoryIdAndSpecificationType(categoryId,specificationCode);
	}

	@Override
	public List<WechatProductSpecification> selectWechatProductSpecificationListByCategoryIdAndSpecifications(Integer categoryId,
			List<String> ggList) {
		return wechatProductSpecificationMapper.selectWechatProductSpecificationListByCategoryIdAndSpecifications(categoryId,ggList);
	}
	
	
}
