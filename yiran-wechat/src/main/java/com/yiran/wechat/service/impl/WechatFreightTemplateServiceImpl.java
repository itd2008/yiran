package com.yiran.wechat.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatFreightTemplateMapper;
import com.yiran.wechat.domain.WechatFreightTemplate;
import com.yiran.wechat.service.IWechatFreightTemplateService;
import com.yiran.common.support.Convert;

/**
 * 运费模板 服务层实现
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Service
public class WechatFreightTemplateServiceImpl implements IWechatFreightTemplateService 
{
	@Autowired
	private WechatFreightTemplateMapper wechatFreightTemplateMapper;

	/**
     * 查询运费模板信息
     * 
     * @param freightTemplateId 运费模板ID
     * @return 运费模板信息
     */
    @Override
	public WechatFreightTemplate selectWechatFreightTemplateById(Integer freightTemplateId)
	{
	    return wechatFreightTemplateMapper.selectWechatFreightTemplateById(freightTemplateId);
	}
	
	/**
     * 查询运费模板列表
     * 
     * @param wechatFreightTemplate 运费模板信息
     * @return 运费模板集合
     */
	@Override
	public List<WechatFreightTemplate> selectWechatFreightTemplateList(WechatFreightTemplate wechatFreightTemplate)
	{
		List<WechatFreightTemplate> list = wechatFreightTemplateMapper.selectWechatFreightTemplateList(wechatFreightTemplate);
		/*//包邮省code
		String[] parcelPostAreas = 
		//不包邮省code
		String[] nonDistributionArea = */
	    return list;
	}
	
    /**
     * 新增运费模板
     * 
     * @param wechatFreightTemplate 运费模板信息
     * @return 结果
     */
	@Override
	public int insertWechatFreightTemplate(WechatFreightTemplate wechatFreightTemplate)
	{
	    return wechatFreightTemplateMapper.insertWechatFreightTemplate(wechatFreightTemplate);
	}
	
	/**
     * 修改运费模板
     * 
     * @param wechatFreightTemplate 运费模板信息
     * @return 结果
     */
	@Override
	public int updateWechatFreightTemplate(WechatFreightTemplate wechatFreightTemplate)
	{
	    return wechatFreightTemplateMapper.updateWechatFreightTemplate(wechatFreightTemplate);
	}

	/**
     * 删除运费模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatFreightTemplateByIds(String ids)
	{
		return wechatFreightTemplateMapper.deleteWechatFreightTemplateByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<WechatFreightTemplate> selectWechatFreightTemplateList() {
		return wechatFreightTemplateMapper.selectWechatFreightTemplateLists();
	}
	
}
