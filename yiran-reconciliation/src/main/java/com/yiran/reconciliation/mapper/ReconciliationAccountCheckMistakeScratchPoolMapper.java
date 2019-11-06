package com.yiran.reconciliation.mapper;

import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistakeScratchPool;
import java.util.List;
import java.util.Map;	

/**
 * 差错暂存池 数据层
 * 
 * @author yiran
 * @date 2019-09-20
 */
public interface ReconciliationAccountCheckMistakeScratchPoolMapper 
{
	/**
     * 查询差错暂存池信息
     * 
     * @param id 差错暂存池ID
     * @return 差错暂存池信息
     */
	public ReconciliationAccountCheckMistakeScratchPool selectReconciliationAccountCheckMistakeScratchPoolById(Integer id);
	
	/**
     * 查询差错暂存池列表
     * 
     * @param reconciliationAccountCheckMistakeScratchPool 差错暂存池信息
     * @return 差错暂存池集合
     */
	public List<ReconciliationAccountCheckMistakeScratchPool> selectReconciliationAccountCheckMistakeScratchPoolList(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool);
	
	/**
     * 新增差错暂存池
     * 
     * @param reconciliationAccountCheckMistakeScratchPool 差错暂存池信息
     * @return 结果
     */
	public int insertReconciliationAccountCheckMistakeScratchPool(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool);
	
	/**
     * 修改差错暂存池
     * 
     * @param reconciliationAccountCheckMistakeScratchPool 差错暂存池信息
     * @return 结果
     */
	public int updateReconciliationAccountCheckMistakeScratchPool(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool);
	
	/**
     * 删除差错暂存池
     * 
     * @param id 差错暂存池ID
     * @return 结果
     */
	public int deleteReconciliationAccountCheckMistakeScratchPoolById(Integer id);
	
	/**
     * 批量删除差错暂存池
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReconciliationAccountCheckMistakeScratchPoolByIds(String[] ids);

	/**
	 * 查询平台缓冲池中所有的数据
	 * @return
	 */
	public List<ReconciliationAccountCheckMistakeScratchPool> listAllScratchPoolRecord();

	/**
	 * 查询是否有创建时间是三天前的数据
	 * @param paramMap
	 * @return
	 */
	public List<ReconciliationAccountCheckMistakeScratchPool> listScratchPoolRecord(Map<String, Object> paramMap);
	
}