package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>机构订单-处理状态</p>
 * 该字段表示CMF与外围系统交互时的状态;不须持久化到数据库.
 * 应用场景：比如通过Mule去调用外围系统进行结果处理时，需要传递此次调用的状态，以便outboundService/resultService等后续处理.
 */
public enum InstOrderProcessStatus {

    SUBMIT_CMF_FAIL("submitCmfFail", "提交CMF失败"), //对应PE的请求失败

    SUBMIT_INST_FAIL("submitInstFail","提交机构失败"), //提交机构失败,指查询时机构订单都不存在结果

    SUCCESS("S", "处理成功"),  //对应PE的CmfFundResultCode.SUCCESS， 同时可以持久化到机构订单结果表.

    AWAITING("A", "提交成功,等待通知"), //异步等待通知的情形. 对应PE的CmfFundResultCode.REQUEST_SUCCESS

    SUBMIT_INST_SUCCESS("IS", "提交渠道成功"), //仅用于出款，对应PE的CmfFundResultCode.SUBMIT_INST

    UNKNOW_EXCEPTION("E", "未知异常"), //对应PE的CmfFundResultCode.UNKNOW_EXCEPTION

    FAILURE("F", "订单处理中(CMF补单)");  //对应PE的CmfFundResultCode.InProcess

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    InstOrderProcessStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static InstOrderProcessStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (InstOrderProcessStatus type : InstOrderProcessStatus.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
