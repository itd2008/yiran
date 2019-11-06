package com.yiran.pay.sdk.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.yiran.pay.sdk.config.AlipayConfig;
import com.yiran.pay.sdk.config.SignType;
import com.yiran.pay.sdk.model.AlipayBizParam;
import com.yiran.pay.sdk.model.OrderQueryRequest;
import com.yiran.pay.sdk.model.OrderQueryResponse;
import com.yiran.pay.sdk.model.OrderRefundQueryRequest;
import com.yiran.pay.sdk.model.OrderRefundQueryResponse;
import com.yiran.pay.sdk.model.PayFundRequest;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.pay.sdk.model.RePayResponse;
import com.yiran.pay.sdk.model.RePayRequest;
import com.yiran.pay.sdk.service.BestPayService;
import com.yiran.pay.sdk.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 支付宝Wap端支付
 * https://doc.open.alipay.com/doc2/detail.htm?treeId=203&articleId=105463&docType=1
 */
class AlipayWapServiceImpl extends AbstractComponent implements BestPayService {

    private AlipayConfig alipayConfig;
    private AlipaySignature signature;

    public AlipayWapServiceImpl(AlipayConfig alipayConfig, AlipaySignature signature) {
        Objects.requireNonNull(alipayConfig, "alipayConfig is null.");
        this.alipayConfig = alipayConfig;
        Objects.requireNonNull(signature, "signature is null.");
        this.signature = signature;
    }

    @Override
    public PayFundResponse pay(PayFundRequest request) {
        this.logger.info("【支付宝Wap端支付】request={}", JsonUtil.toJson(request));

        /* 1. 封装参数 */
        SortedMap<String, String> commonParamMap = new TreeMap<>();
        commonParamMap.put("app_id", this.alipayConfig.getAppId());
        commonParamMap.put("method", "alipay.trade.wap.pay");
        commonParamMap.put("format", "JSON");
        commonParamMap.put("return_url", this.alipayConfig.getReturnUrl());
        commonParamMap.put("charset", this.alipayConfig.getInputCharset());
        commonParamMap.put("sign_type", this.alipayConfig.getSignType().name());
        commonParamMap.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        commonParamMap.put("version", "1.0");
        commonParamMap.put("notify_url", this.alipayConfig.getNotifyUrl());
        commonParamMap.put("biz_content", JsonUtil.toJson(this.buildParam(request).getBizParam()));

        /* 2. 签名 */
        String sign = this.signature.sign(commonParamMap);
        commonParamMap.put("sign", sign);

        /* 3. 构造支付url */
        String payUrl;
        try {
            payUrl = new URIBuilder("https://openapi.alipay.com/gateway.do").setParameters(
                    commonParamMap.entrySet().stream().filter(e -> {
                        String k = e.getKey();
                        String v = e.getValue();
                        return !(StringUtils.isBlank(k) || StringUtils.isBlank(v));
                    }).map(e -> {
                        String k = e.getKey();
                        String v = e.getValue();
                        return new BasicNameValuePair(k, v);
                    }).collect(Collectors.toList())).toString();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("illegal pay url.", e);
        }

        /* 4. 返回签名结果 */
        PayFundResponse response = new PayFundResponse();
        response.setPayUri(URI.create(payUrl));
        this.logger.info("【支付宝Wap端支付】response={}", JsonUtil.toJson(response));
        return response;
    }

    @Override
    public boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign) {
        return this.signature.verify(toBeVerifiedParamMap, signType, sign);
    }

    @Override
    public PayFundResponse asyncNotify(String notifyData) {
        return null;
    }

    @Override
    public PayFundResponse syncNotify(HttpServletRequest request) {
        return null;
    }

    /**
     * 构造支付宝需要的业务参数
     * @param request
     * @return
     */
    private AlipayBizParam buildParam(PayFundRequest request) {
        AlipayBizParam alipayBizParam = new AlipayBizParam();
        alipayBizParam.setSubject(request.getOrderName());
        alipayBizParam.setOutTradeNo(request.getOrderId());
        alipayBizParam.setTotalAmount(String.valueOf(request.getOrderAmount()));
        alipayBizParam.setProductCode("QUICK_WAP_PAY");
        return alipayBizParam;
    }

    @Override
    public RePayResponse refund(RePayRequest request) {
        return null;
    }

	@Override
	public OrderQueryResponse query(OrderQueryRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderRefundQueryResponse refundQuery(OrderRefundQueryRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
