package com.yiran.weixin.entity;
public class DuplicateMessage {
	private String fromUserName;
	private String createTime;
	private Long curTime;
	private String rel;
	
	public Long getCurTime() {
		return curTime;
	}
	public void setCurTime(Long curTime) {
		this.curTime = curTime;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public DuplicateMessage() {
	}
	
	public DuplicateMessage(String fromUserName, String createTime) {
		super();
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.curTime = System.currentTimeMillis();
		this.rel = null;
	}
	@Override
	public boolean equals(Object obj) {
		DuplicateMessage dm = (DuplicateMessage)obj;
		if(dm.getCreateTime().equals(this.getCreateTime())&&(dm.getFromUserName()).equals(this.getFromUserName())) {
			return true;
		}
		return false;
	}
}
