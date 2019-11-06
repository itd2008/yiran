package com.yiran.payorder.validator;

import org.springframework.stereotype.Service;

import com.netfinworks.biz.common.util.BaseResult;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.InstOrderResultStatus;

/**
 * <p>渠道返回结果校验</p>
 */
@Service
public class ChannelResultValidator {
    
    /**
     * 渠道返回结果校验
     * @param instOrder
     * @param instOrderResult
     * @return
     */
    public BaseResult validate(PayInstOrder instOrder,PayInstOrderResult instOrderResult) {
        BaseResult baseResult = new BaseResult(true,"");
        if (!InstOrderResultStatus.SUCCESSFUL.equals(instOrderResult.getStatus())
                && !InstOrderResultStatus.RISK.equals(instOrderResult.getStatus())) {
        	return baseResult;
        }
        if(!instOrder.getAmount().equals(instOrderResult.getRealAmount())){
            baseResult.setResultMessage(baseResult.getResultMessage().concat("金额校验不通过,"
            		+ "原金额[" + instOrder.getAmount() +",返回金额[" + instOrderResult.getRealAmount() + "]"));
            baseResult.setSuccess(false);
        }
        return baseResult;
    }
}
