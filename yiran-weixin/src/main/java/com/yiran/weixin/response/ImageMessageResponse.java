package com.yiran.weixin.response;
/**
 * 图文响应
 * @author 许盼
 *
 */
public class ImageMessageResponse extends BaseMessageResponse {
	
	//通过上传多媒体文件，得到的id。
	private String MediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	

}
