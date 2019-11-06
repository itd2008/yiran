package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductBrandMapper;
import com.yiran.wechat.domain.WechatProductBrand;
import com.yiran.wechat.service.IWechatProductBrandService;
import com.yiran.common.support.Convert;

/**
 * 商品品牌 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductBrandServiceImpl implements IWechatProductBrandService 
{
	@Autowired
	private WechatProductBrandMapper wechatProductBrandMapper;

	/**
     * 查询商品品牌信息
     * 
     * @param productBrandId 商品品牌ID
     * @return 商品品牌信息
     */
    @Override
	public WechatProductBrand selectWechatProductBrandById(Integer productBrandId)
	{
	    return wechatProductBrandMapper.selectWechatProductBrandById(productBrandId);
	}
	
	/**
     * 查询商品品牌列表
     * 
     * @param wechatProductBrand 商品品牌信息
     * @return 商品品牌集合
     */
	@Override
	public List<WechatProductBrand> selectWechatProductBrandList(WechatProductBrand wechatProductBrand)
	{
	    return wechatProductBrandMapper.selectWechatProductBrandList(wechatProductBrand);
	}
	
    /**
     * 新增商品品牌
     * 
     * @param wechatProductBrand 商品品牌信息
     * @return 结果
     */
	@Override
	public int insertWechatProductBrand(WechatProductBrand wechatProductBrand)
	{
	    return wechatProductBrandMapper.insertWechatProductBrand(wechatProductBrand);
	}
	
	/**
     * 修改商品品牌
     * 
     * @param wechatProductBrand 商品品牌信息
     * @return 结果
     */
	@Override
	public int updateWechatProductBrand(WechatProductBrand wechatProductBrand)
	{
	    return wechatProductBrandMapper.updateWechatProductBrand(wechatProductBrand);
	}

	/**
     * 删除商品品牌对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductBrandByIds(String ids)
	{
		return wechatProductBrandMapper.deleteWechatProductBrandByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<WechatProductBrand> selectWechatProductBrandListByCategoryId(String categoryId) {
		return wechatProductBrandMapper.selectWechatProductBrandListByCategoryId(categoryId);
	}
	
}
