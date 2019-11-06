
package com.yiran.reconciliation.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对账状态枚举
 *
 */
public enum ReleaseStatusEnum {

	SUCCESS("解析成功"),

	FAIL("解析失败");

	private String desc;

	private ReleaseStatusEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public static ReleaseStatusEnum getEnum(String name) {
		ReleaseStatusEnum[] arry = ReleaseStatusEnum.values();
		for (int i = 0; i < arry.length; i++) {
			if (arry[i].name().equals(name)) {
				return arry[i];
			}
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		MistakeHandleStatusEnum[] ary = MistakeHandleStatusEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("desc", ary[i].getDesc());
			map.put("name", ary[i].name());
			list.add(map);
		}
		return list;
	}
}
