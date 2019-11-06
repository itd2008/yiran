package com.yiran.weixin.service;

import com.yiran.weixin.domain.WeixinTemplate;
import java.util.List;

/**
 * 微信模板 服务层
 * 
 * @author yiran
 * @date 2018-10-27
 */
public interface IWeixinTemplateService 
{
	/**
     * 查询微信模板信息
     * 
     * @param id 微信模板ID
     * @return 微信模板信息
     */
	public WeixinTemplate selectWeixinTemplateById(String id);
	
	/**
     * 查询微信模板列表
     * 
     * @param weixinTemplate 微信模板信息
     * @return 微信模板集合
     */
	public List<WeixinTemplate> selectWeixinTemplateList(WeixinTemplate weixinTemplate);
	
	/**
     * 新增微信模板
     * 
     * @param weixinTemplate 微信模板信息
     * @return 结果
     */
	public int insertWeixinTemplate(WeixinTemplate weixinTemplate);
	
	/**
     * 修改微信模板
     * 
     * @param weixinTemplate 微信模板信息
     * @return 结果
     */
	public int updateWeixinTemplate(WeixinTemplate weixinTemplate);
	
	/**
     * 保存微信模板
     * 
     * @param weixinTemplate 微信模板信息
     * @return 结果
     */
	public int saveWeixinTemplate(WeixinTemplate weixinTemplate);
	
	
	/**
     * 删除微信模板信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeixinTemplateByIds(String ids);
	
}
