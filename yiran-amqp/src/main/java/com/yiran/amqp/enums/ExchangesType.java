package com.yiran.amqp.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * RabbitMQ渠道类型
 * @author pandaa
 *
 */
public enum ExchangesType {

	DIRECT("Direct", "Direct"),

	TOPIC("Topic", "Topic"),

	HEADERS("Headers", "Headers"),
	
	FANOUT("Fanout", "Fanout");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    ExchangesType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static ExchangesType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (ExchangesType type : ExchangesType.values()) {
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
