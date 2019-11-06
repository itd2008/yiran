package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatIconSort;
import java.util.List;

/**
 * 图标分类 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatIconSortService 
{
	/**
     * 查询图标分类信息
     * 
     * @param id 图标分类ID
     * @return 图标分类信息
     */
	public WechatIconSort selectWechatIconSortById(Integer id);
	
	/**
     * 查询图标分类列表
     * 
     * @param wechatIconSort 图标分类信息
     * @return 图标分类集合
     */
	public List<WechatIconSort> selectWechatIconSortList(WechatIconSort wechatIconSort);
	
	/**
     * 新增图标分类
     * 
     * @param wechatIconSort 图标分类信息
     * @return 结果
     */
	public int insertWechatIconSort(WechatIconSort wechatIconSort);
	
	/**
     * 修改图标分类
     * 
     * @param wechatIconSort 图标分类信息
     * @return 结果
     */
	public int updateWechatIconSort(WechatIconSort wechatIconSort);
		
	/**
     * 删除图标分类信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatIconSortByIds(String ids);
	
}
