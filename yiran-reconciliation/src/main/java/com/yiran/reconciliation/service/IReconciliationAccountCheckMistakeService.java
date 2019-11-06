package com.yiran.reconciliation.service;

import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistake;
import java.util.List;

/**
 * 对账差错 服务层
 * 
 * @author yiran
 * @date 2019-09-20
 */
public interface IReconciliationAccountCheckMistakeService 
{
	/**
     * 查询对账差错信息
     * 
     * @param id 对账差错ID
     * @return 对账差错信息
     */
	public ReconciliationAccountCheckMistake selectReconciliationAccountCheckMistakeById(Integer id);
	
	/**
     * 查询对账差错列表
     * 
     * @param reconciliationAccountCheckMistake 对账差错信息
     * @return 对账差错集合
     */
	public List<ReconciliationAccountCheckMistake> selectReconciliationAccountCheckMistakeList(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake);
	
	/**
     * 新增对账差错
     * 
     * @param reconciliationAccountCheckMistake 对账差错信息
     * @return 结果
     */
	public int insertReconciliationAccountCheckMistake(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake);
	
	/**
     * 修改对账差错
     * 
     * @param reconciliationAccountCheckMistake 对账差错信息
     * @return 结果
     */
	public int updateReconciliationAccountCheckMistake(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake);
		
	/**
     * 删除对账差错信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteReconciliationAccountCheckMistakeByIds(String ids);

	public void saveListDate(List<ReconciliationAccountCheckMistake> mistakeList);
	
}
