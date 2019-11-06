package com.yiran.paychannel.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * 
 * <p>与渠道交互的渠道特有信息状态</p>
 */
public enum ChannelTransInfoStatus {
    IN_PORCESS("I", "使用中"),
    CLOSED("C", "已关闭");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    ChannelTransInfoStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static ChannelTransInfoStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (ChannelTransInfoStatus type : ChannelTransInfoStatus.values()) {
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
