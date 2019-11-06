package com.yiran.member.enums;


/**
* @ClassName: LogoutStatusEnum
* @Description: 销户状态
* 
*/
public enum LifeCycleStatusEnum {

	NOT_LOGOUT(0L,"未销户"),
	LOGOUT(1L,"已销户"),
	INACTIVE_ACCOUNTS(2L,"已结转长期不动户");
	
	/** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    LifeCycleStatusEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static LifeCycleStatusEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (LifeCycleStatusEnum le : LifeCycleStatusEnum.values()) {
            if (le.getCode().equals(code)) {
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
