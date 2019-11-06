package com.yiran.pay.sdk.service.impl;


import javax.servlet.http.HttpServletRequest;

import com.yiran.pay.sdk.config.AliDirectPayConfig;
import com.yiran.pay.sdk.config.AlipayConfig;
import com.yiran.pay.sdk.config.SignType;
import com.yiran.pay.sdk.config.WxPayH5Config;
import com.yiran.pay.sdk.enums.BestPayResultEnum;
import com.yiran.pay.sdk.enums.BestPayTypeEnum;
import com.yiran.pay.sdk.exception.BestPayException;
import com.yiran.pay.sdk.model.OrderQueryRequest;
import com.yiran.pay.sdk.model.OrderQueryResponse;
import com.yiran.pay.sdk.model.OrderRefundQueryRequest;
import com.yiran.pay.sdk.model.OrderRefundQueryResponse;
import com.yiran.pay.sdk.model.PayFundRequest;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.pay.sdk.model.RePayRequest;
import com.yiran.pay.sdk.model.RePayResponse;
import com.yiran.pay.sdk.service.BestPayService;

import java.util.Map;

public class BestPayServiceImpl extends AbstractComponent implements BestPayService {

    private WxPayH5Config wxPayH5Config;
    
    /**
     * 支付宝即时到账支付配置信息
     */
    private AliDirectPayConfig aliDirectPayConfig;
    /**
     * 支付宝配置信息(非即时到账接口)
     */
    private AlipayConfig alipayConfig;

    
    public void setAliDirectPayConfig(AliDirectPayConfig aliDirectPayConfig) {
		this.aliDirectPayConfig = aliDirectPayConfig;
	}

	public void setAlipayConfig(AlipayConfig alipayConfig) {
		this.alipayConfig = alipayConfig;
	}
    public void setWxPayH5Config(WxPayH5Config wxPayH5Config) {
        this.wxPayH5Config = wxPayH5Config;
    }

    @Override
    public PayFundResponse pay(PayFundRequest request) {
    	 //微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        
        switch (request.getPayTypeEnum()) {
		case ALIPAY_APP:
			AlipayAppServiceImpl alipayAppService = new AlipayAppServiceImpl(this.alipayConfig, new AlipaySignature(this.alipayConfig));
			return alipayAppService.pay(request);
		case ALIPAY_PC:
			//AlipayPCServiceImpl alipayPCService = new AlipayPCServiceImpl(this.aliDirectPayConfig,new AlipayPCSignature(this.aliDirectPayConfig));
			AlipayPCServiceImpl alipayPCService = new AlipayPCServiceImpl(this.alipayConfig);
			return alipayPCService.pay(request);
		case ALIPAY_WAP:
			AlipayWapServiceImpl alipayWapService = new AlipayWapServiceImpl(this.alipayConfig,new AlipaySignature(this.alipayConfig));
			return alipayWapService.pay(request);
		case ALIPAY_SWEEP_CODE:
			AlipaySweepCodeService alipaySweepCodeService = new AlipaySweepCodeService(this.alipayConfig,new AlipaySignature(this.alipayConfig));
			return alipaySweepCodeService.pay(request);
		case WXPAY_H5:
			wxPayService.setWxPayH5Config(this.wxPayH5Config);
			return wxPayService.pay(request);
		case WXPAY_MWEB:
			wxPayService.setWxPayH5Config(this.wxPayH5Config);
			return wxPayService.pay(request);
		case WXPAY_NATIVE:
			wxPayService.setWxPayH5Config(this.wxPayH5Config);
			return wxPayService.pay(request);
		}
        
		return null;
    }

    /**
     * 同步返回
     *
     * @param request
     * @return
     */
    public PayFundResponse syncNotify(HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign) {
        return false;
    }

    /**
     * 异步回调
     *
     * @return
     */
    public PayFundResponse asyncNotify(String notifyData) {

        //微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        wxPayService.setWxPayH5Config(this.wxPayH5Config);

        return wxPayService.asyncNotify(notifyData);
    }

    /**
     * 判断是什么支付类型(从同步回调中获取参数)
     *
     * @param request
     * @return
     */
    private BestPayTypeEnum payType(HttpServletRequest request) {
        //先判断是微信还是支付宝 是否是xml
        //支付宝同步还是异步
        if (request.getParameter("notify_type") == null) {
            //支付宝同步
            if (request.getParameter("exterface") != null && request.getParameter("exterface").equals("create_direct_pay_by_user")) {
                return BestPayTypeEnum.ALIPAY_PC;
            }
            if (request.getParameter("method") != null && request.getParameter("method").equals("alipay.trade.wap.pay.return")) {
                return BestPayTypeEnum.ALIPAY_WAP;
            }
        } else {
            //支付宝异步(发起支付时使用这个参数标识支付方式)
            String payType = request.getParameter("passback_params");
            return BestPayTypeEnum.getByCode(payType);
        }

        throw new BestPayException(BestPayResultEnum.PAY_TYPE_ERROR);
    }

    @Override
    public RePayResponse refund(RePayRequest request) {
        //微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        wxPayService.setWxPayH5Config(this.wxPayH5Config);
        return wxPayService.refund(request);
    }

	@Override
	public OrderQueryResponse query(OrderQueryRequest request) {
		//微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        wxPayService.setWxPayH5Config(this.wxPayH5Config);
        return wxPayService.query(request);
	}

	@Override
	public OrderRefundQueryResponse refundQuery(OrderRefundQueryRequest request) {
		//微信h5支付
        WxPayServiceImpl wxPayService = new WxPayServiceImpl();
        wxPayService.setWxPayH5Config(this.wxPayH5Config);
		return wxPayService.refundQuery(request);
	}
}