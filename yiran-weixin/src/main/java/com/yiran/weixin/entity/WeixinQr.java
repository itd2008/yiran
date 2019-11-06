package com.yiran.weixin.entity;

import java.util.Date;


/**
 * 微信二维码VO
 * @author pandaa
 *
 */
public class WeixinQr {
	
public final static int MAX_BASE_SNUM = 100000;
	//重置密码
	public final static int REPASSWORD_TYPE = 1;
	//设置组
	public final static int SET_GROUP_TYPE = 2;
	//登录
	public final static int TEMP_LOGIN = 11;
	//绑定
	public final static int TEMP_BIND = 12;
	//代理商ID
	public final static int AGENT_TYPE=3;
	
	private String id;
	private String name;
	private String type;
	private int status;
	private Integer snum;
	private String msg;
	private String qrimgname;
	private String qrData;
	private Date createDate;
	private String ticket;
	//二维码拥有者(关联sys_user表获取用户信息)
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQrimgname() {
		return qrimgname;
	}
	public void setQrimgname(String qrimgname) {
		this.qrimgname = qrimgname;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getSnum() {
		return snum;
	}
	public void setSnum(Integer snum) {
		this.snum = snum;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getQrData() {
		return qrData;
	}
	public void setQrData(String qrData) {
		this.qrData = qrData;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "WeixinQr [id=" + id + ", name=" + name + ", type=" + type + ", status=" + status + ", snum=" + snum
				+ ", msg=" + msg + ", qrimgname=" + qrimgname + ", qrData=" + qrData + ", createDate=" + createDate
				+ ", ticket=" + ticket + "]";
	}
	
	
}
