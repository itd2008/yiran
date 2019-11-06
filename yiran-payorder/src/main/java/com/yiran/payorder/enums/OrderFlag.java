package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>
 * 机构订单-状态
 * </p>
 */
public enum OrderFlag {
	DEFAULT("D", "默认状态"),

	PAUSE("P", "暂停状态");

	/** 代码 */
	private final String code;
	/** 信息 */
	private final String message;

	/**
	 * 构造
	 *
	 * @param code
	 * @param message
	 */
	OrderFlag(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 通过代码获取
	 *
	 * @param code
	 * @return
	 */
	public static OrderFlag getByCode(String code) {
		if (StringUtil.isBlank(code)) {
			return null;
		}

		for (OrderFlag type : OrderFlag.values()) {
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
