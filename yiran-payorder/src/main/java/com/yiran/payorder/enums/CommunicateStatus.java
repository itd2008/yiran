package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>机构订单-通讯状态</p>
 */
public enum CommunicateStatus {
    AWAITING("A", "等待指令发送"),

    SENT("S", "指令已经发送"),

    RECEIVED("R", "数据已经返回"),

    FAILURE("F", "指令发送失败");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    CommunicateStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static CommunicateStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (CommunicateStatus type : CommunicateStatus.values()) {
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
