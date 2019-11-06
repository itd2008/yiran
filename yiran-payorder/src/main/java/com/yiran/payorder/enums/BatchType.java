package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>
 * 批次归档状态
 * </p>
 *
 */
public enum BatchType {
	CHECK("C", "复核批次"),

	RESULT("R", "结果批次");

	/** 代码 */
	private final String code;
	/** 信息 */
	private final String message;


	BatchType(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 通过代码获取ENUM
	 *
	 * @param code
	 * @return
	 */
	public static BatchType getByCode(String code) {
		if (StringUtil.isBlank(code)) {
			return null;
		}

		for (BatchType status : BatchType.values()) {
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
