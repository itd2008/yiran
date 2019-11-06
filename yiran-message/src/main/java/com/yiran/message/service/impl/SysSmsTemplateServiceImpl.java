package com.yiran.message.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.message.mapper.SysSmsConfigMapper;
import com.yiran.message.mapper.SysSmsTemplateMapper;
import com.yiran.message.domain.SysSmsConfig;
import com.yiran.message.domain.SysSmsTemplate;
import com.yiran.message.service.ISysSmsTemplateService;
import com.yiran.common.support.Convert;

/**
 * 短信模板 服务层实现
 * 
 * @author yiran
 * @date 2019-03-08
 */
@Service
public class SysSmsTemplateServiceImpl implements ISysSmsTemplateService 
{
	@Autowired
	private SysSmsTemplateMapper sysSmsTemplateMapper;

	@Autowired
	private SysSmsConfigMapper sysSmsConfigMapper;
	/**
     * 查询短信模板信息
     * 
     * @param id 短信模板ID
     * @return 短信模板信息
     */
    @Override
	public SysSmsTemplate selectSysSmsTemplateById(Integer id)
	{
	    return sysSmsTemplateMapper.selectSysSmsTemplateById(id);
	}
	
	/**
     * 查询短信模板列表
     * 
     * @param sysSmsTemplate 短信模板信息
     * @return 短信模板集合
     */
	@Override
	public List<SysSmsTemplate> selectSysSmsTemplateList(SysSmsTemplate sysSmsTemplate)
	{
	    return sysSmsTemplateMapper.selectSysSmsTemplateList(sysSmsTemplate);
	}
	
    /**
     * 新增短信模板
     * 
     * @param sysSmsTemplate 短信模板信息
     * @return 结果
     */
	@Override
	public int insertSysSmsTemplate(SysSmsTemplate sysSmsTemplate)
	{
		sysSmsTemplate.setDelFlag("0");
		SysSmsConfig smsConfig =sysSmsConfigMapper.selectSmsConfigBySmsKey(SysSmsConfig.KEY_SMS_EFFECTIVE_TIME);
		String effctive_time = smsConfig.getSmsValue();
		String content = sysSmsTemplate.getTemplateContent().replace("{effctiveTime}", effctive_time);
		sysSmsTemplate.setTemplateContent(content);
	    return sysSmsTemplateMapper.insertSysSmsTemplate(sysSmsTemplate);
	}
	
	/**
     * 修改短信模板
     * 
     * @param sysSmsTemplate 短信模板信息
     * @return 结果
     */
	@Override
	public int updateSysSmsTemplate(SysSmsTemplate sysSmsTemplate)
	{
	    return sysSmsTemplateMapper.updateSysSmsTemplate(sysSmsTemplate);
	}

	/**
     * 删除短信模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSysSmsTemplateByIds(String ids)
	{
		return sysSmsTemplateMapper.deleteSysSmsTemplateByIds(Convert.toStrArray(ids));
	}

	@Override
	public SysSmsTemplate selectSmsTemplateByTemplateId(String templateId) {
		return sysSmsTemplateMapper.selectSmsTemplateByTemplateId(templateId);
	}
	
}
