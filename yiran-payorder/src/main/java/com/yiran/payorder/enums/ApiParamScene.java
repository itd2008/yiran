package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * 
 * <p>api使用参数</p>
 */
public enum ApiParamScene {
    
    REQUEST_CHANNEL("request", "请求渠道"),

    CHANNEL_RETUEN("response", "渠道返回");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    ApiParamScene(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static ApiParamScene getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (ApiParamScene type : ApiParamScene.values()) {
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
