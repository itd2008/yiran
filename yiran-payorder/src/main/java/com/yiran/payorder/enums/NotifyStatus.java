package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>CMF订单-支付结果通知状态</p>
 */
public enum NotifyStatus {
	FAILURE("F", "通知失败"),

    SUCCESSFUL("S", "通知成功"),

    CHANNEL_CODE_NOTIFY_SUCCESS("C", "通知PE出款渠道成功"),

    NOT_NOTIFY("N", "不通知"),

	AWAITING("A","等待通知"),

	IN_PROCESS("I","通知中");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    NotifyStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static NotifyStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (NotifyStatus type : NotifyStatus.values()) {
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
