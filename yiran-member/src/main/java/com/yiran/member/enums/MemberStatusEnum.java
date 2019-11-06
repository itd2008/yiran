package com.yiran.member.enums;

/**
 * <p>会员状态枚举</p>
 * 
 * 详见《详细设计文档》中关于会员状态的状态变化图
 * 
 */
public enum MemberStatusEnum {
    UNACTIVE(0L, "未激活"), NORMAL(1L, "正常"), SLEEP(2L, "休眠"), CANCEL(3L, "销户");

    /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    MemberStatusEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static MemberStatusEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (MemberStatusEnum memberStatus : MemberStatusEnum.values()) {
            if (memberStatus.getCode().equals(code)) {
                return memberStatus;
            }
        }

        return null;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
