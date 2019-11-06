package com.yiran.message.mapper;

import com.yiran.message.domain.SysSmsTemplate;
import java.util.List;	

/**
 * 短信模板 数据层
 * 
 * @author yiran
 * @date 2019-03-08
 */
public interface SysSmsTemplateMapper 
{
	/**
     * 查询短信模板信息
     * 
     * @param id 短信模板ID
     * @return 短信模板信息
     */
	public SysSmsTemplate selectSysSmsTemplateById(Integer id);
	
	/**
     * 查询短信模板列表
     * 
     * @param sysSmsTemplate 短信模板信息
     * @return 短信模板集合
     */
	public List<SysSmsTemplate> selectSysSmsTemplateList(SysSmsTemplate sysSmsTemplate);
	
	/**
     * 新增短信模板
     * 
     * @param sysSmsTemplate 短信模板信息
     * @return 结果
     */
	public int insertSysSmsTemplate(SysSmsTemplate sysSmsTemplate);
	
	/**
     * 修改短信模板
     * 
     * @param sysSmsTemplate 短信模板信息
     * @return 结果
     */
	public int updateSysSmsTemplate(SysSmsTemplate sysSmsTemplate);
	
	/**
     * 删除短信模板
     * 
     * @param id 短信模板ID
     * @return 结果
     */
	public int deleteSysSmsTemplateById(Integer id);
	
	/**
     * 批量删除短信模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysSmsTemplateByIds(String[] ids);

	/**
	 * 根据模板ID获取模板信息
	 * @param templateId
	 * @return
	 */
	public SysSmsTemplate selectSmsTemplateByTemplateId(String templateId);
	
}