package com.yiran.weixin.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.common.utils.StringUtils;
import com.yiran.weixin.domain.WeixinTemplate;
import com.yiran.weixin.mapper.WeixinTemplateMapper;
import com.yiran.weixin.service.IWeixinTemplateService;
import com.yiran.common.support.Convert;

/**
 * 微信模板 服务层实现
 * 
 * @author yiran
 * @date 2018-10-27
 */
@Service
public class WeixinTemplateServiceImpl implements IWeixinTemplateService 
{
	@Autowired
	private WeixinTemplateMapper weixinTemplateMapper;

	/**
     * 查询微信模板信息
     * 
     * @param id 微信模板ID
     * @return 微信模板信息
     */
    @Override
	public WeixinTemplate selectWeixinTemplateById(String id)
	{
	    return weixinTemplateMapper.selectWeixinTemplateById(id);
	}
	
	/**
     * 查询微信模板列表
     * 
     * @param weixinTemplate 微信模板信息
     * @return 微信模板集合
     */
	@Override
	public List<WeixinTemplate> selectWeixinTemplateList(WeixinTemplate weixinTemplate)
	{
	    return weixinTemplateMapper.selectWeixinTemplateList(weixinTemplate);
	}
	
    /**
     * 新增微信模板
     * 
     * @param weixinTemplate 微信模板信息
     * @return 结果
     */
	@Override
	public int insertWeixinTemplate(WeixinTemplate weixinTemplate)
	{
		weixinTemplate.setId(UUID.randomUUID().toString());
	    return weixinTemplateMapper.insertWeixinTemplate(weixinTemplate);
	}
	
	/**
     * 修改微信模板
     * 
     * @param weixinTemplate 微信模板信息
     * @return 结果
     */
	@Override
	public int updateWeixinTemplate(WeixinTemplate weixinTemplate)
	{
	    return weixinTemplateMapper.updateWeixinTemplate(weixinTemplate);
	}
	
	/**
     * 保存微信模板
     * 
     * @param weixinTemplate 微信模板信息
     * @return 结果
     */
	@Override
	public int saveWeixinTemplate(WeixinTemplate weixinTemplate)
	{
	    String id = weixinTemplate.getId();
		int rows = 0;
		if (StringUtils.isNotNull(id))
        {
		    rows = weixinTemplateMapper.updateWeixinTemplate(weixinTemplate);
		}
		else
        {
			weixinTemplate.setId(UUID.randomUUID().toString());
		    rows = weixinTemplateMapper.insertWeixinTemplate(weixinTemplate);
		}
		return rows;
	}
	
	/**
     * 删除微信模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWeixinTemplateByIds(String ids)
	{
		return weixinTemplateMapper.deleteWeixinTemplateByIds(Convert.toStrArray(ids));
	}
	
}
