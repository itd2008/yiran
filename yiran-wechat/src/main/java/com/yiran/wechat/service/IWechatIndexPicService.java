package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatIndexPic;
import java.util.List;

/**
 * 首页宣传图片 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatIndexPicService 
{
	/**
     * 查询首页宣传图片信息
     * 
     * @param id 首页宣传图片ID
     * @return 首页宣传图片信息
     */
	public WechatIndexPic selectWechatIndexPicById(Integer id);
	
	/**
     * 查询首页宣传图片列表
     * 
     * @param wechatIndexPic 首页宣传图片信息
     * @return 首页宣传图片集合
     */
	public List<WechatIndexPic> selectWechatIndexPicList(WechatIndexPic wechatIndexPic);
	
	/**
     * 新增首页宣传图片
     * 
     * @param wechatIndexPic 首页宣传图片信息
     * @return 结果
     */
	public int insertWechatIndexPic(WechatIndexPic wechatIndexPic);
	
	/**
     * 修改首页宣传图片
     * 
     * @param wechatIndexPic 首页宣传图片信息
     * @return 结果
     */
	public int updateWechatIndexPic(WechatIndexPic wechatIndexPic);
		
	/**
     * 删除首页宣传图片信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatIndexPicByIds(String ids);
	
}
