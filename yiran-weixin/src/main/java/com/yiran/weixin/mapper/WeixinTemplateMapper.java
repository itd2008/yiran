package com.yiran.weixin.mapper;

import com.yiran.weixin.domain.WeixinTemplate;
import java.util.List;	

/**
 * 微信模板 数据层
 * 
 * @author yiran
 * @date 2018-10-27
 */
public interface WeixinTemplateMapper 
{
	/**
     * 查询微信模板信息
     * 
     * @param id 微信模板ID
     * @return 微信模板信息
     */
	public WeixinTemplate selectWeixinTemplateById(String id);
	/**
	 * 根据模板Key获取模板对象
	 * @param templateKey
	 * @return
	 */
	public WeixinTemplate selectWeixinTemplateByTemplateKey(String templateKey);
	
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
     * 删除微信模板
     * 
     * @param id 微信模板ID
     * @return 结果
     */
	public int deleteWeixinTemplateById(String id);
	
	/**
     * 批量删除微信模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeixinTemplateByIds(String[] ids);
	
}