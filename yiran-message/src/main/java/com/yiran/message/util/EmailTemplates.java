package com.yiran.message.util;

public class EmailTemplates {

	/**
	 * 普通文本邮件模版
	 * @param subject
	 * @param msg
	 * @return
	 */
	public static String getTemplate(String subject,String msg) {
		StringBuffer sbf = new StringBuffer("<!DOCTYPE HTML><html lang=\"en\"><head>");
		sbf.append("<style>body{background:#f0f0f0}p{font-family:\"Times New Roman\",Georgia,Serif}</style></head>");
		sbf.append("<body><div style=\"height:30px;display:inline-block;padding:10px 10px 0px 10px;background-color:#1ab394;color:#fff;font-weight:bold;\">依然邮件</div>");
		sbf.append("<div style=\"padding:10px;border: 1px solid #1ab394;height: 100%;\">");
		sbf.append("<p style=\"padding-left: 20px;\">"+msg+"</p></div></body></html>");
		return sbf.toString();
	}
}
