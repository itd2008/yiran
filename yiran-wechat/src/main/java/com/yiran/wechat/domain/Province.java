package com.yiran.wechat.domain;

import java.util.List;

public class Province {

	private String name;
	
	private String code;
	
	private List<City> sub;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<City> getSub() {
		return sub;
	}

	public void setSub(List<City> sub) {
		this.sub = sub;
	}
	
	
}
