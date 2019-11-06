package com.yiran.api.enums;

import org.apache.commons.lang.StringUtils;

/**
* 验证参数枚举
* @version :1.0
*
*/
public enum VerifyResultEnum
{

	ERROR_01("E0001", "参数为空"),
	ERROR_02("E0002", "参数类型错误");  

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    VerifyResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static VerifyResultEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (VerifyResultEnum type : VerifyResultEnum.values()) {
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
