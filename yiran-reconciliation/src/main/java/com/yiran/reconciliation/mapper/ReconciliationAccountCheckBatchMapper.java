package com.yiran.reconciliation.mapper;

import com.yiran.reconciliation.domain.ReconciliationAccountCheckBatch;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;	

/**
 * 对账批次 数据层
 * 
 * @author yiran
 * @date 2019-09-20
 */
public interface ReconciliationAccountCheckBatchMapper 
{
	/**
     * 查询对账批次信息
     * 
     * @param id 对账批次ID
     * @return 对账批次信息
     */
	public ReconciliationAccountCheckBatch selectReconciliationAccountCheckBatchById(Integer id);
	
	/**
     * 查询对账批次列表
     * 
     * @param reconciliationAccountCheckBatch 对账批次信息
     * @return 对账批次集合
     */
	public List<ReconciliationAccountCheckBatch> selectReconciliationAccountCheckBatchList(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch);
	
	/**
     * 新增对账批次
     * 
     * @param reconciliationAccountCheckBatch 对账批次信息
     * @return 结果
     */
	public int insertReconciliationAccountCheckBatch(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch);
	
	/**
     * 修改对账批次
     * 
     * @param reconciliationAccountCheckBatch 对账批次信息
     * @return 结果
     */
	public int updateReconciliationAccountCheckBatch(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch);
	
	/**
     * 删除对账批次
     * 
     * @param id 对账批次ID
     * @return 结果
     */
	public int deleteReconciliationAccountCheckBatchById(Integer id);
	
	/**
     * 批量删除对账批次
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReconciliationAccountCheckBatchByIds(String[] ids);

	/**
	 * 根据渠道编号、对账日期、除非对账错误或者对账异常获取集合列表
	 * @param billDateStr
	 * @param fundChannelCode
	 * @param status
	 * @return
	 */
	public List<ReconciliationAccountCheckBatch> selectBatchList(Map<String,Object> paramMap);
	
}