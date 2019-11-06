package com.yiran.reconciliation.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiran.reconciliation.mapper.ReconciliationAccountCheckMistakeScratchPoolMapper;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistake;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistakeScratchPool;
import com.yiran.reconciliation.service.IReconciliationAccountCheckMistakeScratchPoolService;
import com.yiran.reconciliation.service.IReconciliationAccountCheckMistakeService;
import com.yiran.common.support.Convert;

/**
 * 差错暂存池 服务层实现
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Service
public class ReconciliationAccountCheckMistakeScratchPoolServiceImpl implements IReconciliationAccountCheckMistakeScratchPoolService 
{
	private static final Log LOG = LogFactory.getLog(ReconciliationAccountCheckMistakeScratchPoolServiceImpl.class);
	@Autowired
	private ReconciliationAccountCheckMistakeScratchPoolMapper reconciliationAccountCheckMistakeScratchPoolMapper;
	@Autowired
	private IReconciliationAccountCheckMistakeService reconciliationAccountCheckMistakeService;
	/**
     * 查询差错暂存池信息
     * 
     * @param id 差错暂存池ID
     * @return 差错暂存池信息
     */
    @Override
	public ReconciliationAccountCheckMistakeScratchPool selectReconciliationAccountCheckMistakeScratchPoolById(Integer id)
	{
	    return reconciliationAccountCheckMistakeScratchPoolMapper.selectReconciliationAccountCheckMistakeScratchPoolById(id);
	}
	
	/**
     * 查询差错暂存池列表
     * 
     * @param reconciliationAccountCheckMistakeScratchPool 差错暂存池信息
     * @return 差错暂存池集合
     */
	@Override
	public List<ReconciliationAccountCheckMistakeScratchPool> selectReconciliationAccountCheckMistakeScratchPoolList(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool)
	{
	    return reconciliationAccountCheckMistakeScratchPoolMapper.selectReconciliationAccountCheckMistakeScratchPoolList(reconciliationAccountCheckMistakeScratchPool);
	}
	
    /**
     * 新增差错暂存池
     * 
     * @param reconciliationAccountCheckMistakeScratchPool 差错暂存池信息
     * @return 结果
     */
	@Override
	public int insertReconciliationAccountCheckMistakeScratchPool(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool)
	{
	    return reconciliationAccountCheckMistakeScratchPoolMapper.insertReconciliationAccountCheckMistakeScratchPool(reconciliationAccountCheckMistakeScratchPool);
	}
	
	/**
     * 修改差错暂存池
     * 
     * @param reconciliationAccountCheckMistakeScratchPool 差错暂存池信息
     * @return 结果
     */
	@Override
	public int updateReconciliationAccountCheckMistakeScratchPool(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool)
	{
	    return reconciliationAccountCheckMistakeScratchPoolMapper.updateReconciliationAccountCheckMistakeScratchPool(reconciliationAccountCheckMistakeScratchPool);
	}

	/**
     * 删除差错暂存池对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReconciliationAccountCheckMistakeScratchPoolByIds(String ids)
	{
		return reconciliationAccountCheckMistakeScratchPoolMapper.deleteReconciliationAccountCheckMistakeScratchPoolByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<ReconciliationAccountCheckMistakeScratchPool> listAllScratchPoolRecord() {
		
		return reconciliationAccountCheckMistakeScratchPoolMapper.listAllScratchPoolRecord();
	}

	@Override
	public void savaListDate(List<ReconciliationAccountCheckMistakeScratchPool> insertScreatchRecordList) {
		for (ReconciliationAccountCheckMistakeScratchPool mistakeScratchPool : insertScreatchRecordList) {
			insertReconciliationAccountCheckMistakeScratchPool(mistakeScratchPool);
		}
		
	}

	@Override
	public void deleteFromPool(List<ReconciliationAccountCheckMistakeScratchPool> removeScreatchRecordList) {
		for (ReconciliationAccountCheckMistakeScratchPool mistakeScratchPool : removeScreatchRecordList) {
			reconciliationAccountCheckMistakeScratchPoolMapper.deleteReconciliationAccountCheckMistakeScratchPoolById(mistakeScratchPool.getId());
		}
	}

	@Override
	public List<ReconciliationAccountCheckMistakeScratchPool> listScratchPoolRecord(Map<String, Object> paramMap) {
		return reconciliationAccountCheckMistakeScratchPoolMapper.listScratchPoolRecord(paramMap);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void removeDateFromPool(List<ReconciliationAccountCheckMistakeScratchPool> list, List<ReconciliationAccountCheckMistake> mistakeList) {
		LOG.info("========>  清理缓冲池中没有对账的数据，记录差错=========>");
		LOG.info("===> step1:保存差错记录====总共[" + mistakeList.size() + "]条");
		reconciliationAccountCheckMistakeService.saveListDate(mistakeList);
		LOG.info("===> step2:从缓存池中删除已匹配记录====总共[" + list.size() + "]条");
		deleteFromPool(list);
		LOG.info("<========  清理缓冲池中没有对账的数据，记录差错结束<=========");
	}
	
}
