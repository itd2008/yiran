package com.yiran.payorder.service;

import com.yiran.payorder.domain.ChannelResult;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.InstOrderResultRequest;
import com.yiran.payorder.request.PayOrderRequest;
import com.yiran.payorder.response.InstOrderResultResponse;
import com.yiran.payorder.response.PayFundResult;

public interface IFundRequestService {
	
	/**
     * 入款,出款申请
     * @param request
     * @param environment
     * @return
     */
	public PayFundResult apply(PayOrderRequest request);
	
   /**
    * 退款申请
    * @param request 必须明确原入款PE订单号,充退金额，如果没有指定充退金额以原入款金额作为充退金额
    * @param environment 
    * @return
    */
   	public PayFundResult refund(PayOrderRequest request);
   /**
    * 根据退款订单查询退款商户返回的订单信息
    * @param request
    * @return
    */
   	public InstOrderResultResponse findInstOrderResult(InstOrderResultRequest request);

   	/**
   	 * 下载对文件
   	 * @param channelRequest
   	 * @return
   	 */
   	public ChannelResult download(ChannelRequest channelRequest);
   	
   	
}
