package com.yiran.member.enums;

import org.apache.commons.lang3.StringUtils;


/**
 * <p>操作类型</p>
 * @author wangbin.ben
 * @version $Id: MethodType.java, v 0.1 2013-4-10 下午3:27:12 User Exp $
 */
public enum TerminalTypeEnum {
    WEB("web", "PC网页"),
    WAP("wap", "wap"),
    APP("app", "手机应用"),
    OTHER("other", "其他");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    TerminalTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static TerminalTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (TerminalTypeEnum type : TerminalTypeEnum.values()) {
            if (type.getCode().equals(code.toLowerCase())) {
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
