package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 *
 * <p>监控日志状态</p>
 */
public enum MonitorLogStatus {

    AWAITING("A", "待发送"),

    IN_PROCESS("I", "正在发送"),

    NOT_SEND("N", "本批次不发送"),

    SUCCESSFUL("S", "完成");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    MonitorLogStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static MonitorLogStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (MonitorLogStatus type : MonitorLogStatus.values()) {
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
