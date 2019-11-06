package com.yiran.wechat.mapper;

import com.yiran.wechat.domain.WechatFreightTemplate;
import java.util.List;	

/**
 * 运费模板 数据层
 * 
 * @author yiran
 * @date 2019-07-06
 */
public interface WechatFreightTemplateMapper 
{
	/**
     * 查询运费模板信息
     * 
     * @param freightTemplateId 运费模板ID
     * @return 运费模板信息
     */
	public WechatFreightTemplate selectWechatFreightTemplateById(Integer freightTemplateId);
	
	/**
     * 查询运费模板列表
     * 
     * @param wechatFreightTemplate 运费模板信息
     * @return 运费模板集合
     */
	public List<WechatFreightTemplate> selectWechatFreightTemplateList(WechatFreightTemplate wechatFreightTemplate);
	
	/**
     * 新增运费模板
     * 
     * @param wechatFreightTemplate 运费模板信息
     * @return 结果
     */
	public int insertWechatFreightTemplate(WechatFreightTemplate wechatFreightTemplate);
	
	/**
     * 修改运费模板
     * 
     * @param wechatFreightTemplate 运费模板信息
     * @return 结果
     */
	public int updateWechatFreightTemplate(WechatFreightTemplate wechatFreightTemplate);
	
	/**
     * 删除运费模板
     * 
     * @param freightTemplateId 运费模板ID
     * @return 结果
     */
	public int deleteWechatFreightTemplateById(Integer freightTemplateId);
	
	/**
     * 批量删除运费模板
     * 
     * @param freightTemplateIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatFreightTemplateByIds(String[] freightTemplateIds);

	/**
	 * 获取所有的运费模板
	 * @return
	 */
	public List<WechatFreightTemplate> selectWechatFreightTemplateLists();
	
}