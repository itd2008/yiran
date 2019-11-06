package com.yiran.message.domain;

import java.util.Date;

import com.yiran.common.base.BaseEntity;

public class Email extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 邮箱编号 */
	private Long emailId;
	
	/** 0：站内信；1：站外信 */
	private int emailSite;
	
	/** 用户编号，发送方 */
	private Long formUser;
	
	/** 用户编号，接收方 */
	private Long toUser;
	
	/** 接收方邮件地址 */
	private String toUserEmail;
	
	/** 邮件主题 */
	private String emailSubject;
	
	/** 邮件内容 */
	private String emailContent;
	
	/** 邮件状态(0：正常；1：已删除（注意进入垃圾箱内并非已删除，垃圾箱内的邮件可恢复）) */
	private int emailStatus;
	
	/** 邮件类型(例：工作，广告，文档等，可在字典中配置) */
	private String emailType;
	
	/** 邮件文件夹(例：收件箱，重要，草稿，垃圾箱等，可在字典中配置)) */
	private String emailFolder;
	
	/** 邮件标签(例：朋友；音乐，家庭，自定义标签，可在字典中配置) */
	private String emailLabel;
	
	/** 发送邮件状态(0：成功；1：失败) */
	private int sendStatus;
	
	/** 创建者 */
	private String createBy;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 发信人姓名*/
	private String userName;
	
	/** 传递 */
	private String toUserIds;
	private String toUserEmails;

	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	public int getEmailSite() {
		return emailSite;
	}

	public void setEmailSite(int emailSite) {
		this.emailSite = emailSite;
	}

	public Long getFormUser() {
		return formUser;
	}

	public void setFormUser(Long formUser) {
		this.formUser = formUser;
	}

	public Long getToUser() {
		return toUser;
	}

	public void setToUser(Long toUser) {
		this.toUser = toUser;
	}

	public String getToUserEmail() {
		return toUserEmail;
	}

	public void setToUserEmail(String toUserEmail) {
		this.toUserEmail = toUserEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public int getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(int emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmailFolder() {
		return emailFolder;
	}

	public void setEmailFolder(String emailFolder) {
		this.emailFolder = emailFolder;
	}

	public String getEmailLabel() {
		return emailLabel;
	}

	public void setEmailLabel(String emailLabel) {
		this.emailLabel = emailLabel;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getToUserIds() {
		return toUserIds;
	}

	public void setToUserIds(String toUserIds) {
		this.toUserIds = toUserIds;
	}

	public String getToUserEmails() {
		return toUserEmails;
	}

	public void setToUserEmails(String toUserEmails) {
		this.toUserEmails = toUserEmails;
	}

	@Override
	public String toString() {
		return "Email [emailId=" + emailId + ", emailSite=" + emailSite + ", formUser=" + formUser + ", toUser="
				+ toUser + ", toUserEmail=" + toUserEmail + ", emailSubject=" + emailSubject + ", emailContent="
				+ emailContent + ", emailStatus=" + emailStatus + ", emailType=" + emailType + ", emailFolder="
				+ emailFolder + ", emailLabel=" + emailLabel + ", sendStatus=" + sendStatus + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", toUserIds=" + toUserIds + ", toUserEmails=" + toUserEmails + "]";
	}
}
