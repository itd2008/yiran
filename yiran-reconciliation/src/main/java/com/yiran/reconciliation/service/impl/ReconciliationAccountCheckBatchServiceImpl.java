package com.yiran.reconciliation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.reconciliation.mapper.ReconciliationAccountCheckBatchMapper;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiran.reconciliation.service.IReconciliationAccountCheckBatchService;
import com.yiran.common.support.Convert;

/**
 * 对账批次 服务层实现
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Service
public class ReconciliationAccountCheckBatchServiceImpl implements IReconciliationAccountCheckBatchService 
{
	@Autowired
	private ReconciliationAccountCheckBatchMapper reconciliationAccountCheckBatchMapper;

	/**
     * 查询对账批次信息
     * 
     * @param id 对账批次ID
     * @return 对账批次信息
     */
    @Override
	public ReconciliationAccountCheckBatch selectReconciliationAccountCheckBatchById(Integer id)
	{
	    return reconciliationAccountCheckBatchMapper.selectReconciliationAccountCheckBatchById(id);
	}
	
	/**
     * 查询对账批次列表
     * 
     * @param reconciliationAccountCheckBatch 对账批次信息
     * @return 对账批次集合
     */
	@Override
	public List<ReconciliationAccountCheckBatch> selectReconciliationAccountCheckBatchList(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{
	    return reconciliationAccountCheckBatchMapper.selectReconciliationAccountCheckBatchList(reconciliationAccountCheckBatch);
	}
	
    /**
     * 新增对账批次
     * 
     * @param reconciliationAccountCheckBatch 对账批次信息
     * @return 结果
     */
	@Override
	public int insertReconciliationAccountCheckBatch(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{
	    return reconciliationAccountCheckBatchMapper.insertReconciliationAccountCheckBatch(reconciliationAccountCheckBatch);
	}
	
	/**
     * 修改对账批次
     * 
     * @param reconciliationAccountCheckBatch 对账批次信息
     * @return 结果
     */
	@Override
	public int updateReconciliationAccountCheckBatch(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{
	    return reconciliationAccountCheckBatchMapper.updateReconciliationAccountCheckBatch(reconciliationAccountCheckBatch);
	}

	/**
     * 删除对账批次对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReconciliationAccountCheckBatchByIds(String ids)
	{
		return reconciliationAccountCheckBatchMapper.deleteReconciliationAccountCheckBatchByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<ReconciliationAccountCheckBatch> selectBatchList(Map<String, Object> paramMap) {
		
		return reconciliationAccountCheckBatchMapper.selectBatchList(paramMap);
	}
	
}
