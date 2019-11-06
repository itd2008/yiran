package com.yiran.quartz.biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.service.IPayInstOrderService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 平台数据获取biz业务类.
 * 
 */
@Component("reconciliationDataGetBiz")
public class ReconciliationDataGetBiz {

	private static final Log LOG = LogFactory.getLog(ReconciliationDataGetBiz.class);

	@Autowired
	private IPayInstOrderService payInstOrderService;

	/**
	 * 获取平台指定支付渠道、指定订单日下[所有成功]的数据
	 * 
	 * @param billDate
	 *            账单日
	 * @param interfaceCode
	 *            支付渠道
	 * @return
	 */
	public List<PayInstOrder> getSuccessPlatformDateByBillDate(Date billDate, String fundChannelCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String billDateStr = sdf.format(billDate);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billDate", billDateStr);
		paramMap.put("fundChannelCode", fundChannelCode);
		paramMap.put("status", InstOrderStatus.SUCCESSFUL.getCode());

		LOG.info("开始查询平台支付成功的数据：billDate[" + billDateStr + "],支付渠道为[" + fundChannelCode + "]");
		List<PayInstOrder> recordList = payInstOrderService.listPaymentRecord(paramMap);
		if (recordList == null) {
			recordList = new ArrayList<PayInstOrder>();
		}
		LOG.info("查询得到的数据count[" + recordList.size() + "]");
		return recordList;

	}

	/**
	 * 获取平台指定支付渠道、指定订单日下[所有]的数据
	 * 
	 * @param billDate
	 *            账单日
	 * @param interfaceCode
	 *            支付渠道
	 * @return
	 */
	public List<PayInstOrder> getAllPlatformDateByBillDate(Date billDate, String fundChannelCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String billDateStr = sdf.format(billDate);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("billDate", billDateStr);
		paramMap.put("fundChannelCode", fundChannelCode);

		LOG.info("开始查询平台支付所有的数据：billDate[" + billDateStr + "],支付渠道为[" + fundChannelCode + "]");
		List<PayInstOrder> recordList = payInstOrderService.listPaymentRecord(paramMap);
		if (recordList == null) {
			recordList = new ArrayList<PayInstOrder>();
		}
		LOG.info("查询得到的数据count[" + recordList.size() + "]");

		return recordList;

	}
}
