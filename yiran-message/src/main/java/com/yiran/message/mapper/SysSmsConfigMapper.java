package com.yiran.message.mapper;

import com.yiran.message.domain.SysSmsConfig;
import java.util.List;	

/**
 * 短信基本设置 数据层
 * 
 * @author yiran
 * @date 2019-03-08
 */
public interface SysSmsConfigMapper 
{
	/**
     * 查询短信基本设置信息
     * 
     * @param id 短信基本设置ID
     * @return 短信基本设置信息
     */
	public SysSmsConfig selectSysSmsConfigById(Integer id);
	
	/**
     * 查询短信基本设置列表
     * 
     * @param sysSmsConfig 短信基本设置信息
     * @return 短信基本设置集合
     */
	public List<SysSmsConfig> selectSysSmsConfigList(SysSmsConfig sysSmsConfig);
	
	/**
     * 新增短信基本设置
     * 
     * @param sysSmsConfig 短信基本设置信息
     * @return 结果
     */
	public int insertSysSmsConfig(SysSmsConfig sysSmsConfig);
	
	/**
     * 修改短信基本设置
     * 
     * @param sysSmsConfig 短信基本设置信息
     * @return 结果
     */
	public int updateSysSmsConfig(SysSmsConfig sysSmsConfig);
	
	/**
     * 删除短信基本设置
     * 
     * @param id 短信基本设置ID
     * @return 结果
     */
	public int deleteSysSmsConfigById(Integer id);
	
	/**
     * 批量删除短信基本设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysSmsConfigByIds(String[] ids);

	/**
	 * 根据key获取短信配置对象
	 * @param smsKey
	 * @return
	 */
	public SysSmsConfig selectSmsConfigBySmsKey(String smsKey);
	
}