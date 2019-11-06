package com.yiran.pay.sdk.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.yiran.pay.sdk.config.AlipayConfig;
import com.yiran.pay.sdk.config.SignType;
import com.yiran.pay.sdk.model.AlipayBizParam;
import com.yiran.pay.sdk.model.OrderQueryRequest;
import com.yiran.pay.sdk.model.OrderQueryResponse;
import com.yiran.pay.sdk.model.OrderRefundQueryRequest;
import com.yiran.pay.sdk.model.OrderRefundQueryResponse;
import com.yiran.pay.sdk.model.PayFundRequest;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.pay.sdk.model.RePayRequest;
import com.yiran.pay.sdk.model.RePayResponse;
import com.yiran.pay.sdk.service.BestPayService;
import com.yiran.pay.sdk.utils.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付宝app支付业务参数
 * 详见 https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.Vvthbm&treeId=204&articleId=105465&docType=1
 */
class AlipayAppServiceImpl extends AbstractComponent implements BestPayService {

    private AlipayConfig alipayConfig;
    private AlipaySignature signature;

    public AlipayAppServiceImpl(AlipayConfig alipayConfig, AlipaySignature signature) {
        Objects.requireNonNull(alipayConfig, "alipayConfig is null.");
        this.alipayConfig = alipayConfig;
        Objects.requireNonNull(signature, "signature is null.");
        this.signature = signature;
    }

    @Override
    public PayFundResponse pay(PayFundRequest request) {
        this.logger.info("【支付宝App端支付】request={}", JsonUtil.toJson(request));

        /* 1. 封装参数 */
        SortedMap<String, String> commonParamMap = new TreeMap<>();
        commonParamMap.put("app_id", this.alipayConfig.getAppId());
        commonParamMap.put("method", "alipay.trade.app.pay");
        commonParamMap.put("format", "JSON");
        commonParamMap.put("charset", this.alipayConfig.getInputCharset());
        commonParamMap.put("sign_type", this.alipayConfig.getSignType().name());
        commonParamMap.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        commonParamMap.put("version", "1.0");
        commonParamMap.put("notify_url", this.alipayConfig.getNotifyUrl());
        commonParamMap.put("biz_content", JsonUtil.toJson(this.buildParam(request)));

        /* 2. 签名 */
        String sign = this.signature.sign(commonParamMap);
        String encodedSign;
        try {
            encodedSign = URLEncoder.encode(sign, this.alipayConfig.getInputCharset());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("illegal encoding charset.", e);
        }

        /* 3. 返回的结果 */
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : commonParamMap.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (StringUtils.isBlank(k) || k.equals("sign") || StringUtils.isBlank(v)) {
                continue;
            }

            String encodedV;
            try {
                encodedV = URLEncoder.encode(v, this.alipayConfig.getInputCharset());
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("illegal encoding charset.", e);
            }
            sb.append(k).append("=").append(encodedV).append("&");
        }
        sb.append("sign=").append(encodedSign);

        /* 4. 返回签名结果 */
        PayFundResponse response = new PayFundResponse();
        response.setPrePayParams(sb.toString());
        return response;
    }

    @Override
    public boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign) {
        return this.signature.verify(toBeVerifiedParamMap, signType, sign);
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
        alipayBizParam.setProductCode("QUICK_MSECURITY_PAY");
        alipayBizParam.setBody("abc");
        return alipayBizParam;
    }

	@Override
	public PayFundResponse syncNotify(HttpServletRequest request) {
		//TODO 待完善
		return null;
	}

	@Override
	public PayFundResponse asyncNotify(String notifyData) {
		//TODO 待完善
		return null;
	}

	@Override
	public RePayResponse refund(RePayRequest request) {
		//TODO 待完善
		return null;
	}

	@Override
	public OrderQueryResponse query(OrderQueryRequest request) {
		//TODO 待完善
		return null;
	}

	@Override
	public OrderRefundQueryResponse refundQuery(OrderRefundQueryRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
