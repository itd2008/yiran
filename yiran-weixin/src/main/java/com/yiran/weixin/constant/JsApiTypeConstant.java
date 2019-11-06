package com.yiran.weixin.constant;
/**
 * js接口类型常量
 * @author pandaa
 *
 */
public class JsApiTypeConstant {
	/**
	 * onMenuShareTimeline 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口（即将废弃）
	 */
	public static final String ON_MENU_SHARE_TIMELINE ="onMenuShareTimeline";
	/**
	 * onMenuShareAppMessage 获取“分享给朋友”按钮点击状态及自定义分享内容接口（即将废弃）
	 */
	public static final String ON_MENU_SHARE_APP_MESSAGE ="onMenuShareAppMessage";
	/**
	 * updateAppMessageShareData 自定义“分享给朋友”及“分享到QQ”按钮的分享内容
	 */
	public static final String UPDATE_APP_MESSAGE_SHARE_DATA ="updateAppMessageShareData";
	/**
	 * updateTimelineShareData  自定义“分享到朋友圈”及“分享到QQ空间”按钮的分享内容
	 */
	public static final String UPDATE_TIMELINE_SHARE_DATA ="updateTimelineShareData";
	
	/**
	 * onMenuShareWeibo 获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
	 */
	public static final String ON_MENU_SHARE_WEIBO ="onMenuShareWeibo";
	
	/**
	 * chooseImage 拍照或从手机相册中选图接口
	 */
	public static final String CHOOSE_IMAGE ="chooseImage";
	/**
	 * previewImage 预览图片接口
	 */
	public static final String PREVIEW_IMAGE ="previewImage";
	/**
	 * uploadImage 上传图片接口
	 */
	public static final String UPLOAD_IMAGE ="uploadImage";
	/**
	 * downloadImage 下载图片接口
	 */
	public static final String DOWNLOAD_IMAGE ="downloadImage";
	/**
	 * startRecord 开始录音接口
	 */
	public static final String START_RECORD ="startRecord";
	/**
	 * stopRecord 停止录音接口
	 */
	public static final String STOP_RECORD ="stopRecord";
	/**
	 * onVoiceRecordEnd 监听录音自动停止接口
	 */
	public static final String ON_VOICE_RECORD_END ="onVoiceRecordEnd";
	/**
	 * playVoice 播放语音接口
	 */
	public static final String PLAY_VOICE ="playVoice";
	/**
	 * pauseVoice 暂停播放接口
	 */
	public static final String PAUSE_VOICE ="pauseVoice";
	/**
	 * stopVoice 停止播放接口
	 */
	public static final String STOP_VOICE ="stopVoice";
	/**
	 * onVoicePlayEnd 监听语音播放完毕接口
	 */
	public static final String ON_VOICE_PLAYEND ="onVoicePlayEnd";
	/**
	 * uploadVoice 上传语音接口
	 */
	public static final String UPLOAD_VOICE ="uploadVoice";
	/**
	 * downloadVoice 下载语音
	 */
	public static final String DOWNLOAD_VOICE="downloadVoice";
	/**
	 * translateVoice 识别音频并返回识别结果接口
	 */
	public static final String TRANSLATE_VOICE ="translateVoice";
	/**
	 * getNetworkType 获取网络状态接口
	 */
	public static final String GET_NETWORK_TYPE ="getNetworkType";
	/**
	 * openLocation 使用微信内置地图查看位置接口
	 */
	public static final String OPEN_LOCATION ="openLocation";
	/**
	 * getLocation  获取地理位置接口
	 */
	public static final String GET_LOCATION ="getLocation";
	/**
	 * startSearchBeacons 开启查找周边ibeacon设备接口
	 */
	public static final String START_SEARCH_BEACONS ="startSearchBeacons";
	/**
	 * stopSearchBeacons 关闭查找周边ibeacon设备接口
	 */
	public static final String STOP_SEARCH_BEACONS ="stopSearchBeacons";
	/**
	 * onSearchBeacons 监听周边ibeacon设备接口
	 */
	public static final String ON_SEARCH_BEACONS ="onSearchBeacons";
	/**
	 * closeWindow 关闭当前网页窗口接口
	 */
	public static final String CLOSE_WINDOW ="closeWindow";
	/**
	 * hideMenuItems 批量隐藏功能按钮接口
	 */
	public static final String HIDE_MENU_ITEMS ="hideMenuItems";
	/**
	 * showMenuItems 批量显示功能按钮接口
	 */
	public static final String SHOW_MENU_ITEMS ="showMenuItems";
	/**
	 * hideAllNonBaseMenuItem 隐藏所有非基础按钮接口
	 */
	public static final String HIDE_ALL_NON_BASE_MENU_ITEM ="hideAllNonBaseMenuItem";
	/**
	 * showAllNonBaseMenuItem 显示所有功能按钮接口
	 */
	public static final String show_All_Non_Base_Menu_Item ="showAllNonBaseMenuItem";
	/**
	 * scanQRCode 调起微信扫一扫接口
	 */
	public static final String SCAN_QRCODE ="scanQRCode";
	/**
	 * openProductSpecificView 跳转微信商品页接口
	 */
	public static final String OPEN_PRODUCT_SPECIFIC_VIEW ="openProductSpecificView";
	/**
	 * openAddress 共享收货地址接口
	 */
	public static final String OPEN_ADDRESS ="openAddress";
}
