package com.yiran.channel;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.base.BaseJunit;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.enums.RequestType;
import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.system.domain.SysOperLog;
/**
 * 路由测试
 * @author pandaa
 *
 */
public class RouterFundChannelTest extends BaseJunit{
	@Autowired
	private IFundChannelRouter fundChannelRouter;
	@Test
	public void routerTest(){
		
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("partnerId", "20000126504");
			TmFundChannel routerFundChannel = fundChannelRouter.routerFundChannel("BOC", PayMode.QUICKPAY, RequestType.FUND_IN.getCode(), map);
			System.out.println(routerFundChannel);
		} catch (RouteChannelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
