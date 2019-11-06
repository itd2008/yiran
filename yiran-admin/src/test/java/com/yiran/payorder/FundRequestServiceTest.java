package com.yiran.payorder;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.netfinworks.common.domain.Extension;
import com.yiran.base.BaseJunit;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.payorder.domain.ChannelResult;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.PayOrderRequest;
import com.yiran.payorder.response.PayFundResult;
import com.yiran.payorder.service.IFundRequestService;

public class FundRequestServiceTest extends BaseJunit{
	@Autowired
	private IFundRequestService fundRequestService;
	
	@Test
	public void apply(){
		PayOrderRequest request = new PayOrderRequest();
		BigDecimal money = new BigDecimal(1000).setScale(2, BigDecimal.ROUND_DOWN);
		request.setAmount(money);
		request.setBizTime(new Date());
		request.setBizType(BizType.FUNDIN);
		request.setCurrencyCode("CNY");
		request.setInstCode("CCB");
		request.setMemberId("100001605065");
		request.setPayMode(PayMode.QUICKPAY);
		request.setPaymentCode("1001");
		request.setPaymentSeqNo("20190818FI035527021");
		request.setProductCode("20040001");
		request.setSettlementId("2019081820617952");
		Extension ext = new Extension();
		ext.add("idNo", "411081199511024992");
		ext.add("cardNo", "6217002440002924587");
		ext.add("mobileNo", "13260695779");
		ext.add("idType", "IC");
		ext.add("accountName", "高鹏辉");
		ext.add("verifyFlag", "Y");
		ext.add("signed", "N");
		ext.add("tradeVoucherNos", "101156378589524053613");
		ext.add("COMPANY_OR_PERSONAL", "C");
		ext.add("DBCR", "DC");
		ext.add("ACCESS_CHANNEL", "WEB");
		ext.add("smsSender", "PLATFORM");
		ext.add("GATE_ORDER_NO", "301156378589525153614");
		ext.add("paymentOrderNo", "301156378589525153614");
		ext.add("payeeId", "innerMember");
		ext.add("partnerId", "20000126504");
		ext.add("clientId", "payment");
		ext.add("PAGE_URL", "http://192.168.79.128:8087/api/yiran/channelpay/notify/resultNotify");
		request.setExtension(ext);
		PayFundResult result = fundRequestService.apply(request);
		
		System.out.println(JSON.toJSONString(result));
		
	}
	
	
	@Test
	public void wxApply(){
		PayOrderRequest request = new PayOrderRequest();
		BigDecimal money = new BigDecimal(0.01).setScale(2, BigDecimal.ROUND_DOWN);
		request.setAmount(money);
		request.setBizTime(new Date());
		request.setBizType(BizType.FUNDIN);
		request.setCurrencyCode("CNY");
		request.setInstCode("WXPAY");
		request.setMemberId("100001605065");
		request.setPayMode(PayMode.TRUSTCOLLECT);
		request.setPaymentCode("1001");
		request.setPaymentSeqNo("20190820FI035527114");
		request.setProductCode("20040001");
		request.setSettlementId("2019082020617114");
		Extension ext = new Extension();
		ext.add("openId", "oAv2w07K6_YrG4nW9qvEGMy2ydY8");
		ext.add("orderName", "测试订单");
		ext.add("mobileNo", "13260695779");
		ext.add("idType", "IC");
		ext.add("accountName", "高鹏辉");
		ext.add("verifyFlag", "Y");
		ext.add("signed", "N");
		ext.add("tradeVoucherNos", "101156378589524053613");
		ext.add("COMPANY_OR_PERSONAL", "C");
		ext.add("DBCR", "DC");
		ext.add("ACCESS_CHANNEL", "WEB");
		ext.add("smsSender", "PLATFORM");
		ext.add("GATE_ORDER_NO", "301156378589525153614");
		ext.add("paymentOrderNo", "301156378589525153614");
		ext.add("payeeId", "innerMember");
		ext.add("partnerId", "20000126505");
		ext.add("clientId", "payment");
		ext.add("PAGE_URL", "http://192.168.79.128:8087/api/yiran/channelpay/notify/resultNotify");
		request.setExtension(ext);
		PayFundResult result = fundRequestService.apply(request);
		
		System.out.println(JSON.toJSONString(result));
		
	}
	
	
	@Test
	public void wxBillDownLoad(){
		ChannelRequest channelRequest = new ChannelRequest();
		channelRequest.setFundChannelCode("WXPAY10102");
		channelRequest.setApiType(FundChannelApiType.FILE_DOWN);
		channelRequest.setInstOrderNo("WXPAY2019092103");
		channelRequest.getExtension().put("billDate", "20190919");
		ChannelResult result = fundRequestService.download(channelRequest);
		
		System.out.println(JSON.toJSONString(result));
		
	}
	
	@Test
	public void aliPayTest(){
		PayOrderRequest request = new PayOrderRequest();
		BigDecimal money = new BigDecimal(0.01).setScale(2, BigDecimal.ROUND_DOWN);
		request.setAmount(money);
		request.setBizTime(new Date());
		request.setBizType(BizType.FUNDIN);
		request.setCurrencyCode("CNY");
		request.setInstCode("ALIPAY");
		request.setMemberId("100001605065");
		request.setPayMode(PayMode.TRUSTCOLLECT);
		request.setPaymentCode("1001");
		request.setPaymentSeqNo("20191029FI035527131");
		request.setProductCode("20040001");
		request.setSettlementId("2019102920617131");
		Extension ext = new Extension();
		ext.add("orderName", "测试订单");
		ext.add("qrPayMode", "2");
		ext.add("mobileNo", "13260695779");
		ext.add("idType", "IC");
		ext.add("accountName", "高鹏辉");
		ext.add("verifyFlag", "Y");
		ext.add("signed", "N");
		ext.add("tradeVoucherNos", "101156378589524053613");
		ext.add("COMPANY_OR_PERSONAL", "C");
		ext.add("DBCR", "DC");
		ext.add("ACCESS_CHANNEL", "WEB");
		ext.add("smsSender", "PLATFORM");
		ext.add("GATE_ORDER_NO", "301156378589525153614");
		ext.add("paymentOrderNo", "301156378589525153614");
		ext.add("payeeId", "innerMember");
		ext.add("partnerId", "20000126506");
		ext.add("clientId", "payment");
		ext.add("PAGE_URL", "http://192.168.79.128:8087/api/yiran/channelpay/notify/resultNotify");
		request.setExtension(ext);
		PayFundResult result = fundRequestService.apply(request);
		
		System.out.println(JSON.toJSONString(result));
		
	}

}
