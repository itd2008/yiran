package com.yiran.weixin.response;
/**
 * 语音回复
 * @author 许盼
 *
 */
public class VoiceMessageResponse extends BaseMessageResponse{
	//通过上传多媒体文件，得到的id
	private String MediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	

}
