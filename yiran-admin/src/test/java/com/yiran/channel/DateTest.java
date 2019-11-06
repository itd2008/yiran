package com.yiran.channel;

import java.util.Date;

import com.yiran.common.utils.DateUtils;

public class DateTest {
	
	public static void main(String[] args) {
		
		Date startDate = DateUtils.parseDateNewFormat("2019-04-20 15:25:20");
		Date endDate = DateUtils.parseDateNewFormat("2019-04-23 15:25:20");		
		boolean belongCalendar = DateUtils.belongCalendar(new Date(),startDate,endDate);
		System.out.println("是否有效："+belongCalendar);
	}

}
