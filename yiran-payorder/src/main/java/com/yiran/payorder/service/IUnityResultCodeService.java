package com.yiran.payorder.service;

import com.yiran.payorder.domain.InstBaseResult;

/**
 * 
 * <p>统一结果编码处理服务接口</p>
 */
public interface IUnityResultCodeService {

    /**
     * 依据返回码补全返回状态
     * @param instOrderResult
     * @param fundsChannelCode
     */
    public void fillResultStatus(InstBaseResult instBaseResult, String fundsChannelCode);

}
