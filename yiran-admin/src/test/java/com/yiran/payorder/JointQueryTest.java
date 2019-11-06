package com.yiran.payorder;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.base.BaseJunit;
import com.yiran.payorder.domaindo.VInstOrder;
import com.yiran.payorder.domaindo.VInstOrderResult;
import com.yiran.payorder.domaindo.VPayOrder;
import com.yiran.payorder.service.IJointQueryService;

public class JointQueryTest extends BaseJunit{
	@Autowired
	private IJointQueryService jointQueryService;
	
	@Test
	public void jointQueryTest(){
		VPayOrder vPayOrder = new VPayOrder();
		List<VPayOrder> channelPayOrderList = jointQueryService.selectChannelPayOrderList(vPayOrder);
		for (VPayOrder po : channelPayOrderList) {
			System.out.println(po);
		}
		System.out.println("----------------------------------------------------------");
		VInstOrder vInstOrder = new VInstOrder();
		List<VInstOrder> instOrderList = jointQueryService.selectInstOrderList(vInstOrder);
		for (VInstOrder io : instOrderList) {
			System.out.println(io);
		}
		
		System.out.println("----------------------------------------------------------");
		VInstOrderResult vInstOrderResult = new VInstOrderResult();
		List<VInstOrderResult> orderResultList = jointQueryService.selectInstOrderResultList(vInstOrderResult);
		for (VInstOrderResult or : orderResultList) {
			System.out.println(or);
		}
		
	}

}
