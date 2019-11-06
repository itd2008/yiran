package com.yiran.quartz.biz;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistake;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistakeScratchPool;
import com.yiran.reconciliation.enums.HandleStatusEnum;
import com.yiran.reconciliation.enums.MistakeHandleStatusEnum;
import com.yiran.reconciliation.enums.ReconciliationMistakeTypeEnum;
import com.yiran.reconciliation.enums.TradeStatusEnum;
import com.yiran.reconciliation.service.IReconciliationAccountCheckMistakeScratchPoolService;
import com.yiran.reconciliation.service.IReconciliationAccountCheckTransactionService;
import com.yiran.reconciliation.vo.ReconciliationEntityVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 对账的核心业务biz.
 *
 */
@Component("reconciliationCheckBiz")
public class ReconciliationCheckBiz {

	private static final Log LOG = LogFactory.getLog(ReconciliationCheckBiz.class);

	@Autowired
	private IReconciliationAccountCheckMistakeScratchPoolService rpAccountCheckMistakeScratchPoolService;
	@Autowired
	private IReconciliationAccountCheckTransactionService rpAccountCheckTransactionService;

	@Autowired
	private ReconciliationDataGetBiz reconciliationDataGetBiz;

	/**
	 * 对账核心方法
	 * 
	 * @param bankList
	 *            对账文件解析出来的数据
	 * @param interfaceCode
	 *            支付渠道
	 * @param batch
	 *            对账批次记录
	 */
	public void check(List<ReconciliationEntityVo> bankList, String fundChannelCode, ReconciliationAccountCheckBatch batch) {
		// 判断bankList是否为空
		if (bankList == null) {
			bankList = new ArrayList<ReconciliationEntityVo>();
		}
		// 查询平台bill_date,fundChannelCode成功的交易
		List<PayInstOrder> platSucessDateList = reconciliationDataGetBiz.getSuccessPlatformDateByBillDate(batch.getBillDate(), fundChannelCode);

		// 查询平台bill_date,fundChannelCode所有的交易
		List<PayInstOrder> platAllDateList = reconciliationDataGetBiz.getAllPlatformDateByBillDate(batch.getBillDate(), fundChannelCode);

		// 查询平台缓冲池中所有的数据
		List<ReconciliationAccountCheckMistakeScratchPool> platScreatchRecordList = rpAccountCheckMistakeScratchPoolService.listAllScratchPoolRecord();

		// 差错list
		List<ReconciliationAccountCheckMistake> mistakeList = new ArrayList<ReconciliationAccountCheckMistake>();

		// 需要放入缓冲池中平台长款list
		List<ReconciliationAccountCheckMistakeScratchPool> insertScreatchRecordList = new ArrayList<ReconciliationAccountCheckMistakeScratchPool>();

		// 需要从缓冲池中移除的数据
		List<ReconciliationAccountCheckMistakeScratchPool> removeScreatchRecordList = new ArrayList<ReconciliationAccountCheckMistakeScratchPool>();

		LOG.info("  开始以平台的数据为准对账,平台长款记入缓冲池");
		baseOnPaltForm(platSucessDateList, bankList, mistakeList, insertScreatchRecordList, batch);
		LOG.info("结束以平台的数据为准对账");

		LOG.info("  开始以银行通道的数据为准对账");
		baseOnBank(platAllDateList, bankList, platScreatchRecordList, mistakeList, batch, removeScreatchRecordList);
		LOG.info(" 结束以银行通道的数据为准对账");

		// 保存数据
		rpAccountCheckTransactionService.saveDatasaveDate(batch, mistakeList, insertScreatchRecordList, removeScreatchRecordList);

	}

	/**
	 * 以平台的数据为准对账
	 * 
	 * @param platformDateList
	 *            平台dilldate的成功数据
	 * @param bankList
	 *            银行成功对账单数据
	 * 
	 * @param misTakeList
	 *            差错list
	 * @param screatchRecordList
	 *            需要放入缓冲池中平台长款list
	 * 
	 * @param batch
	 *            对账批次
	 */
	private void baseOnPaltForm(List<PayInstOrder> platformDateList, List<ReconciliationEntityVo> bankList, List<ReconciliationAccountCheckMistake> misTakeList, List<ReconciliationAccountCheckMistakeScratchPool> screatchRecordList, ReconciliationAccountCheckBatch batch) {
		BigDecimal platTradeAmount = BigDecimal.ZERO;// 平台交易总金额
		BigDecimal platFee = BigDecimal.ZERO;// 平台总手续费
		Integer tradeCount = 0;// 平台订单总数
		Integer mistakeCount = 0;

		for (PayInstOrder record : platformDateList) {
			Boolean flag = false;// 用于标记是否有匹配
			// 累计平台交易总金额和总手续费
			platTradeAmount = platTradeAmount.add(record.getAmount().getAmount());
			//TODO 目前还没有做手续费计算 2019-09-23
			//platFee = platFee.add(record.getPlatCost() == null ? BigDecimal.ZERO : record.getPlatCost());
			tradeCount++;
			for (ReconciliationEntityVo bankRecord : bankList) {
				// 如果银行账单中有匹配数据：进行金额，手续费校验
				if (record.getInstOrderNo().equalsIgnoreCase(bankRecord.getBankOrderNo())) {
					flag = true;// 标记已经找到匹配

					/** step1:匹配订单金额 **/
					// 平台金额多
					if (record.getAmount().getAmount().compareTo(bankRecord.getBankAmount()) == 1) {
						// 金额不匹配，创建差错记录
						ReconciliationAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_OVER_CASH_MISMATCH, batch);
						misTakeList.add(misktake);
						mistakeCount++;
						break;
					}
					// 平台金额少
					else if (record.getAmount().getAmount().compareTo(bankRecord.getBankAmount()) == -1) {
						// 金额不匹配，创建差错记录
						ReconciliationAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_SHORT_CASH_MISMATCH, batch);
						misTakeList.add(misktake);
						mistakeCount++;
						break;
					}

					/** step2:匹配订单手续费 **/
					/*if (record.getPlatCost().compareTo(bankRecord.getBankFee()) != 0) {
						// 金额不匹配，创建差错记录
						ReconciliationAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.FEE_MISMATCH, batch);
						misTakeList.add(misktake);
						mistakeCount++;
						break;
					}*/

				}
			}
			// 没有找到匹配的记录，把这个订单记录到缓冲池中
			if (!flag) {
				ReconciliationAccountCheckMistakeScratchPool screatchRecord = getScratchRecord(record, batch);
				screatchRecordList.add(screatchRecord);
			}
		}

		// 统计数据保存
		batch.setTradeAmount(platTradeAmount);
		batch.setTradeCount(tradeCount);
		batch.setBankFee(platFee);
		batch.setMistakeCount(mistakeCount);
		batch.setUnhandleMistakeCount(mistakeCount);
	}

	/**
	 * 以银行的数据为准对账
	 * 
	 * @param bankList
	 *            银行对账单数据
	 * 
	 * @param misTakeList
	 *            差错list
	 * 
	 * @param platScreatchRecordList
	 *            平台缓冲池中的数据
	 * 
	 * @param batch
	 *            对账批次
	 */
	private void baseOnBank(List<PayInstOrder> platAllDateList, List<ReconciliationEntityVo> bankList, List<ReconciliationAccountCheckMistakeScratchPool> platScreatchRecordList, List<ReconciliationAccountCheckMistake> misTakeList, ReconciliationAccountCheckBatch batch, List<ReconciliationAccountCheckMistakeScratchPool> removeScreatchRecordList) {
		BigDecimal platTradeAmount = BigDecimal.ZERO;// 平台交易总金额
		BigDecimal platFee = BigDecimal.ZERO;// 平台总手续费
		Integer tradeCount = 0;// 平台订单总数
		Integer mistakeCount = 0;
		// 拿银行数据去对账
		for (ReconciliationEntityVo bankRecord : bankList) {

			boolean flag = false;// 用于标记是否有匹配
			for (PayInstOrder record : platAllDateList) {
				/** step1 检查有匹配的数据 **/
				if (bankRecord.getBankTrxNo().equals(record.getInstSeqNo())) {
					flag = true;
					/** step2： 判断平台状态是否匹配 **/
					/** 注意：状态匹配不需要做金额和手续费验证，以平台数据为基准对账已经做了验证 **/
					// 不匹配记录差错。
					if (!InstOrderStatus.SUCCESSFUL.getCode().equals(record.getStatus().getCode())) {
						ReconciliationAccountCheckMistake misktake1 = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_SHORT_STATUS_MISMATCH, batch);
						misTakeList.add(misktake1);
						mistakeCount++;
						// break;

						/** 订单状态不匹配验证完之后，在验证金额和手续费，差错处理必须先处理状态不符的情况 **/
						// 验证金额和手续费
						/** step1:匹配订单金额 **/
						// 平台金额多
						if (record.getAmount().getAmount().compareTo(bankRecord.getBankAmount()) == 1) {
							// 金额不匹配，创建差错记录
							ReconciliationAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_OVER_CASH_MISMATCH, batch);
							misTakeList.add(misktake);
							mistakeCount++;
							break;
						}
						// 平台金额少
						else if (record.getAmount().getAmount().compareTo(bankRecord.getBankAmount()) == -1) {
							// 金额不匹配，创建差错记录
							ReconciliationAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_SHORT_CASH_MISMATCH, batch);
							misTakeList.add(misktake);
							mistakeCount++;
							break;
						}

						/** TODO:暂无手续费 step2:匹配订单手续费 **/
						/*if (record.getPlatCost().compareTo(bankRecord.getBankFee()) != 0) {
							// 金额不匹配，创建差错记录
							ReconciliationAccountCheckMistake misktake = createMisktake(null, record, bankRecord, ReconciliationMistakeTypeEnum.FEE_MISMATCH, batch);
							misTakeList.add(misktake);
							mistakeCount++;
							break;
						}*/

						batch.setHandleStatus(HandleStatusEnum.NOHANDLE.name());
					}
				}
			}

			/** step3： 如果没有匹配的数据，去缓冲池中查找对账，如果没有记录差错 **/
			if (!flag) {
				// 去缓冲池中查找对账(前提是缓冲池里面有数据)
				if (platScreatchRecordList != null)
					for (ReconciliationAccountCheckMistakeScratchPool scratchRecord : platScreatchRecordList) {

						// 找到匹配的
						if (scratchRecord.getBankOrderNo().equals(bankRecord.getBankOrderNo())) {
							// 累计平台交易总金额和总手续费
							platTradeAmount = platTradeAmount.add(scratchRecord.getOrderAmount());
							platFee = platFee.add(scratchRecord.getPlatCost() == null ? BigDecimal.ZERO : scratchRecord.getPlatCost());
							tradeCount++;
							flag = true;

							// 验证金额和手续费
							/** step1:匹配订单金额 **/
							// 平台金额多
							if (scratchRecord.getOrderAmount().compareTo(bankRecord.getBankAmount()) == 1) {
								// 金额不匹配，创建差错记录
								ReconciliationAccountCheckMistake misktake = createMisktake(scratchRecord, null, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_OVER_CASH_MISMATCH, batch);
								misTakeList.add(misktake);
								mistakeCount++;
								break;
							}
							// 平台金额少
							else if (scratchRecord.getOrderAmount().compareTo(bankRecord.getBankAmount()) == -1) {
								// 金额不匹配，创建差错记录
								ReconciliationAccountCheckMistake misktake = createMisktake(scratchRecord, null, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_SHORT_CASH_MISMATCH, batch);
								misTakeList.add(misktake);
								mistakeCount++;
								break;
							}

							/** step2:匹配订单手续费 **/
							if (scratchRecord.getPlatCost().compareTo(bankRecord.getBankFee()) != 0) {
								// 金额不匹配，创建差错记录
								ReconciliationAccountCheckMistake misktake = createMisktake(scratchRecord, null, bankRecord, ReconciliationMistakeTypeEnum.FEE_MISMATCH, batch);
								misTakeList.add(misktake);
								mistakeCount++;
								break;
							}

							/** step3:把缓存池中匹配的记录删除掉 **/
							removeScreatchRecordList.add(scratchRecord);
						}
					}
			}

			// 缓冲池中还是没有这条记录,直接记录差错，差错类型为 PLATFORM_MISS("平台漏单")
			if (!flag) {
				ReconciliationAccountCheckMistake misktake = createMisktake(null, null, bankRecord, ReconciliationMistakeTypeEnum.PLATFORM_MISS, batch);
				misTakeList.add(misktake);
				mistakeCount++;
			}
		}

		// 统计数据保存
		batch.setTradeAmount(batch.getTradeAmount().add(platTradeAmount));
		batch.setTradeCount(batch.getTradeCount() + tradeCount);
		//batch.setFee(batch.getFee().add(platFee));
		batch.setMistakeCount(batch.getMistakeCount() + mistakeCount);
		batch.setUnhandleMistakeCount(batch.getMistakeCount());
	}

	/**
	 * 创建差错记录
	 * 
	 * @param scratchRecord
	 *            平台缓冲池中的订单记录
	 * @param record
	 *            平台订单记录
	 * @param bankRecord
	 *            银行账单记录
	 * @param mistakeType
	 *            差错类型
	 * @return 注意：scratchRecord和record 至少有一个为空
	 */
	private ReconciliationAccountCheckMistake createMisktake(ReconciliationAccountCheckMistakeScratchPool scratchRecord, PayInstOrder record, ReconciliationEntityVo bankRecord, ReconciliationMistakeTypeEnum mistakeType, ReconciliationAccountCheckBatch batch) {

		ReconciliationAccountCheckMistake mistake = new ReconciliationAccountCheckMistake();
		mistake.setAccountCheckBatchNo(batch.getBatchNo());
		mistake.setBillDate(batch.getBillDate());
		mistake.setErrType(mistakeType.name());
		mistake.setHandleStatus(MistakeHandleStatusEnum.NOHANDLE.name());
		mistake.setBankType(batch.getBankType());
		mistake.setOrderTime(record.getGmtCreate());
		if (record != null) {
			/**
			 * 商家编号
			 */
			mistake.setMerchantNo(record.getExtension().get("partnerId"));
			/**
			 * 商家订单号
			 */
			mistake.setOrderNo(record.getInstOrderNo());
			/**平台交易时间 */
			mistake.setTradeTime(record.getGmtCreate());
			/** 平台流水号 */
			mistake.setTrxNo(record.getPaymentSeqNo());
			/** 平台交易金额 */
			mistake.setOrderAmount(record.getAmount().getAmount());
			/** 平台退款金额  */
			//mistake.setRefundAmount(record.getSuccessRefundAmount());
			/** 平台交易状态 */
			if("F".equals(record.getStatus().getCode())){
				mistake.setTradeStatus(TradeStatusEnum.FAILED.name());
			}else if("S".equals(record.getStatus().getCode())){
				mistake.setTradeStatus(TradeStatusEnum.SUCCESS.name());
			}
			
			/** 平台手续费 */
			//mistake.setFee(record.getPlatCost());
		}

		if (scratchRecord != null) {
			mistake.setOrderNo(scratchRecord.getMerchantOrderNo());
			mistake.setTradeTime(scratchRecord.getPaySuccessTime());
			mistake.setTrxNo(scratchRecord.getTrxNo());
			mistake.setOrderAmount(scratchRecord.getOrderAmount());
			mistake.setRefundAmount(scratchRecord.getSuccessRefundAmount());
			mistake.setTradeStatus(scratchRecord.getStatus());
			mistake.setFee(scratchRecord.getPlatCost());
		}

		if (bankRecord != null) {
			mistake.setBankAmount(bankRecord.getBankAmount());
			mistake.setBankFee(bankRecord.getBankFee());
			mistake.setBankOrderNo(bankRecord.getBankOrderNo());
			mistake.setBankRefundAmount(bankRecord.getBankRefundAmount());
			mistake.setBankTradeStatus(bankRecord.getBankTradeStatus());
			mistake.setBankTradeTime(bankRecord.getBankTradeTime());
			mistake.setBankTrxNo(bankRecord.getBankTrxNo());
		}
		return mistake;

	}

	/**
	 * 得到缓存记录：用于放入缓冲池
	 * 
	 * @param record
	 *            支付记录
	 * @param batch
	 *            对账批次记录
	 * @return
	 */
	private ReconciliationAccountCheckMistakeScratchPool getScratchRecord(PayInstOrder record, ReconciliationAccountCheckBatch batch) {

		ReconciliationAccountCheckMistakeScratchPool scratchRecord = new ReconciliationAccountCheckMistakeScratchPool();
		/** 银行订单号 */
		scratchRecord.setBankOrderNo(record.getInstSeqNo());
		/** 银行流水号 */
		scratchRecord.setBankTrxNo(record.getInstSeqNo());
		/** 完成时间 */
		scratchRecord.setCompleteTime(record.getGmtCreate());
		/** 支付成功时间 */
		scratchRecord.setPaySuccessTime(record.getGmtModified());
		/** 商户订单号 */
		scratchRecord.setMerchantOrderNo(record.getInstOrderNo());
		/** 订单金额 */
		scratchRecord.setOrderAmount(record.getAmount().getAmount());
		/** 平台成本 */
		//scratchRecord.setPlatCost(record.getPlatCost());
		/** 支付渠道*/
		scratchRecord.setFundChannelCode(record.getFundChannelCode());
		/** 支付流水号 */
		scratchRecord.setTrxNo(record.getPaymentSeqNo());
		/*** 订单状态*/
		scratchRecord.setStatus(record.getStatus().getCode());
		/** 对账批次号 */
		scratchRecord.setBatchNo(batch.getBatchNo());
		/** 对账时间 */
		scratchRecord.setBillDate(batch.getBillDate());
		return scratchRecord;
	}
}
