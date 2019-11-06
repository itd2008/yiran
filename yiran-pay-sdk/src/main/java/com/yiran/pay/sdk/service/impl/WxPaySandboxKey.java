package com.yiran.pay.sdk.service.impl;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiran.pay.sdk.constants.WxPayConstants;
import com.yiran.pay.sdk.model.wxpay.WxPayApi;
import com.yiran.pay.sdk.model.wxpay.response.WxPaySandboxKeyResponse;
import com.yiran.pay.sdk.utils.JsonUtil;
import com.yiran.pay.sdk.utils.RandomUtil;
import com.yiran.pay.sdk.utils.XmlUtil;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class WxPaySandboxKey {
	private static final Logger logger = LoggerFactory.getLogger(WxPaySandboxKey.class);
    public void get(String mchId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WxPayConstants.WXPAY_GATEWAY)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        SandboxParam sandboxParam = new SandboxParam();
        sandboxParam.setMchId(mchId);
        sandboxParam.setNonceStr(RandomUtil.getRandomStr());
        sandboxParam.setSign(WxPaySignature.sign(sandboxParam.buildMap(), ""));

        String xml = XmlUtil.toString(sandboxParam);
        RequestBody body = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Call<WxPaySandboxKeyResponse> call = retrofit.create(WxPayApi.class).getsignkey(body);
        Response<WxPaySandboxKeyResponse> retrofitResponse  = null;
        try{
            retrofitResponse = call.execute();
        }catch (IOException e) {
            e.printStackTrace();
        }
        if (!retrofitResponse.isSuccessful()) {
            throw new RuntimeException("【微信统一支付】发起支付，网络异常，" + retrofitResponse);
        }
        Object response = retrofitResponse.body();
        logger.info("【获取微信沙箱密钥】response={}", JsonUtil.toJson(response));
    }


    @Root(name = "xml")
    static class SandboxParam {

        @Element(name = "mch_id")
        private String mchId;

        @Element(name = "nonce_str")
        private String nonceStr;

        @Element(name = "sign")
        private String sign;

        public Map<String, String> buildMap() {
            Map<String, String> map = new HashMap<>();
            map.put("mch_id", this.mchId);
            map.put("nonce_str", this.nonceStr);
            return map;
        }

		public String getMchId() {
			return mchId;
		}

		public void setMchId(String mchId) {
			this.mchId = mchId;
		}

		public String getNonceStr() {
			return nonceStr;
		}

		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}
        
        
    }
}
