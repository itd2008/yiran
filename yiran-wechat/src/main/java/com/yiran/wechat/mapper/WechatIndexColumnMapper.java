package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatIndexColumn;
import java.util.List;	

/**
 * 首页栏目 数据层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface WechatIndexColumnMapper 
{
	/**
     * 查询首页栏目信息
     * 
     * @param id 首页栏目ID
     * @return 首页栏目信息
     */
	public WechatIndexColumn selectWechatIndexColumnById(Integer id);
	
	/**
     * 查询首页栏目列表
     * 
     * @param wechatIndexColumn 首页栏目信息
     * @return 首页栏目集合
     */
	public List<WechatIndexColumn> selectWechatIndexColumnList(WechatIndexColumn wechatIndexColumn);
	
	/**
     * 新增首页栏目
     * 
     * @param wechatIndexColumn 首页栏目信息
     * @return 结果
     */
	public int insertWechatIndexColumn(WechatIndexColumn wechatIndexColumn);
	
	/**
     * 修改首页栏目
     * 
     * @param wechatIndexColumn 首页栏目信息
     * @return 结果
     */
	public int updateWechatIndexColumn(WechatIndexColumn wechatIndexColumn);
	
	/**
     * 删除首页栏目
     * 
     * @param id 首页栏目ID
     * @return 结果
     */
	public int deleteWechatIndexColumnById(Integer id);
	
	/**
     * 批量删除首页栏目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatIndexColumnByIds(String[] ids);
	
}