package com.yiran.payorder.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.ManualRefundType;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.IRefundProcessService;

/**
 * 
 * <p>退款处理实现</p>
 */
@Service
public class RefundProcessServiceImpl implements IRefundProcessService {

	@Autowired
    private IFundChannelRouter   fundChannelRouter;

	@Autowired
   	private IPayInstOrderService payInstOrderService;

	@Override
    public void processRefund(PayInstOrder instOrder, PayInstOrderResult result) {
        if (!canTransfer(instOrder)) {
            return;
        }

        //结果不为空时,更新备注信息为失败,由后续定时任务转人工
        if (result != null && InstOrderResultStatus.FAILURE.equals(result.getStatus())) {
            String memo = ManualRefundType.AUTO_REFUND_FAILED.getCode();
            if (!StringUtil.isEmpty(result.getMemo())) {
                memo = memo + ManualRefundType.SPLIT_CHARACTER + result.getMemo();
            }
            result.setMemo(memo);

            instOrder.setMemo(result.getMemo());
            payInstOrderService.updateMemoById(instOrder.getMemo(), instOrder.getInstOrderId());
            result.setProcessStatus(InstOrderProcessStatus.AWAITING);
            result.setStatus(InstOrderResultStatus.AWAITING);
            return;
        }
    }

    @Override
    public void processRefund(PayInstOrder instOrder, ManualRefundType refundType) {
        if (!canTransfer(instOrder)) {
            return;
        }
        switch (refundType) {
            case REFUND_NON_RETURN_RESULT:
                //如果类型是超过固定时限未返回结果,更新memo信息,直接转人工
                instOrder.setMemo(ManualRefundType.REFUND_NON_RETURN_RESULT.getCode());
                payInstOrderService.updateMemoById(instOrder.getMemo(), instOrder.getInstOrderId());
                transfer2Manual(instOrder);
                break;
            case OTHERS:
            case AUTO_REFUND_FAILED:
                //其它,退款失败,直接转人工
                transfer2Manual(instOrder);
                break;
            default:
                break;
        }
    }

    /**
     * 转人工操作,修改API
     * @param instOrder
     */
    private void transfer2Manual(PayInstOrder instOrder) {
        TmFundChannel channel = fundChannelRouter.routerFundChannel(instOrder.getFundChannelCode(),
            FundChannelApiType.MANUAL_REFUND);
        instOrder.setFundChannelApi(channel.getApi());
        instOrder.setFundChannelCode(channel.getFundChannelCode());
        payInstOrderService.updateChannelInfoById(channel.getFundChannelCode(), channel.getApi()
            .getApiCode(), instOrder.getInstOrderId());
    }

    private boolean canTransfer(PayInstOrder instOrder) {
    	
        return BizType.REFUND.equals(instOrder.getBizType())
               && FundChannelApiType.SINGLE_REFUND.equals(instOrder.getFundChannelApi()
                   .getApiType()) && instOrder.getFundChannel().isManualRefundSupported();
    }
    
}
