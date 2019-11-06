
package com.yiran.reconciliation.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共状态枚举. 只有 (冻结) 与(激活) 两种状态
 */
public enum PublicStatusEnum {

	ACTIVE("激活"),

	UNACTIVE("冻结");

	/** 描述 */
	private String desc;

	private PublicStatusEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static Map<String, Map<String, Object>> toMap() {
		PublicStatusEnum[] ary = PublicStatusEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = ary[num].name();
			map.put("value", ary[num].name());
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList() {
		PublicStatusEnum[] ary = PublicStatusEnum.values();
		List list = new ArrayList();
		for (int i = 0; i < ary.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("desc", ary[i].getDesc());
			map.put("name", ary[i].name());
			list.add(map);
		}
		return list;
	}

	public static PublicStatusEnum getEnum(String name) {
		PublicStatusEnum[] arry = PublicStatusEnum.values();
		for (int i = 0; i < arry.length; i++) {
			if (arry[i].name().equalsIgnoreCase(name)) {
				return arry[i];
			}
		}
		return null;
	}

	/**
	 * 取枚举的json字符串
	 * 
	 * @return
	 */
	public static String getJsonStr() {
		PublicStatusEnum[] enums = PublicStatusEnum.values();
		StringBuffer jsonStr = new StringBuffer("[");
		for (PublicStatusEnum senum : enums) {
			if (!"[".equals(jsonStr.toString())) {
				jsonStr.append(",");
			}
			jsonStr.append("{id:'").append(senum).append("',desc:'").append(senum.getDesc()).append("'}");
		}
		jsonStr.append("]");
		return jsonStr.toString();
	}

}
