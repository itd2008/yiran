
package com.yiran.reconciliation.service;


import java.util.List;

import com.yiran.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistake;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistakeScratchPool;

/**
 * 对账数据事务一致性service.
 */
public interface IReconciliationAccountCheckTransactionService {

	/**
	 * 保存
	 */
	void saveDatasaveDate(ReconciliationAccountCheckBatch batch, List<ReconciliationAccountCheckMistake> mistakeList, List<ReconciliationAccountCheckMistakeScratchPool> insertScreatchRecordList, List<ReconciliationAccountCheckMistakeScratchPool> removeScreatchRecordList);

	/**
	 * 
	 * @param list
	 * @param mistakeList
	 */
	void removeDateFromPool(List<ReconciliationAccountCheckMistakeScratchPool> list, List<ReconciliationAccountCheckMistake> mistakeList);

	/**
	 * 差错处理
	 * 
	 * @param id
	 *            差错记录id
	 * @param handleType
	 *            差错处理方式
	 * @param handleRemark
	 *            差错备注
	 */
	void handle(String id, String handleType, String handleRemark);

}