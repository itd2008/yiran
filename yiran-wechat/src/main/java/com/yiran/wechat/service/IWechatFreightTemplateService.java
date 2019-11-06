package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatFreightTemplate;
import java.util.List;

/**
 * 运费模板 服务层
 * 
 * @author yiran
 * @date 2019-07-06
 */
public interface IWechatFreightTemplateService 
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
     * 删除运费模板信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatFreightTemplateByIds(String ids);

	/**
	 * 获取所有运费模板
	 * @return
	 */
	public List<WechatFreightTemplate> selectWechatFreightTemplateList();
	
}
