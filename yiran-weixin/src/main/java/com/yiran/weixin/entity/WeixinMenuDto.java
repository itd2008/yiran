package com.yiran.weixin.entity;
import java.util.ArrayList;
import java.util.List;

public class WeixinMenuDto {
	private String name;//菜单名
	private String key;//
	private String type;
	private String url;
	//子菜单集合   sub_button不能是null
	private List<WeixinMenuDto> sub_button = new ArrayList<WeixinMenuDto>();
	private String id;
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<WeixinMenuDto> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WeixinMenuDto> sub_button) {
		this.sub_button = sub_button;
	}
}
