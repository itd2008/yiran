package com.yiran.weixin.request;

/**
 * 文本消息
 * @author 许盼
 *
 */
public class TextMessage extends BaseMessage {
	
	//文本消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	

}
