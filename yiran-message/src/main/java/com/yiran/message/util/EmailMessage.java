package com.yiran.message.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 邮件对象，可以包含多个邮件附件对象.
 */
public class EmailMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
    private String emailId;

    /**
     * 源地址.
     */
    private String from;

    /**
     * 回复地址.
     */
    private List<String> replyTo = new ArrayList<String>();

    /**
     * 目的地址.
     */
    private List<String> to = new ArrayList<String>();

    /**
     * 抄送地址.
     */
    private List<String> cc = new ArrayList<String>();

    /**
     * 密送地址.
     */
    private List<String> bcc = new ArrayList<String>();

    /**
     * 时间.
     */
    private Date sentDate = null;

    /**
     * 主题.
     */
    private String subject;

    /**
     * 字符集.
     */
    private String charset = "GBK";

    /**
     * 邮件正文.
     */
    private String msg;

    /**
     * HTML邮件正文.
     */
    private String htmlMsg;

    /**
     * TEXT邮件正文，用于HTML正文无法现实是的替代文本. 建议用msg代替这个文本
     */
    private String textMsg;

    /**
     * 附件列表.
     */
    private List<EmailMessageAttachment> attachements = new ArrayList<EmailMessageAttachment>();

    /**
     * 邮件类型，默认为PLAIN.
     */
    private EmailType type = EmailType.PLAIN;
    
    /**
     * 是否同步发送
     */
    private boolean isAsynSend = true;
    

    public void addReplyTo(String replyTo) {
        this.replyTo.add(replyTo);
    }

    public void addTo(String to) {
        this.to.add(to);
    }

    public void addCc(String cc) {
        this.cc.add(cc);
    }

    public void addBcc(String bcc) {
        this.bcc.add(bcc);
    }
    
    public void addAttachment(EmailMessageAttachment attachment) {
        this.attachements.add(attachment);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(List<String> replyTo) {
        this.replyTo = replyTo;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHtmlMsg() {
        return htmlMsg;
    }

    public void setHtmlMsg(String htmlMsg) {
        this.htmlMsg = htmlMsg;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public EmailType getType() {
        return type;
    }

    public void setType(EmailType type) {
        this.type = type;
    }

    public List<EmailMessageAttachment> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<EmailMessageAttachment> attachements) {
        this.attachements = attachements;
    }


    public String toString() {
        return "EmailMessage{" +
                "textMsg='" + textMsg + '\'' +
                ", to=" + to +
                ", subject='" + subject + '\'' +
                ", sentDate=" + sentDate +
                ", from='" + from + '\'' +
                ", htmlMsg='" + htmlMsg + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

	public boolean isAsynSend() {
		return isAsynSend;
	}

	public void setAsynSend(boolean isAsynSend) {
		this.isAsynSend = isAsynSend;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
