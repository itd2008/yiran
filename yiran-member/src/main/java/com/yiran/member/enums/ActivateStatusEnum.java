package com.yiran.member.enums;


/**
* @ClassName: ActivateStatusEnum
* @Description: 激活状态（账户是否激状态枚举）
* 
*/
public enum ActivateStatusEnum {

	NOTACTIVATED(0L,"未激活"),
	ACTIVATED(1L,"已激活");
	
	  /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    ActivateStatusEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static ActivateStatusEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (ActivateStatusEnum le : ActivateStatusEnum.values()) {
            if (le.getCode().equals(code)) {
                return le;
            }
        }

        return null;
    }
    
    public static ActivateStatusEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (ActivateStatusEnum le : ActivateStatusEnum.values()) {
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
