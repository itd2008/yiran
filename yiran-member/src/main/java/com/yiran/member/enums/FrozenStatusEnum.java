package com.yiran.member.enums;


/**
* @ClassName: LogoutStatusEnum
* @Description: 冻结状态
* 
*/
public enum FrozenStatusEnum {

	INIT(0L,"未冻结"),
	FROZEN_OUT(1L, "止出"), 
	FROZEN_IN(2L, "止入"),
	FROZEN_IN_OUT(3L, "锁定");
	
	/** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    FrozenStatusEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static FrozenStatusEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (FrozenStatusEnum le : FrozenStatusEnum.values()) {
            if (le.getCode().equals(code)) {
                return le;
            }
        }

        return null;
    }
    
    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static FrozenStatusEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (FrozenStatusEnum le : FrozenStatusEnum.values()) {
            if (le.getCode().toString().equals(code)) {
                return le;
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
