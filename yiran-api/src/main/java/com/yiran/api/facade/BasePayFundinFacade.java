package com.yiran.api.facade;

import java.util.Date;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.request.ChannelFundRequest;

public class BasePayFundinFacade {

	/**
     * 返回支付结果对象
     * 
     * @param instOrderNo
     * @param amount
     * @param apiResultCode
     * @param apiResultMessage
     * @return
     */
    protected ChannelFundResult buildFundinResult(String instOrderNo, String amount,
                                                  String apiResultCode, String apiResultMessage) {
        ChannelFundResult result = new ChannelFundResult();
        result.setApiType(FundChannelApiType.DEBIT);
        result.setInstOrderNo(instOrderNo);
        result.setInstReturnOrderNo(StringUtil.EMPTY_STRING);
        result.setApiResultCode(apiResultCode);
        result.setApiResultMessage(apiResultMessage);
        result.setRealAmount(new Money(amount));
        result.setSuccess(true);
        return result;
    }

   /**
    * 
    * @param instOrderNo
    * @param sAmount
    * @param apiResultCode
    * @param apiResultMessage
    * @param instReturnOrderNo
    * @return
    */
    protected ChannelFundResult buildFundinResult(String instOrderNo, String sAmount,
                                                  String apiResultCode, String apiResultMessage,
                                                  String instReturnOrderNo) {
        ChannelFundResult result = new ChannelFundResult();
        result.setApiType(FundChannelApiType.DEBIT);
        result.setInstOrderNo(instOrderNo);
        result.setInstReturnOrderNo(instReturnOrderNo);
        result.setApiResultCode(apiResultCode);
        result.setApiResultMessage(apiResultMessage);
        result.setRealAmount(new Money(sAmount));
        result.setSuccess(true);
        return result;
    }

    /**
     * 返回支付异常时的响应
     * 
     * @param channelFundRequest
     * @param resultMessage
     * @param resultCode
     * @param apiResultCode
     * @param apiResultMessage
     * @return
     */
    protected ChannelFundResult builFalidFundinResponse(ChannelFundRequest channelFundRequest, String resultMessage,
                                                        String resultCode, String apiResultCode,
                                                        String apiResultMessage) {

        ChannelFundResult instOrderResult = new ChannelFundResult();
        instOrderResult.setApiType(channelFundRequest.getApiType());
        instOrderResult.setInstOrderNo(channelFundRequest.getInstOrderNo());
        instOrderResult.setApiResultCode(apiResultCode);
        instOrderResult.setApiResultMessage(apiResultMessage);
        instOrderResult.setResultCode(resultCode);
        instOrderResult.setResultMessage(resultMessage);
        instOrderResult.setProcessTime(new Date());
        instOrderResult.setRealAmount(channelFundRequest.getAmount());
        instOrderResult.setSuccess(false);
        return instOrderResult;
    }
}
