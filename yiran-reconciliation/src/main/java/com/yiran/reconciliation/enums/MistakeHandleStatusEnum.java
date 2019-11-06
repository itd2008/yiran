
package com.yiran.reconciliation.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 差错处理状态 .
 */
public enum MistakeHandleStatusEnum {

	HANDLED("已处理"),

	NOHANDLE("未处理");

	private String desc;

	private MistakeHandleStatusEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public static MistakeHandleStatusEnum getEnum(String name) {
		MistakeHandleStatusEnum[] arry = MistakeHandleStatusEnum.values();
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
