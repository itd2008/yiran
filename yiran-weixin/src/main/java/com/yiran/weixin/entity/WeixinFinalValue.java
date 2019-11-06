package com.yiran.weixin.entity;

/**
 * 微信API
 * @author pandaa
 *
 */
public class WeixinFinalValue {
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	public final static String WX_CARD_API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";
	public final static String MENU_ADD = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public final static String MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	public final static String MSG_TEXT_TYPE = "text";
	public final static String MSG_IMAGE_TYPE = "image";
	public final static String MSG_VOICE_TYPE = "voice";
	public final static String MSG_VIDEO_TYPE = "video";
	public final static String MSG_SHORTVIDEO_TYPE = "shortvideo";
	public final static String MSG_LOCATION_TYPE = "location";
	public final static String MSG_EVENT_TYPE = "event";
	
	public final static String POST_MEDIA="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public final static String GET_MEDIA="https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
	public final static String SEND_TEMPLATE_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	public final static String USER_QUERY = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public final static String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public final static String AUTH_GET_OID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	public final static String ADD_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	public final static String QUERY_ALL_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	public final static String QUERY_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	public final static String UPDATE_GROUP_NAME = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	public final static String MOVE_USER_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	public final static String MOVE_USERS_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
	public final static String DELETE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
	
	public final static String QR_GET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";

	public final static String KF_ADD = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
}
