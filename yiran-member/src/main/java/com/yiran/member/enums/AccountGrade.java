package com.yiran.member.enums;

public enum AccountGrade {
	COMMON(1, "普通账户"), 
	VIP(9, "VIP账户"),
	;

	private final int code;
	private final String displayName;

	AccountGrade(int code, String displayName) {
		this.code = code;
		this.displayName = displayName;

	}

	public int code() {
		return code;
	}

	public String displayName() {
		return displayName;
	}
	
	public static AccountGrade getByCode(int code){
		for(AccountGrade item: values()){
			if(item.code() == code){
				return item;
			}
		}
		return null;
	}
}
