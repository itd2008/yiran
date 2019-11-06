package com.yiran.weixin.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.common.utils.StringUtils;
import com.yiran.weixin.mapper.WeixinSettingMapper;
import com.yiran.system.domain.SysUser;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.service.IWeixinSettingService;
import com.yiran.common.support.Convert;

/**
 * 微信基本设置 服务层实现
 * 
 * @author yiran
 * @date 2018-07-17
 */
@Service
public class WeixinSettingServiceImpl implements IWeixinSettingService 
{
	@Autowired
	private WeixinSettingMapper weixinSettingMapper;

	/**
     * 查询微信基本设置信息
     * 
     * @param id 微信基本设置ID
     * @return 微信基本设置信息
     */
    @Override
	public WeixinSetting selectWeixinSettingById(Integer id)
	{
	    return weixinSettingMapper.selectWeixinSettingById(id);
	}
	
	/**
     * 查询微信基本设置列表
     * 
     * @param weixinSetting 微信基本设置信息
     * @return 微信基本设置集合
     */
	@Override
	public List<WeixinSetting> selectWeixinSettingList(WeixinSetting weixinSetting)
	{
	    return weixinSettingMapper.selectWeixinSettingList(weixinSetting);
	}
	
    /**
     * 新增微信基本设置
     * 
     * @param weixinSetting 微信基本设置信息
     * @return 结果
     */
	@Override
	public int insertWeixinSetting(WeixinSetting weixinSetting)
	{
		weixinSetting.setDelFlag("0");
	    return weixinSettingMapper.insertWeixinSetting(weixinSetting);
	}
	
	/**
     * 修改微信基本设置
     * 
     * @param weixinSetting 微信基本设置信息
     * @return 结果
     */
	@Override
	public int updateWeixinSetting(WeixinSetting weixinSetting)
	{
	    return weixinSettingMapper.updateWeixinSetting(weixinSetting);
	}
	
	/**
     * 保存微信基本设置
     * 
     * @param weixinSetting 微信基本设置信息
     * @return 结果
     */
	@Override
	public int saveWeixinSetting(WeixinSetting weixinSetting)
	{
	    Integer id = weixinSetting.getId();
		int rows = 0;
		if (StringUtils.isNotNull(id))
        {
			weixinSetting.setUpdateTime(new Date());
		    rows = weixinSettingMapper.updateWeixinSetting(weixinSetting);
		}
		else
        {
			weixinSetting.setCreateTime(new Date());
			weixinSetting.setUpdateTime(new Date());
			weixinSetting.setDelFlag("0");
		    rows = weixinSettingMapper.insertWeixinSetting(weixinSetting);
		}
		return rows;
	}
	
	/**
     * 删除微信基本设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWeixinSettingByIds(String ids)
	{
		return weixinSettingMapper.deleteWeixinSettingByIds(Convert.toStrArray(ids));
	}

	@Override
	public WeixinSetting getValueByKey(String key) {
		return weixinSettingMapper.getValueByKey(key);
	}
	
}
