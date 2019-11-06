package com.yiran.reconciliation.service;

import com.yiran.reconciliation.domain.ReconciliationChannelSetting;
import java.util.List;

/**
 * 对账渠道设置 服务层
 * 
 * @author yiran
 * @date 2019-09-20
 */
public interface IReconciliationChannelSettingService 
{
	/**
     * 查询对账渠道设置信息
     * 
     * @param id 对账渠道设置ID
     * @return 对账渠道设置信息
     */
	public ReconciliationChannelSetting selectReconciliationChannelSettingById(Integer id);
	
	/**
     * 查询对账渠道设置列表
     * 
     * @param reconciliationChannelSetting 对账渠道设置信息
     * @return 对账渠道设置集合
     */
	public List<ReconciliationChannelSetting> selectReconciliationChannelSettingList(ReconciliationChannelSetting reconciliationChannelSetting);
	
	/**
     * 新增对账渠道设置
     * 
     * @param reconciliationChannelSetting 对账渠道设置信息
     * @return 结果
     */
	public int insertReconciliationChannelSetting(ReconciliationChannelSetting reconciliationChannelSetting);
	
	/**
     * 修改对账渠道设置
     * 
     * @param reconciliationChannelSetting 对账渠道设置信息
     * @return 结果
     */
	public int updateReconciliationChannelSetting(ReconciliationChannelSetting reconciliationChannelSetting);
		
	/**
     * 删除对账渠道设置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReconciliationChannelSettingByIds(String ids);

	
}
