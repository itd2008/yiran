package com.yiran.payorder.validator;

import java.util.Currency;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.money.Money;
import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.request.PayOrderRequest;

/**
 * <p>资金请求通用校验器</p>
 */
public class FundRequestCommonValidator implements Validator, BasicConstant {
    
    @Override
    public void validate(Object model) throws ValidationException {
    	PayOrderRequest request = (PayOrderRequest) model;
        try {
            Assert.isTrue(StringUtil.isNotBlank(request.getPaymentSeqNo()), "支付流水号不能为空");
            Assert.notNull(request.getAmount(), "金额不能为空");

            Assert.isTrue(StringUtil.isNotBlank(request.getCurrencyCode()), "币种不能为空");
            Currency currency = Currency.getInstance(request.getCurrencyCode());
            Assert.notNull(currency, "币种不能为空");

            Money amount = new Money(request.getAmount(), currency);
            Assert.isTrue(amount.greaterThan(ZERO_MONEY), "支付金额必须大于零");
            
            Assert.isTrue(StringUtil.isNotBlank(request.getProductCode()), "产品编码不能为空");
            Assert.isTrue(StringUtil.isNotBlank(request.getPaymentCode()), "支付编码不能为空");
            Assert.isTrue(StringUtil.isNotBlank(request.getInstCode()), "目标机构不能为空");

        } catch (IllegalArgumentException e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
