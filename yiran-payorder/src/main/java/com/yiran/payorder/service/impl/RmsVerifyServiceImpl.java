package com.yiran.payorder.service.impl;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.payorder.converter.RmsRequestConverter;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.Result;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.enums.OrderRiskStatus;
import com.yiran.payorder.rms.RmsRequest;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.IRmsVerifyService;


/**
 *
 * <p>风险审核系统时限</p>
 */
@Service
public class RmsVerifyServiceImpl implements IRmsVerifyService {

    private static final Logger   logger      = LoggerFactory.getLogger(RmsVerifyServiceImpl.class);

    public static final String[]  HTTP_PREFIX = { "http://", "https://" };

    @Autowired
	private IPayInstOrderService payInstOrderService;

    @Override
    public OrderRiskStatus verify(ChannelPayOrder channelPayOrder,PayInstOrder instOrder,PayInstOrderResult result) {
        if (!BizType.FUNDIN.equals(channelPayOrder.getBizType())) {
            return OrderRiskStatus.PASS;
        }
        RmsRequest request = RmsRequestConverter.convert(channelPayOrder, instOrder, result);
        OrderRiskStatus riskStatus = OrderRiskStatus.IN_PROCESS;
        String resultCode = "";
        try {
            
        	//TODO:风控都通过
            resultCode = "000";

            if (StringUtil.isEmpty(resultCode) || Result.RESULT_CODE_EXCEPTION.equals(resultCode)) {
                throw new RuntimeException("instOrderNo:" + instOrder.getInstOrderNo() + "审核异常");
            }

            if (Result.RESULT_CODE_PASS.equals(resultCode)
                || Result.RESULT_CODE_WARN.equals(resultCode)
                || Result.RESULT_CODE_MONITOR.equals(resultCode)) {
                riskStatus = OrderRiskStatus.PASS;
            }

            if (Result.RESULT_CODE_FAILED.equals(resultCode)) {
                riskStatus = OrderRiskStatus.REJECT;
            }

            if (Result.RESULT_CODE_MANUAL.equals(resultCode)) {
                riskStatus = OrderRiskStatus.IN_PROCESS;
            }

        } catch (Exception e) {
            logger.error("[风险订单]instOrderNo:" + instOrder.getInstOrderNo() + "发送异常", e);
            if (!InstOrderResultStatus.RISK.equals(result.getStatus())) {
                if (logger.isInfoEnabled()) {
                    logger.info("[风险订单]instOrderNo:" + instOrder.getInstOrderNo() + "通知失败,当审核通过处理");
                }
                riskStatus = OrderRiskStatus.PASS;
            } else {
                riskStatus = OrderRiskStatus.FAILED_SEND;
            }
        } finally {
            instOrder.setRiskStatus(riskStatus);
            payInstOrderService.updateRiskStatus(riskStatus, instOrder.getInstOrderId());
        }
        // 新老PE兼容
        if (Result.RESULT_CODE_MANUAL.equals(resultCode)) {
            riskStatus = OrderRiskStatus.PASS;
        }
        return riskStatus;
    }

    /*
     * (non-Javadoc)
     * @see com.netfinworks.cmf.fss.core.domainservice.process.RmsVerifyService#verify(com.netfinworks.cmf.common.core.domain.channelPayOrder)
     */
    @Override
    public OrderRiskStatus verify(ChannelPayOrder channelPayOrder) {
        if (!BizType.FUNDOUT.equals(channelPayOrder.getBizType())) {
            return OrderRiskStatus.PASS;
        }
        RmsRequest request = RmsRequestConverter.convert(channelPayOrder);
        OrderRiskStatus riskStatus = OrderRiskStatus.IN_PROCESS;
        try {
            if (logger.isInfoEnabled()) {
                logger.info("风险订单[" + channelPayOrder.getPaymentSeqNo() + "]请求信息：" + request.toString());
            }

            //TODO:风控都通过
            String resultCode = "000";

            if (StringUtil.isEmpty(resultCode) || Result.RESULT_CODE_EXCEPTION.equals(resultCode)) {
                throw new RuntimeException("instOrderNo:" + channelPayOrder.getPaymentSeqNo() + "审核异常");
            }

            if (Result.RESULT_CODE_PASS.equals(resultCode)
                || Result.RESULT_CODE_WARN.equals(resultCode)
                || Result.RESULT_CODE_MONITOR.equals(resultCode)) {
                riskStatus = OrderRiskStatus.PASS;
            }

            if (Result.RESULT_CODE_REJECT.equals(resultCode)) {
                riskStatus = OrderRiskStatus.REJECT;
            }

            if (Result.RESULT_CODE_MANUAL.equals(resultCode)) {
                riskStatus = OrderRiskStatus.IN_PROCESS;
            }

        } catch (Exception e) {
            logger.warn("[风险订单]instOrderNo:" + channelPayOrder.getPaymentSeqNo() + "发送异常,当审核通过处理", e);
            riskStatus = OrderRiskStatus.PASS;
        }
        return riskStatus;
    }

    @Override
    public boolean checkChannelResult(PayInstOrder instOrder, PayInstOrderResult result) {
        //不需要校验风控审核的和结果不为成功的不需要校验风险
        if ( !InstOrderResultStatus.SUCCESSFUL.equals(result.getStatus())) {
            return true;
        }
        //check the user's IP and domain
        boolean checkSuccess = true;
        String userDomain = populateUserDomain(result.getExtension().get(
            ExtensionKey.WEIBO_PAY_USER_PAY_DOMAIN.key));
        String logCheckPrefix = "";
        if (StringUtils.isEmpty(userDomain)) {
            logCheckPrefix = "ip";
            checkSuccess = checkUserIP(result, instOrder); //check IP
        }
        if (logger.isInfoEnabled()) {
            logger.info("[RMS_Verify]机构订单(" + instOrder.getInstOrderNo() + ")判定" + logCheckPrefix
                        + "," + checkSuccess);
        }

        return checkSuccess;
    }

    /**
     * 检查用户IP是否匹配
     *
     * @param request
     * @param instOrder
     * @return
     */
    private boolean checkUserIP(PayInstOrderResult result, PayInstOrder instOrder) {
        String notifyUserIP = result.getExtension().get(ExtensionKey.WEIBO_PAY_USER_PAY_IP.key);
        String dbUserIP = instOrder.getExtension().get(ExtensionKey.ORDER_IP.key);

        logger.info("[RMS_Verify]风控IP审核: instOrderNo=" + instOrder.getInstOrderNo()
                    + ",notifyUserIP=" + notifyUserIP + ", dbUserIP=" + dbUserIP
                    + ", resultExtention=" + result.getExtension());
        if (StringUtils.isEmpty(dbUserIP)) {
            return StringUtils.isEmpty(notifyUserIP);
        } else {
            return dbUserIP.equals(notifyUserIP);
        }
    }

    /**
     * 解析用户domain.
     *
     * @param domainStr
     * @return
     */
    private String populateUserDomain(String domainStr) {
        if (StringUtils.isEmpty(domainStr)) {
            return domainStr;
        }

        domainStr = domainStr.toLowerCase();
        for (String prefix : HTTP_PREFIX) {
            if (domainStr.startsWith(prefix)) {
                domainStr = domainStr.substring(prefix.length());
                return domainStr.split("/|\\\\")[0];
            }
        }
        return domainStr;
    }

}
