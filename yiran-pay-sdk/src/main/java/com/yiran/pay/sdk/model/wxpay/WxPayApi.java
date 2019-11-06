package com.yiran.pay.sdk.model.wxpay;

import com.yiran.pay.sdk.model.wxpay.response.WxOrderQueryResponse;
import com.yiran.pay.sdk.model.wxpay.response.WxPaySandboxKeyResponse;
import com.yiran.pay.sdk.model.wxpay.response.WxPaySyncResponse;
import com.yiran.pay.sdk.model.wxpay.response.WxRefundResponse;
import com.yiran.pay.sdk.model.wxpay.response.WxRefundqueryResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 微信支付api
 */
public interface WxPayApi {

    /**
     * 统一下单
     * @param body
     * @return
     */
    @POST("/pay/unifiedorder")
    Call<WxPaySyncResponse> unifiedorder(@Body RequestBody body);

    /**
     * 申请退款
     * @param body
     * @return
     */
    @POST("/secapi/pay/refund")
    Call<WxRefundResponse> refund(@Body RequestBody body);

    /**
     * 申请沙箱密钥
     * @param body
     * @return
     */
    @POST("/sandboxnew/pay/getsignkey")
    Call<WxPaySandboxKeyResponse> getsignkey(@Body RequestBody body);

    /**
     * 订单查询
     * @param body
     * @return
     */
    @POST("/pay/orderquery")
    Call<WxOrderQueryResponse> orderquery(@Body RequestBody body);
    
    /**
     * 订单查询
     * @param body
     * @return
     */
    @POST("/pay/refundquery")
    Call<WxRefundqueryResponse> refundquery(@Body RequestBody body);
}
