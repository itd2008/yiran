package com.yiran.reconciliation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReconciliationTest {
	
	public static void main(String[] args) {
		String totalRawData = "`2,`0.02,`0.00,`0.00,`0.00000,`0.02,`0.00";
		Pattern totalPattern = Pattern.compile("^`(.*?),`(.*?),`(.*?),`(.*?),`(.*?),`(.*?),`(.*?)$");
		Matcher totalMatcher = totalPattern.matcher(totalRawData);
		if (totalMatcher.find()) {
			// 总交易单数
			String totalCount = totalMatcher.group(1);
			System.out.println("totalCount="+totalCount);
			// 总交易额
			String totalAmountStr = totalMatcher.group(2);
			System.out.println("totalAmountStr="+totalAmountStr);
			// 总退款金额
			String refundAmountStr = totalMatcher.group(3);
			System.out.println("refundAmountStr="+refundAmountStr);
			// 手续费总金额
			String feeAmountStr = totalMatcher.group(5);
			System.out.println("feeAmountStr="+feeAmountStr);
			//订单总金额
			String totalAmount = totalMatcher.group(6);
			System.out.println("totalAmount="+totalAmount);
			//申请退款总金额
			String retotalAmount = totalMatcher.group(7);
			System.out.println("retotalAmount="+retotalAmount);
		}
	}

}
