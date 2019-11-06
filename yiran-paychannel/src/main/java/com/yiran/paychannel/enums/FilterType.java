package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>过滤规则类型</p>
 */
public enum FilterType {
	
	CHANNEL("channel", "按渠道过滤"),

	API("api", "按api过滤");

	/** 代码 */
	private final String code;
	/** 信息 */
	private final String message;


	FilterType(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 通过代码获取ENUM
	 *
	 * @param code
	 * @return
	 */
	public static FilterType getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}

		for (FilterType status : FilterType.values()) {
			if (status.getCode().equalsIgnoreCase(code)) {
				return status;
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
