package com.yiran.weixin.response;

/**
 *视频回复
 * @author 许盼
 *
 */
public class VideoMessageResponse extends BaseMessageResponse{
	//通过上传多媒体文件，得到的id
	private String MediaId;
	//视频的标题
	private String title;
	//视频的描述
	private String Description;
	
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
