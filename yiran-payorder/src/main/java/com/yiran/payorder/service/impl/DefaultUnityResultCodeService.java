package com.yiran.payorder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.paychannel.domain.TmApiResultCode;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.service.ITmUnityResultCodeService;
import com.yiran.payorder.domain.InstBaseResult;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.service.IUnityResultCodeService;

/**
 * 
 * <p>统一结果编码处理实现接口</p>
 */
@Service("unityResultCodeService")
public class DefaultUnityResultCodeService implements IUnityResultCodeService {

    private Logger           logger = LoggerFactory.getLogger(DefaultUnityResultCodeService.class);

    @Autowired
    private ITmUnityResultCodeService tmUnityResultCodeService;

    /**
     * 根据结果码转换订单状态
     * @param instOrderResult
     * @param fundsChannelCode
     */
    @Override
    public void fillResultStatus(InstBaseResult instBaseResult, String fundsChannelCode) {
        String instOrderNo = instBaseResult.getInstOrderNo();

        //渠道返回结果 改造后 都会设置 apitype
        if (!instBaseResult.isReturnCodeRefacted()) {
            if (instBaseResult.getStatus() == null) {
                if (logger.isInfoEnabled()) {
                    logger.info("渠道[" + fundsChannelCode + "]返回码未改造，使用原编码格式,instOrderNo="
                                + instOrderNo + "状态为空,返回异常结果");
                }
                buildFailResult(instBaseResult);
            } else {
                if (logger.isInfoEnabled()) {
                    logger.info("渠道[" + fundsChannelCode + "]返回码未改造，使用原编码格式,instOrderNo="
                                + instOrderNo + ",inst_status=" + instBaseResult.getStatus());
                }
            }
            return;
        }

        TmApiResultCode apiResultCode = tmUnityResultCodeService.parse(fundsChannelCode,
            instBaseResult.getApiType(), instBaseResult.getApiResultCode(),
            instBaseResult.getApiResultSubCode(), null,instBaseResult.getMemo());

        // 开启映射，填充统一结果对象编码
        if (apiResultCode.getUnityResultCode() != null) {
            instBaseResult.setInstResultCode(apiResultCode.getUnityResultCode());
            instBaseResult.getExtension().put(ExtensionKey.UNITY_RESULT_MESSAGE.key,
                apiResultCode.getUnityResultCode());
        }else {
        	instBaseResult.getExtension().put(ExtensionKey.UNITY_RESULT_MESSAGE.key,
        			instBaseResult.getResultMessage());
        }

        if (isUndefinedCode(apiResultCode)) {
            instBaseResult.setStatus(InstOrderResultStatus.QUESTIONABLE);
        } else {
            instBaseResult.setStatus(InstOrderResultStatus.getByCode(apiResultCode.getOrderStatus()));
        }

        // 设置机构订单处理状态
        switch (instBaseResult.getStatus()) {
            case SUCCESSFUL:
            case FAILURE:
                instBaseResult.setProcessStatus(InstOrderProcessStatus.SUCCESS);
                break;
            case NONEXISTS:
                instBaseResult.setProcessStatus(InstOrderProcessStatus.SUBMIT_INST_FAIL);
                break;
            case QUESTIONABLE://未找到对应的统一编码，期待后续继续识别完成后处理
            default:
                instBaseResult.setProcessStatus(InstOrderProcessStatus.AWAITING);
                break;
        }

        //设置备注信息
        instBaseResult.setMemo(apiResultCode.getMemo());
        instBaseResult.setResultMessage(apiResultCode.getMemo());
        if (logger.isInfoEnabled()) {
            logger.info("获取统一结果编码成功:instOrderNo=" + instOrderNo + ",instStatus="
                        + instBaseResult.getStatus() + ",processStatus="
                        + instBaseResult.getProcessStatus());
        }
    }


    /**
     * 是否定义编码
     * @param code
     * @return
     */
    private boolean isUndefinedCode(TmApiResultCode code) {
        if (code == null || code.getOrderStatus() == null) {
            return true;
        }

        if ("N".equals(code.getUseMapping())) {
            return true;
        }

        return false;
    }

    /**
     * 构造错误结果信息
     * @param instResult
     */
    private void buildFailResult(InstBaseResult instResult) {
        instResult.setProcessStatus(InstOrderProcessStatus.UNKNOW_EXCEPTION);
        instResult.setStatus(InstOrderResultStatus.AWAITING);
    }

}
