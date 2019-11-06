package com.yiran.reconciliation.service;

import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistake;

/**
 * <b>功能说明:交易模块对账差错处理接口</b>
 */
public interface IReconciliationTradeReconciliationService {

	/**
	 * 平台成功，银行记录不存在，或者银行失败，以银行为准
	 * 
	 * @param trxNo
	 *            平台交易流水
	 */
	public void bankMissOrBankFailBaseBank(String trxNo);

	/**
	 * 银行成功，平台失败。
	 * 
	 * @param trxNo
	 *            平台交易流水
	 * @param bankTrxNo
	 *            银行返回流水
	 */
	public void platFailBankSuccess(String trxNo, String bankTrxNo);

	/**
	 * 处理金额不匹配异常(都是以银行数据为准才需要调整)
	 * 
	 * @param mistake
	 *            差错记录
	 * @param isBankMore
	 *            是否是银行金额多
	 */
	public void handleAmountMistake(ReconciliationAccountCheckMistake mistake, boolean isBankMore) ;

	/**
	 * 处理手续费不匹配差错（默认以银行为准）
	 * 
	 * @param mistake
	 */
	public void handleFeeMistake(ReconciliationAccountCheckMistake mistake);

}
