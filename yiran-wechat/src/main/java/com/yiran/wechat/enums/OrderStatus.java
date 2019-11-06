package com.yiran.wechat.enums;

import org.apache.commons.lang3.StringUtils;
/**
 * 订单状态:0未付款,1已付款,2已发货,3已签收,4退货申请,5退货中,6已退货,7取消交易,8交易完成
 * @author pandaa
 *
 */
public enum OrderStatus {
	UNPAID("0", "未付款"),

	PAYMENT("1", "已付款"),

	SHIPPED("2", "已发货"),

	ALREADY_SIGNED("3", "已签收"),
    
	RETURN_REQUEST("4", "退货申请"),
    
	RETURNS("5", "退货中"),
	
	RETURN_COMPLETED("6", "已退货"),

	CANCEL_TRANSACTION("7", "取消交易"),
	
	TRANSACTION_COMPLETED("8","交易完成");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    OrderStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static OrderStatus getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (OrderStatus type : OrderStatus.values()) {
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
