package com.yiran.message.util;


import java.io.Serializable;

/**
 * 邮件附件对象.
 */
public class EmailMessageAttachment implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 邮件Id
     */
    private String emailId = null;

    /**
     * 附件名称.
     */
    private String name = null;

    /**
     * 附件描述.
     */
    private String description = null;

    /**
     * 附件路径.
     */
    private String path = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
