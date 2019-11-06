package com.yiran.weixin.kit;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.yiran.weixin.servlet.BeanFactoryContext;
import com.yiran.weixin.servlet.WeixinContext;
import com.yiran.weixin.utils.WeiXinJsonUtil;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.mapper.WeixinSettingMapper;

public class WeixinBasicKit {
	private static final Logger logger = LoggerFactory.getLogger(WeixinBasicKit.class);
	
	@Autowired
	public static void setWeixinContext() {
		WeixinSettingMapper weixinSettingMapper = (WeixinSettingMapper)BeanFactoryContext.getBean("weixinSettingMapper");
		String appId=weixinSettingMapper.getValueByKey(WeixinSetting.KEY_WEIXIN_APPID).getWeixinValue();
		logger.info("微信公众号appId:"+appId);
		WeixinContext.getInstance().setAppId(appId);
		String appSecurt = weixinSettingMapper.getValueByKey(WeixinSetting.KEY_WEIXIN_APPSECRET).getWeixinValue();
		logger.info("微信公众号appSecurt:"+appSecurt);
		WeixinContext.getInstance().setAppSecurt(appSecurt);
		String baseUrl = weixinSettingMapper.getValueByKey(WeixinSetting.KEY_WEIXIN_BASE_URL).getWeixinValue();
		logger.info("微信公众号baseUrl:"+baseUrl);
		WeixinContext.getInstance().setBaseUrl(baseUrl);
		String token = weixinSettingMapper.getValueByKey(WeixinSetting.KEY_WEIXIN_WEIXIN_TOKEN).getWeixinValue();
		logger.info("微信公众号token:"+token);
		WeixinContext.getInstance().setToken(token);
		
	}
	
	public static String replaceAccessTokenUrl(String url) {
		String token = WeixinContext.getInstance().getAccessToken().getAccess_token();
		logger.info("replaceAccessTokenUrl:"+token);
		return url.replace("ACCESS_TOKEN",token );
	}
	
	/**
	 * 检查请求是否成功
	 * @return
	 */
	public static boolean checkRequestSucc(String content) {
		try {
			JsonNode jn = WeiXinJsonUtil.getMapper().readTree(content);
			if(!jn.has("errcode")) return true;
			if(jn.get("errcode").asInt()==0) return true;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int getRequestCode(String content) {
		try {
			JsonNode jn = WeiXinJsonUtil.getMapper().readTree(content);
			if(jn.has("errcode")) return jn.get("errcode").asInt();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static String getRequestMsg(String content) {
		try {
			JsonNode jn = WeiXinJsonUtil.getMapper().readTree(content);
			if(jn.has("errcode")) return jn.get("errmsg").asText();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String sendGet(String url) {
		HttpGet get = null;
		CloseableHttpResponse resp = null;
		CloseableHttpClient client = null;
		try {
			client = HttpClients.createDefault();
			get = new HttpGet(url);
			resp = client.execute(get);
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode>=200&&statusCode<300) {
				HttpEntity entity = resp.getEntity();
				String content = EntityUtils.toString(entity,"utf-8");
				return content;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resp!=null) resp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(client!=null) client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String sendJsonPost(String url,String content) {
		return sendPost(url, content, "application/json");
	}
	
	public static String sendXmlPost(String url,String content) {
		return sendPost(url, content, "application/xml");
	}
	
	public static String sendPost(String url,String content,String type) {
		CloseableHttpClient client = null;
		CloseableHttpResponse resp = null;
		try {
			client = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			post.addHeader("Content-type",type);
			StringEntity entity = new StringEntity(content, ContentType.create(type, "UTF-8"));
			post.setEntity(entity);
			resp = client.execute(post);
			int statusCode = resp.getStatusLine().getStatusCode();
			if(statusCode>=200&&statusCode<300) {
				String str = EntityUtils.toString(resp.getEntity(),"utf-8");
				return str;
			}
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(client!=null) client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if(resp!=null) resp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
