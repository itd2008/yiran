package com.yiran.weixin.mapper;

import com.yiran.weixin.domain.WeixinSetting;
import java.util.List;	

/**
 * 微信基本设置 数据层
 * 
 * @author yiran
 * @date 2018-07-17
 */
public interface WeixinSettingMapper 
{
	/**
     * 查询微信基本设置信息
     * 
     * @param id 微信基本设置ID
     * @return 微信基本设置信息
     */
	public WeixinSetting selectWeixinSettingById(Integer id);
	
	/**
     * 查询微信基本设置列表
     * 
     * @param weixinSetting 微信基本设置信息
     * @return 微信基本设置集合
     */
	public List<WeixinSetting> selectWeixinSettingList(WeixinSetting weixinSetting);
	
	/**
     * 新增微信基本设置
     * 
     * @param weixinSetting 微信基本设置信息
     * @return 结果
     */
	public int insertWeixinSetting(WeixinSetting weixinSetting);
	
	/**
     * 修改微信基本设置
     * 
     * @param weixinSetting 微信基本设置信息
     * @return 结果
     */
	public int updateWeixinSetting(WeixinSetting weixinSetting);
	
	/**
     * 删除微信基本设置
     * 
     * @param id 微信基本设置ID
     * @return 结果
     */
	public int deleteWeixinSettingById(Integer id);
	
	/**
     * 批量删除微信基本设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeixinSettingByIds(String[] ids);

	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 */
	public WeixinSetting getValueByKey(String key);
	
}