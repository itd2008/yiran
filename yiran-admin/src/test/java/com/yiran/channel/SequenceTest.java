package com.yiran.channel;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.base.BaseJunit;
import com.yiran.paychannel.service.ISequenceService;

public class SequenceTest extends BaseJunit{

	@Autowired
	private ISequenceService sequenceService;
	
	@Test
	public void testSequen(){
		int num = sequenceService.currval("ONLINE_BANK_LLPAY");
		System.out.println(num);
		int num2 = sequenceService.setval("ONLINE_BANK_LLPAY", 2000);
		System.out.println(num2);
		int num3 = sequenceService.nextval("ONLINE_BANK_LLPAY");
		System.out.println(num3);
	}
}
