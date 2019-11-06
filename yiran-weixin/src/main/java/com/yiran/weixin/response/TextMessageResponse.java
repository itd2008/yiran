package com.yiran.weixin.response;

/**
 * 响应-文本消息
 * @author 许盼
 *
 */
public class TextMessageResponse extends BaseMessageResponse {
	
	//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	

}
