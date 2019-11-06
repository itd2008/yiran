package com.yiran.message.service;

import com.yiran.message.domain.SysSmsTemplate;
import java.util.List;

/**
 * 短信模板 服务层
 * 
 * @author yiran
 * @date 2019-03-08
 */
public interface ISysSmsTemplateService 
{
	/**
     * 查询短信模板信息
     * 
     * @param id 短信模板ID
     * @return 短信模板信息
     */
	public SysSmsTemplate selectSysSmsTemplateById(Integer id);
	/**
	 * 根据模板ID查询模板信息
	 * @param templateId
	 * @return
	 */
	public SysSmsTemplate selectSmsTemplateByTemplateId(String  templateId);
	
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
     * 删除短信模板信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysSmsTemplateByIds(String ids);
	
}
