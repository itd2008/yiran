package com.yiran.message.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.message.mapper.SysSmsConfigMapper;
import com.yiran.message.domain.SysSmsConfig;
import com.yiran.message.service.ISysSmsConfigService;
import com.yiran.common.support.Convert;

/**
 * 短信基本设置 服务层实现
 * 
 * @author yiran
 * @date 2019-03-08
 */
@Service
public class SysSmsConfigServiceImpl implements ISysSmsConfigService 
{
	@Autowired
	private SysSmsConfigMapper sysSmsConfigMapper;

	/**
     * 查询短信基本设置信息
     * 
     * @param id 短信基本设置ID
     * @return 短信基本设置信息
     */
    @Override
	public SysSmsConfig selectSysSmsConfigById(Integer id)
	{
	    return sysSmsConfigMapper.selectSysSmsConfigById(id);
	}
	
	/**
     * 查询短信基本设置列表
     * 
     * @param sysSmsConfig 短信基本设置信息
     * @return 短信基本设置集合
     */
	@Override
	public List<SysSmsConfig> selectSysSmsConfigList(SysSmsConfig sysSmsConfig)
	{
	    return sysSmsConfigMapper.selectSysSmsConfigList(sysSmsConfig);
	}
	
    /**
     * 新增短信基本设置
     * 
     * @param sysSmsConfig 短信基本设置信息
     * @return 结果
     */
	@Override
	public int insertSysSmsConfig(SysSmsConfig sysSmsConfig)
	{
		sysSmsConfig.setDelFlag("0");
	    return sysSmsConfigMapper.insertSysSmsConfig(sysSmsConfig);
	}
	
	/**
     * 修改短信基本设置
     * 
     * @param sysSmsConfig 短信基本设置信息
     * @return 结果
     */
	@Override
	public int updateSysSmsConfig(SysSmsConfig sysSmsConfig)
	{
	    return sysSmsConfigMapper.updateSysSmsConfig(sysSmsConfig);
	}

	/**
     * 删除短信基本设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSysSmsConfigByIds(String ids)
	{
		return sysSmsConfigMapper.deleteSysSmsConfigByIds(Convert.toStrArray(ids));
	}

	@Override
	public SysSmsConfig selectSmsConfigBySmsKey(String smsKey) {
		return sysSmsConfigMapper.selectSmsConfigBySmsKey(smsKey);
	}
	
}
