package com.yiran.payorder.validator;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ControlRequestType;

/**
 * <p>校验器工厂默认实现</p>
 */
@Service
public class DefaultValidatorFactory implements ValidatorFactory {
    /** 控制校验器MAP */
    private static  Map<String, Validator> controlValidatorMap = new HashMap<String, Validator>();
    /** 资金校验器MAP */
    private static  Map<String, Validator> fundValidatorMap = new HashMap<String, Validator>();
    /**
     * 初始化数据
     */
    static{
    	fundValidatorMap.put("FUNDIN", new FundRequestCommonValidator());
    }

    @Override
    public Validator load(ControlRequestType requestType) throws ValidationException {
        if (requestType == null) {
            throw new ValidationException("请求类型不能为空");
        }

        return controlValidatorMap.get(requestType.name());
    }

    @Override
    public Validator load(BizType bizType) throws ValidationException {
        if (bizType == null) {
            throw new ValidationException("业务类型不能为空");
        }
        Validator validator = fundValidatorMap.get(bizType.name());
        return validator;
    }


}
