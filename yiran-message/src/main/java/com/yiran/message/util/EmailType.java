package com.yiran.message.util;

/**
 * 邮件类型枚举类.
 * PLAIN: 简单类型邮件，即邮件正文只包含有文本信息（可带附件）
 * IMPLE: HTML类型邮件，即邮件正文由HTML信息构成（可带附件）
 */
public enum EmailType {
	
    /**
     * 邮件类型.
     */
    PLAIN, HTML;
}
