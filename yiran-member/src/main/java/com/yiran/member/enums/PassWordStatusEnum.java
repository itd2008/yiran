package com.yiran.member.enums;

public enum PassWordStatusEnum {

    INIT(0, "初始状态"),
    NORMAL(1, "正常") ;

    /** 代码 */
    private final int    code;
    /** 信息 */
    private final String message;

    private PassWordStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static PassWordStatusEnum getByCode(int code) {
        for (PassWordStatusEnum item : PassWordStatusEnum.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
