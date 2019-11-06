package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 控制类请求类型
 *
 */
public enum ControlRequestType {
    PRE_AUTH_REQUEST("PR", "预授权请求"),

    PRE_AUTH_REVERSAL("PD", "预授权请求冲正"),

    PRE_AUTH_REVERSAL_UNDO("PV", "预授权请求撤销冲正"),

    PRE_AUTH_UNDO("PU", "预授权请求撤销"),

    REVERSAL("D", "冲正"),

    REVERSAL_UNDO("V", "撤销冲正"),

    UNDO("U", "撤销"),

    DEBIT_ADVANCE("DBA", "扣款推进"),

    AUTHENTICATE("AUTH", "鉴权"),
    
    AUTH_ADVANCE("AUTHA", "鉴权推进"),

    TERMINATE("TERM", "解约"),

    SEND_MESSAGE("SM", "发送短信"),
    
    NOTIFY("NT", "通知"),
    //add by zhanghs 2016-05-05
    UPDATE_REPAYMENT_PLAN("URP", "更新还款计划")
    ;

    /** 代码 */
    private final String code;

    /** 描述信息 */
    private final String message;

    ControlRequestType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static ControlRequestType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (ControlRequestType type : ControlRequestType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
