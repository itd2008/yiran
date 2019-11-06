package com.yiran.payorder.converter;

import java.util.HashMap;
import java.util.Map;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.enums.RiskFlag;
import com.yiran.payorder.rms.RmsRequest;

/**
 * 
 * <p>
 * rms请求转换器
 * </p>
 * 
 */
public class RmsRequestConverter {

    /**
     * 组装风控请求对象
     * 
     * @param channelPayOrder
     * @param instOrder
     * @param instOrderResult
     * @return
     */
    public static RmsRequest convert(ChannelPayOrder channelPayOrder, PayInstOrder instOrder,
                                     PayInstOrderResult instOrderResult) {
        RmsRequest request = new RmsRequest();

        if (null != instOrderResult) {
            // R_C:通知风控系统时，需要改成统一支付编码
            request.setBankReturnCode(instOrderResult.getInstResultCode());
            request.setBankReturnMsg("");
            request.setRealPayIp(instOrderResult.getExtension().get(
                ExtensionKey.WEIBO_PAY_USER_PAY_IP.key));
            request.setPayReturnIP(instOrderResult.getExtension().get(
                ExtensionKey.WEIBO_PAY_USER_PAY_IP.key));
            request.setRealPayWebdomain(instOrderResult.getExtension().get(
                ExtensionKey.WEIBO_PAY_USER_PAY_DOMAIN.key));
            request.setBankSerialNo(instOrderResult.getInstSeqNo());
            request.setMemo(instOrderResult.getMemo());
            request.setPayTime(DateUtil.getNewFormatDateString(instOrderResult.getGmtCreate()));
            if (InstOrderResultStatus.SUCCESSFUL.equals(instOrderResult.getStatus())) {
                request.setPaymentStatus("0");
            } else {
                request.setPaymentStatus("1");
            }
            if (RiskFlag.BANK_CHECK.equals(instOrderResult.getRiskFlag())) {
                request.setRiskOrderFlag(1);
            } else {
                request.setRiskOrderFlag(0);
            }

        }
        request.setChannelCode(instOrder.getFundChannelCode());
        request.setCheckPoint("CP126");
        request.setCurrency("CNY");
        request.setCustomerOrderNo(channelPayOrder.getExtension().get(ExtensionKey.GATE_ORDER_NO.key));
        request.setInstOrderNo(instOrder.getInstOrderNo());
        request.setMasOrderNo(channelPayOrder.getExtension().get(ExtensionKey.MAS_ORDER_NO.key));
        request.setPayeeId(channelPayOrder.getExtension().get(ExtensionKey.PAYEE_ID.key));
        request.setTradeVoucherNos(channelPayOrder.getExtension().get(ExtensionKey.TRADE_VOUCHER_NOS.key));
        // 兼容使用,后续删掉
        request.setMemberId(channelPayOrder.getMemberId());
        request.setPayMode(channelPayOrder.getPayMode().getCode());

        request.setOrderIp(channelPayOrder.getExtension().get(ExtensionKey.ORDER_IP.key));
        request.setOrderTime(DateUtil.getNewFormatDateString(instOrder.getGmtCreate()));
        request.setOrderType("");
        request.setPayableAmount(instOrder.getAmount().getAmount().toString());

        String payChannel = instOrder.getExtension().get(ExtensionKey.PAY_CHANNEL.key);
        if (StringUtil.isEmpty(payChannel)) {
            request.setPayChannel("");
        } else {
            request.setPayChannel(payChannel);
        }

        request.setPaymentCode(channelPayOrder.getPaymentCode());
        request.setProductCode(channelPayOrder.getProductCode());

        request.setPaymentSeqNo(channelPayOrder.getPaymentSeqNo());
        request.setPaymentOrderNo(channelPayOrder.getExtension().get(ExtensionKey.PAYMENT_ORDER_NO.key));

        request.setPayOrg(channelPayOrder.getInstCode());

        Map<String, String> map = new HashMap<String, String>();
        map.put(ExtensionKey.CLIENT_ID.key, channelPayOrder.getExtension().get(ExtensionKey.CLIENT_ID.key));
        request.setPublicMapExpand(PropertyConverter.convertFromMap(map));

        request.setVersion(1);

        request.setRiskMsg(instOrder.getExtension().get(ExtensionKey.riskMsg.key));
        request
            .setRiskResult(instOrder.getExtension().get(ExtensionKey.RMS_RISK_VERIFY_RESULT.key));
        request.setRiskValue(instOrder.getExtension().get(ExtensionKey.riskValue.key));

        request.setPaymentSeqNo(channelPayOrder.getPaymentSeqNo());
        request.setBizType(channelPayOrder.getBizType().getCode());

        return request;
    }

    /**
     * 转换cmf订单为风险订单请求
     * 
     * @param channelPayOrder
     * @return
     */
    public static RmsRequest convert(ChannelPayOrder channelPayOrder) {
        RmsRequest request = new RmsRequest();

        request.setRiskOrderFlag(0);
        request.setCheckPoint("CP125");
        request.setCurrency("CNY");
        request.setMasOrderNo(channelPayOrder.getExtension().get(ExtensionKey.GATE_ORDER_NO.key));
        request.setPaySeqNo(channelPayOrder.getPaySeqNo().toString());
        request.setMasOrderNo(channelPayOrder.getExtension().get(ExtensionKey.MAS_ORDER_NO.key));
        request.setPayeeId(channelPayOrder.getExtension().get(ExtensionKey.PAYEE_ID.key));
        // 兼容使用,后续删掉
        request.setMemberId(channelPayOrder.getMemberId());

        request.setOrderIp(channelPayOrder.getExtension().get(ExtensionKey.ORDER_IP.key));
        request.setOrderTime(DateUtil.getNewFormatDateString(channelPayOrder.getGmtCreate()));
        request.setOrderType("");
        request.setPayableAmount(channelPayOrder.getAmount().getAmount().toString());
        String payChannel = channelPayOrder.getExtension().get(ExtensionKey.PAY_CHANNEL.key);
        if (StringUtil.isEmpty(payChannel)) {
            request.setPayChannel("");
        } else {
            request.setPayChannel(payChannel);
        }
        request.setPayerMemberId(channelPayOrder.getExtension().get(ExtensionKey.PT_ID.key));
        request.setPaymentCode(channelPayOrder.getPaymentCode());
        request.setProductCode(channelPayOrder.getProductCode());
        request.setPaymentSeqNo(channelPayOrder.getPaymentSeqNo());
        request.setPayMode(channelPayOrder.getPayMode().getCode());
        request.setPayOrg(channelPayOrder.getInstCode());

        request.setRiskMsg(channelPayOrder.getExtension().get(ExtensionKey.riskMsg.key));
        request.setRiskResult(channelPayOrder.getExtension().get(ExtensionKey.RMS_RISK_VERIFY_RESULT.key));
        request.setRiskValue(channelPayOrder.getExtension().get(ExtensionKey.riskValue.key));
        request.setTradeVoucherNos(channelPayOrder.getExtension().get(ExtensionKey.TRADE_VOUCHER_NOS.key));

        request.setPublicMapExpand("");
        request.setPaymentOrderNo(channelPayOrder.getExtension().get(ExtensionKey.PAYMENT_ORDER_NO.key));

        request.setVersion(1);

        request.setBizType(channelPayOrder.getBizType().getCode());

        if (BizType.FUNDOUT.equals(channelPayOrder.getBizType())) {
            request.setBankAccountName(channelPayOrder.getExtension().get(ExtensionKey.ACCOUNT_NAME.key));
            request.setBankAccountNumber(channelPayOrder.getExtension().get(ExtensionKey.CARD_NO.key));
            request.setBankArea(channelPayOrder.getExtension().get(ExtensionKey.AREA_CODE.key));
            request.setBankCode(channelPayOrder.getExtension().get(ExtensionKey.BANK_CODE.key));
            request.setBankName(channelPayOrder.getExtension().get(ExtensionKey.BANK_NAME.key));
            request.setBankLineNo(channelPayOrder.getExtension().get(ExtensionKey.BANK_LINE_NO.key));
        }
        return request;
    }
}
