package com.yiran.common.base;
/**
 * 默认结果码枚举
 * @author pandaa
 *
 */
public enum DefaultResultEnum implements AbsResultEnum {
	SUCCESS("200", "成功"),
    FAIL("400", "失败"),
    ERROR("500", "异常");

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    DefaultResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "DefaultResultEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                "} " + super.toString();
    }
}
