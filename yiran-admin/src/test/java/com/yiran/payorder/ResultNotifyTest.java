package com.yiran.payorder;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.netfinworks.common.util.money.Money;
import com.yiran.base.BaseJunit;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.ReturnInfo;
import com.yiran.payorder.enums.InstOrderGlideStatus;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.facade.IResultNotifyFacade;
import com.yiran.payorder.notify.OrderResult;

public class ResultNotifyTest  extends BaseJunit{
	
	@Autowired
	private IResultNotifyFacade resultNotifyFacade;
	
	@Test
    public void notifyCMF() {
        //1. 发请求到CMF, 模拟网银发送请求
        ReturnInfo result = resultNotifyFacade.notify(getNotifyRequest("DBLLPAY20190818004901002046"));
        System.out.println("结果返回：" + JSON.toJSONString(result));

    }

    @SuppressWarnings("deprecation")
    protected OrderResult getNotifyRequest(String instOrderNo) {
        OrderResult request = new OrderResult();
        PayInstOrderResult instOrderResult = new PayInstOrderResult();
        instOrderResult.setRealCurrency(CurrencyType.CNY);
        Money amount = new Money();
        amount.setAmount(new BigDecimal("1000"));
        instOrderResult.setRealAmount(amount);
        instOrderResult.setInstOrderNo(instOrderNo);
        instOrderResult.setStatus(InstOrderResultStatus.SUCCESSFUL);
        instOrderResult.setProcessStatus(InstOrderProcessStatus.SUCCESS);
        instOrderResult.setGlideStatus(InstOrderGlideStatus.NONEED);
        request.setOrderResult(instOrderResult);
        return request;
    }

}
