package com.yiran.reconciliation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.reconciliation.mapper.ReconciliationChannelSettingMapper;
import com.yiran.reconciliation.domain.ReconciliationChannelSetting;
import com.yiran.reconciliation.service.IReconciliationChannelSettingService;
import com.yiran.common.support.Convert;

/**
 * 对账渠道设置 服务层实现
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Service
public class ReconciliationChannelSettingServiceImpl implements IReconciliationChannelSettingService 
{
	@Autowired
	private ReconciliationChannelSettingMapper reconciliationChannelSettingMapper;

	/**
     * 查询对账渠道设置信息
     * 
     * @param id 对账渠道设置ID
     * @return 对账渠道设置信息
     */
    @Override
	public ReconciliationChannelSetting selectReconciliationChannelSettingById(Integer id)
	{
	    return reconciliationChannelSettingMapper.selectReconciliationChannelSettingById(id);
	}
	
	/**
     * 查询对账渠道设置列表
     * 
     * @param reconciliationChannelSetting 对账渠道设置信息
     * @return 对账渠道设置集合
     */
	@Override
	public List<ReconciliationChannelSetting> selectReconciliationChannelSettingList(ReconciliationChannelSetting reconciliationChannelSetting)
	{
	    return reconciliationChannelSettingMapper.selectReconciliationChannelSettingList(reconciliationChannelSetting);
	}
	
    /**
     * 新增对账渠道设置
     * 
     * @param reconciliationChannelSetting 对账渠道设置信息
     * @return 结果
     */
	@Override
	public int insertReconciliationChannelSetting(ReconciliationChannelSetting reconciliationChannelSetting)
	{
	    return reconciliationChannelSettingMapper.insertReconciliationChannelSetting(reconciliationChannelSetting);
	}
	
	/**
     * 修改对账渠道设置
     * 
     * @param reconciliationChannelSetting 对账渠道设置信息
     * @return 结果
     */
	@Override
	public int updateReconciliationChannelSetting(ReconciliationChannelSetting reconciliationChannelSetting)
	{
	    return reconciliationChannelSettingMapper.updateReconciliationChannelSetting(reconciliationChannelSetting);
	}

	/**
     * 删除对账渠道设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReconciliationChannelSettingByIds(String ids)
	{
		return reconciliationChannelSettingMapper.deleteReconciliationChannelSettingByIds(Convert.toStrArray(ids));
	}
	
}
