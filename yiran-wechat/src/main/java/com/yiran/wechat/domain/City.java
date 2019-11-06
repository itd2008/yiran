package com.yiran.wechat.domain;

import java.util.List;

public class City {

	private String name;
	
	private String code;
	
	private List<Area> sub;

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

	public List<Area> getSub() {
		return sub;
	}

	public void setSub(List<Area> sub) {
		this.sub = sub;
	}
	
	
}
