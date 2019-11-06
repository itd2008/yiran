/**
 * 
 */
package com.yiran.member.enums;

/**
 * <p>账户余额明细查询排序</p>
 */
public enum OrderModeEnum {
    ASE(1, "正序"), DESC(2, "倒序");
    
    private Integer code;
    private String  displayName;

    OrderModeEnum(Integer code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static OrderModeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (OrderModeEnum item : OrderModeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }

        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
