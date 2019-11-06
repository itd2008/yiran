package com.yiran.payorder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.base.BaseJunit;
import com.yiran.payorder.domain.QueryOrderResult;
import com.yiran.payorder.facade.InstOrderProcessFacade;

public class InstOrderQueryTest extends BaseJunit{

	@Autowired
	private InstOrderProcessFacade instOrderProcessFacade;
	@Test
	public void instOrderQueryTest(){
		String instOrderNo ="WXPAY20190820131453001006";
		QueryOrderResult queryOrderResult = instOrderProcessFacade.queryInstOrderResult(instOrderNo);
		System.out.println("机构订单结果查询返回结果:"+queryOrderResult);
		
	}
}
