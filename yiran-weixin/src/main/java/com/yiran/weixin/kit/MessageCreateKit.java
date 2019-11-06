package com.yiran.weixin.kit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiran.weixin.response.Article;
import com.yiran.weixin.response.NewsMessageResponse;
import com.yiran.weixin.utils.MessageUtil;

public class MessageCreateKit {
	private static final Logger logger = LoggerFactory.getLogger(MessageCreateKit.class);
	public static Map<String, Object> createTextMsg(Map<String, String> msgMap,
			String content) {
		Map<String,Object > tm = new HashMap<String,Object>();
		tm.put("ToUserName", msgMap.get("FromUserName"));
		tm.put("FromUserName", msgMap.get("ToUserName"));
		tm.put("CreateTime", System.currentTimeMillis()+"");
		tm.put("MsgType", "text");
		tm.put("Content", content);
		return tm;
	}
	
	
	public static Map<String, Object> createImgeMsg(Map<String, String> msgMap,
			String picUrl) {
		Map<String,Object > tm = new HashMap<String,Object>();
		tm.put("ToUserName", msgMap.get("FromUserName"));
		tm.put("FromUserName", msgMap.get("ToUserName"));
		tm.put("CreateTime", System.currentTimeMillis()+"");
		tm.put("MsgType", "image");
		tm.put("PicUrl", picUrl);
		return tm;
	}
	
	public static String createImage(Map<String, String> msgMap){

		// 创建图文消息
		NewsMessageResponse newsMessage = new NewsMessageResponse();
		newsMessage.setToUserName(msgMap.get("FromUserName"));
		newsMessage.setFromUserName(msgMap.get("ToUserName"));
		newsMessage.setCreateTime(System.currentTimeMillis());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("");
		article.setDescription("");
		article.setPicUrl("http://www.51javaeng.cn/jeesite/uploadFiles/twoDimensionCode/4904545695094192beac3ac3662a3467.png");
		article.setUrl("");
		articleList.add(article);
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		String respMessage =MessageUtil.newsMessageToXml(newsMessage);
		logger.info("------>>>>将图文消息对象转换成xml字符串:"+respMessage);
		return respMessage;
		
	}
	
	

}
