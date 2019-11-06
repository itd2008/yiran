package com.yiran.pay.sdk.service.impl;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.yiran.pay.sdk.config.AlipayConfig;
import com.yiran.pay.sdk.config.SignType;
import com.yiran.pay.sdk.model.OrderQueryRequest;
import com.yiran.pay.sdk.model.OrderQueryResponse;
import com.yiran.pay.sdk.model.OrderRefundQueryRequest;
import com.yiran.pay.sdk.model.OrderRefundQueryResponse;
import com.yiran.pay.sdk.model.PayFundRequest;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.pay.sdk.model.RePayRequest;
import com.yiran.pay.sdk.model.RePayResponse;
import com.yiran.pay.sdk.service.BestPayService;
/**
 * 支付宝支付 扫码支付
 * @author pandaa
 *
 */
public class AlipaySweepCodeService extends AbstractComponent implements BestPayService{
	private AlipayConfig alipayConfig;
    private AlipaySignature signature;
    
    public AlipaySweepCodeService(AlipayConfig alipayConfig, AlipaySignature signature) {
        Objects.requireNonNull(alipayConfig, "alipayConfig is null.");
        this.alipayConfig = alipayConfig;
        Objects.requireNonNull(signature, "signature is null.");
        this.signature = signature;
    }
	@Override
	public PayFundResponse pay(PayFundRequest request) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public OrderQueryResponse query(OrderQueryRequest request) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public PayFundResponse syncNotify(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public PayFundResponse asyncNotify(String notifyData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RePayResponse refund(RePayRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderRefundQueryResponse refundQuery(OrderRefundQueryRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
