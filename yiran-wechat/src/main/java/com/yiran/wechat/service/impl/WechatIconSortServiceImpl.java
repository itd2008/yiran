package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatIconSortMapper;
import com.yiran.wechat.domain.WechatIconSort;
import com.yiran.wechat.service.IWechatIconSortService;
import com.yiran.common.support.Convert;

/**
 * 图标分类 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatIconSortServiceImpl implements IWechatIconSortService 
{
	@Autowired
	private WechatIconSortMapper wechatIconSortMapper;

	/**
     * 查询图标分类信息
     * 
     * @param id 图标分类ID
     * @return 图标分类信息
     */
    @Override
	public WechatIconSort selectWechatIconSortById(Integer id)
	{
	    return wechatIconSortMapper.selectWechatIconSortById(id);
	}
	
	/**
     * 查询图标分类列表
     * 
     * @param wechatIconSort 图标分类信息
     * @return 图标分类集合
     */
	@Override
	public List<WechatIconSort> selectWechatIconSortList(WechatIconSort wechatIconSort)
	{
	    return wechatIconSortMapper.selectWechatIconSortList(wechatIconSort);
	}
	
    /**
     * 新增图标分类
     * 
     * @param wechatIconSort 图标分类信息
     * @return 结果
     */
	@Override
	public int insertWechatIconSort(WechatIconSort wechatIconSort)
	{
	    return wechatIconSortMapper.insertWechatIconSort(wechatIconSort);
	}
	
	/**
     * 修改图标分类
     * 
     * @param wechatIconSort 图标分类信息
     * @return 结果
     */
	@Override
	public int updateWechatIconSort(WechatIconSort wechatIconSort)
	{
	    return wechatIconSortMapper.updateWechatIconSort(wechatIconSort);
	}

	/**
     * 删除图标分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatIconSortByIds(String ids)
	{
		return wechatIconSortMapper.deleteWechatIconSortByIds(Convert.toStrArray(ids));
	}
	
}
