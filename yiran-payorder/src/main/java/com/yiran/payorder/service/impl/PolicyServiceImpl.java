package com.yiran.payorder.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.enums.OrderRiskStatus;
import com.yiran.payorder.enums.PayOrderConfirmStatus;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IPolicyService;
import com.yiran.payorder.service.IRmsVerifyService;
import com.yiran.payorder.service.ISubmitInstitutionService;


/**
 * 
 * <p>提交订单审核流程</p>
 */
@Service
public class PolicyServiceImpl implements IPolicyService {

    private static final Logger      logger = LoggerFactory.getLogger(PolicyServiceImpl.class);
    @Autowired
	private IChannelPayOrderService channelPayOrderService;
    @Autowired
    private IRmsVerifyService         rmsVerifyService;
    
    /** 提交机构服务 */
    @Autowired
    private ISubmitInstitutionService submitInstitutionService;
    @Override
    public PayInstOrderResult apply(ChannelPayOrder channelPayOrder) {
    	channelPayOrder.setConfirmStatus(PayOrderConfirmStatus.AWAITING);
    	channelPayOrderService.updateConfirmStatusById(PayOrderConfirmStatus.AWAITING,
        		channelPayOrder.getPaySeqNo());
        OrderRiskStatus status = rmsVerifyService.verify(channelPayOrder);
		return applyResult(channelPayOrder, status, true);
		
    }

    @Override
    public PayInstOrderResult applyResult(ChannelPayOrder channelPayOrder, OrderRiskStatus status, boolean isSync){
        switch (status) {
            case IN_PROCESS:
            	channelPayOrder.setConfirmStatus(PayOrderConfirmStatus.SUBMIT);
            	channelPayOrder.setStatus(PayOrderStatus.AWAITING);
                break;
            case FAILED_SEND:
            	channelPayOrder.setConfirmStatus(PayOrderConfirmStatus.FAIL);
            	channelPayOrder.setStatus(PayOrderStatus.AWAITING);
                break;
            case PASS:
            	channelPayOrder.setConfirmStatus(PayOrderConfirmStatus.PASS);
            	channelPayOrder.setStatus(PayOrderStatus.IN_PROCESS);
                break;
            case REJECT:
            	channelPayOrder.setConfirmStatus(PayOrderConfirmStatus.REJECT);
            	channelPayOrder.setStatus(PayOrderStatus.CANCEL);
                break;
        }
        //更新订单状态
        boolean result = channelPayOrderService.updateCmfOrderStatus(channelPayOrder, PayOrderStatus.AWAITING);
        //若没有明确更新成功,说明其它操作已经执行,返回处理中
        if (!result) {
            return awaiting(channelPayOrder.getBizType(), channelPayOrder.getPaySeqNo(), channelPayOrder.getAmount());
        }
        
        PayInstOrderResult instOrderResult = null;
        
        try {
	        //后续流程操作
	        switch (channelPayOrder.getConfirmStatus()) {
	            case PASS:
	                return submitInstitutionService.submit(channelPayOrder, isSync);
	            case REJECT:
	                return definiteFail(channelPayOrder.getBizType(), "风控拦截", channelPayOrder.getPaySeqNo(),
	                    channelPayOrder.getAmount());
	            default:
	                return awaiting(channelPayOrder.getBizType(), channelPayOrder.getPaySeqNo(),
	                    channelPayOrder.getAmount());
	        }
        } catch (RouteChannelException e) {
			logger.error("[入款/出款]处理异常(paymentSeqNo=" + channelPayOrder.getPaymentSeqNo() + "):", e);
        	switch (channelPayOrder.getBizType()) {
			case FUNDIN:
				instOrderResult = fail(channelPayOrder);
				break;
			case REFUND:
				instOrderResult = awaiting(channelPayOrder);
				break;
			case FUNDOUT:
				instOrderResult = awaiting(channelPayOrder);
				break;
			default:
				instOrderResult = fail(channelPayOrder);
				break;
			}
		}
        return instOrderResult;
    }

    /**
     * 返回明确失败结果
     * @param bizType
     * @param memo
     * @param cmfSeqNo
     * @param amount
     * @return
     */
    private PayInstOrderResult definiteFail(BizType bizType, String memo, String paySeqNo, Money amount) {
        PayInstOrderResult result = new PayInstOrderResult();
        result.setMemo(memo);
        result.setOrderType(bizType);
        result.setRealAmount(amount);
        result.setGmtCreate(new Date());
        result.setInstOrderNo(paySeqNo);
        //明确失败
        result.setProcessStatus(InstOrderProcessStatus.SUCCESS);
        result.setStatus(InstOrderResultStatus.FAILURE);
        return result;
    }

    /**
     * 返回处理中结果
     * @param bizType
     * @param cmfSeqNo
     * @param amount
     * @return
     */
    private PayInstOrderResult awaiting(BizType bizType, String paySeqNo, Money amount) {
        PayInstOrderResult result = new PayInstOrderResult();
        result.setOrderType(bizType);
        result.setRealAmount(amount);
        result.setGmtCreate(new Date());
        result.setInstOrderNo(paySeqNo);
        //处理中
        result.setProcessStatus(InstOrderProcessStatus.AWAITING);
        result.setStatus(InstOrderResultStatus.AWAITING);

        result.setMemo("受理中，请等待.");
        return result;
    }

    /**
     * 路由失败置cmf失败逻辑
     * @param cmfOrder
     * @return
     */
    private PayInstOrderResult fail(ChannelPayOrder channelPayOrder) {
        //TODO 1. cmfOrder直接跃迁为失败
    	channelPayOrder.setStatus(PayOrderStatus.FAILURE);
    	channelPayOrderService.updateCmfOrderStatus(channelPayOrder, PayOrderStatus.IN_PROCESS);

        PayInstOrderResult result = new PayInstOrderResult();
        result.setRealAmount(channelPayOrder.getAmount());
        result.setOrderType(channelPayOrder.getBizType());

        result.setStatus(InstOrderResultStatus.FAILURE);
        result.setProcessStatus(InstOrderProcessStatus.SUCCESS);
        result.setMemo("路由失败,没有可用渠道");

        return result;
    }
    
    /**
     * 路由失败置cmf待处理逻辑
     * @param cmfOrder
     * @return
     */
    private PayInstOrderResult awaiting(ChannelPayOrder channelPayOrder) {
        //TODO 1. cmfOrder直接跃迁为失败
    	channelPayOrder.setStatus(PayOrderStatus.AWAITING);
    	channelPayOrderService.updateCmfOrderStatus(channelPayOrder, PayOrderStatus.IN_PROCESS);
        PayInstOrderResult result = new PayInstOrderResult();
        result.setRealAmount(channelPayOrder.getAmount());
        result.setOrderType(channelPayOrder.getBizType());
        result.setStatus(InstOrderResultStatus.AWAITING);
        result.setProcessStatus(InstOrderProcessStatus.AWAITING);
        result.setMemo("路由失败,没有可用渠道");

        return result;
    }
}
