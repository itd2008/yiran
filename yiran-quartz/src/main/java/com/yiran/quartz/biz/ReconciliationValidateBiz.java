package com.yiran.quartz.biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiran.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistake;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistakeScratchPool;
import com.yiran.reconciliation.enums.BatchStatusEnum;
import com.yiran.reconciliation.enums.MistakeHandleStatusEnum;
import com.yiran.reconciliation.enums.ReconciliationMistakeTypeEnum;
import com.yiran.reconciliation.service.IReconciliationAccountCheckBatchService;
import com.yiran.reconciliation.service.IReconciliationAccountCheckMistakeScratchPoolService;
import com.yiran.reconciliation.utils.ReconciliationDateUtils;
/**
 * 对账验证biz，(检查是否已经对过账).
 * @author pandaa
 *
 */
@Component("reconciliationValidateBiz")
public class ReconciliationValidateBiz {
	private static final Logger LOG = LoggerFactory.getLogger(ReconciliationValidateBiz.class);
	@Autowired
	private IReconciliationAccountCheckBatchService reconciliationAccountCheckBatchService;
	@Autowired
	private IReconciliationAccountCheckMistakeScratchPoolService reconciliationAccountCheckMistakeScratchPoolService;
	/**
	 * 判断某支付方式某天是否对过账，避免重复对账
	 * 
	 * @param interfaceCode
	 *            支付方式
	 * @param billDate
	 *            账单日
	 * @return
	 */
	public Boolean isChecked(String fundChannelCode, Date billDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String billDateStr = sdf.format(billDate);
		LOG.info("检查,支付方式[" + fundChannelCode + "],订单日期[" + billDateStr + "]");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billDate", billDateStr);
		paramMap.put("fundChannelCode", fundChannelCode);
		// 除非对账错误或者对账异常才可以发起第二次对账
		paramMap.put("status", "'"+BatchStatusEnum.ERROR.name() + "','" + BatchStatusEnum.FAIL.name()+"'");

		List<ReconciliationAccountCheckBatch> list = reconciliationAccountCheckBatchService.selectBatchList(paramMap);
		if (list.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 如果缓冲池中有三天前的数据就清理掉并记录差错
	 */
	public void validateScratchPool() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(ReconciliationDateUtils.addDay(new Date(), -3));
		// 查询是否有创建时间是三天前的数据
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("maxDate", dateStr);
		List<ReconciliationAccountCheckMistakeScratchPool> list = reconciliationAccountCheckMistakeScratchPoolService.listScratchPoolRecord(paramMap);
		List<ReconciliationAccountCheckMistake> mistakeList = null;
		// 如果有数据
		if (!list.isEmpty()) {
			mistakeList = new ArrayList<ReconciliationAccountCheckMistake>();
			for (ReconciliationAccountCheckMistakeScratchPool scratchRecord : list) {
				// 创建差错记录
				ReconciliationAccountCheckMistake mistake = new ReconciliationAccountCheckMistake();
				mistake.setAccountCheckBatchNo(scratchRecord.getBatchNo());
				mistake.setBillDate(scratchRecord.getBillDate());
				mistake.setErrType(ReconciliationMistakeTypeEnum.BANK_MISS.name());
				mistake.setHandleStatus(MistakeHandleStatusEnum.NOHANDLE.name());
				mistake.setBankType(scratchRecord.getFundChannelCode());

				mistake.setOrderNo(scratchRecord.getMerchantOrderNo());
				mistake.setTradeTime(scratchRecord.getPaySuccessTime());
				mistake.setTrxNo(scratchRecord.getTrxNo());
				mistake.setOrderAmount(scratchRecord.getOrderAmount());
				mistake.setRefundAmount(scratchRecord.getSuccessRefundAmount());
				mistake.setTradeStatus(scratchRecord.getStatus());
				mistake.setFee(scratchRecord.getPlatCost());
				mistakeList.add(mistake);
			}

			reconciliationAccountCheckMistakeScratchPoolService.removeDateFromPool(list, mistakeList);

		}

	
		
	}
	
}
