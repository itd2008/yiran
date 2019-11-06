package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 查询类型
 *
 */
public enum QueryType {

    BATCH_ID("batchId", "批次号查询"),

    ORDER_RANGE("orderRange", "订单号区间查询"),

    TIME_RANGE("timeRange", "时间区间查询"), ;

    /** 代码 */
    private final String code;

    /** 描述信息 */
    private final String message;

    QueryType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static QueryType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (QueryType type : QueryType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
