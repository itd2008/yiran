package com.yiran.payorder.validator;

import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ControlRequestType;

/**
 * <p>校验器工厂</p>
 */
public interface ValidatorFactory {
    /**
     * 根据控制请求类型获取校验器
     * @param requestType
     * @return
     * @throws ValidationException
     */
    public Validator load(ControlRequestType requestType) throws ValidationException;

    /**
     * 根据资金业务类型获取校验器
     * @param bizType
     * @return
     * @throws ValidationException
     */
    public Validator load(BizType bizType) throws ValidationException;
}
