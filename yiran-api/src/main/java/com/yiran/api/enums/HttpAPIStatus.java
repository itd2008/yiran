package com.yiran.api.enums;

import org.apache.commons.lang.StringUtils;

public enum HttpAPIStatus {

	INVALID_CLIENTID("30003", "Invalid clientid"),
	INVALID_PASSWORD("30004", "User name or password is incorrect"),
	INVALID_CAPTCHA("30005", "Invalid captcha or captcha overdue"),
	INVALID_TOKEN("30006", "Invalid token"),
	IP_White("30007","IP is not in the whitelist"),
	TOKEN_VERIFICATION_FAILED("30008","Token verification failed");

	/** 代码 */
	private final String code;

	/** 描述信息 */
	private final String message;

	HttpAPIStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
     * 通过代码获取
     * @param code
     * @return
     */
    public static HttpAPIStatus getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (HttpAPIStatus type : HttpAPIStatus.values()) {
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
