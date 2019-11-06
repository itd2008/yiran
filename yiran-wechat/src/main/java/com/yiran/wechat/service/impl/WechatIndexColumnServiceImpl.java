package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatIndexColumnMapper;
import com.yiran.wechat.domain.WechatIndexColumn;
import com.yiran.wechat.service.IWechatIndexColumnService;
import com.yiran.common.support.Convert;

/**
 * 首页栏目 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatIndexColumnServiceImpl implements IWechatIndexColumnService 
{
	@Autowired
	private WechatIndexColumnMapper wechatIndexColumnMapper;

	/**
     * 查询首页栏目信息
     * 
     * @param id 首页栏目ID
     * @return 首页栏目信息
     */
    @Override
	public WechatIndexColumn selectWechatIndexColumnById(Integer id)
	{
	    return wechatIndexColumnMapper.selectWechatIndexColumnById(id);
	}
	
	/**
     * 查询首页栏目列表
     * 
     * @param wechatIndexColumn 首页栏目信息
     * @return 首页栏目集合
     */
	@Override
	public List<WechatIndexColumn> selectWechatIndexColumnList(WechatIndexColumn wechatIndexColumn)
	{
	    return wechatIndexColumnMapper.selectWechatIndexColumnList(wechatIndexColumn);
	}
	
    /**
     * 新增首页栏目
     * 
     * @param wechatIndexColumn 首页栏目信息
     * @return 结果
     */
	@Override
	public int insertWechatIndexColumn(WechatIndexColumn wechatIndexColumn)
	{
	    return wechatIndexColumnMapper.insertWechatIndexColumn(wechatIndexColumn);
	}
	
	/**
     * 修改首页栏目
     * 
     * @param wechatIndexColumn 首页栏目信息
     * @return 结果
     */
	@Override
	public int updateWechatIndexColumn(WechatIndexColumn wechatIndexColumn)
	{
	    return wechatIndexColumnMapper.updateWechatIndexColumn(wechatIndexColumn);
	}

	/**
     * 删除首页栏目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatIndexColumnByIds(String ids)
	{
		return wechatIndexColumnMapper.deleteWechatIndexColumnByIds(Convert.toStrArray(ids));
	}
	
}
