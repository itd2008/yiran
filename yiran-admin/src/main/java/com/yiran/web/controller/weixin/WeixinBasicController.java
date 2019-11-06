package com.yiran.web.controller.weixin;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yiran.framework.web.base.BaseController;
import com.yiran.weixin.kit.BasicKit;
import com.yiran.weixin.kit.SecurityKit;
import com.yiran.weixin.kit.WeixinMessageKit;
import com.yiran.weixin.servlet.WeixinContext;


@Controller
@RequestMapping(value = "/public/weixin")
public class WeixinBasicController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(WeixinBasicController.class);
	@RequestMapping(value="/wreceive",method=RequestMethod.GET)
	public void handlerGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println("微信token验证开始..............");
		logger.info("------------------------>>>>>>>Token:"+WeixinContext.getInstance().getToken());
		String signature = StringUtils.defaultIfEmpty(req.getParameter("signature"), "");
		logger.info("------------------------>>>>>>>signature:"+signature);
		String timestamp = StringUtils.defaultIfEmpty(req.getParameter("timestamp"), "");
		logger.info("------------------------>>>>>>>timestamp:"+timestamp);
		String nonce = StringUtils.defaultIfEmpty(req.getParameter("nonce"), "");
		logger.info("------------------------>>>>>>>nonce:"+nonce);
		String echostr = StringUtils.defaultIfEmpty(req.getParameter("echostr"), "");
		logger.info("------------------------>>>>>>>echostr:"+echostr);
		String[] arrs = {WeixinContext.getInstance().getToken(),nonce,timestamp};
		Arrays.sort(arrs);
		StringBuffer sb = new StringBuffer();
		for(String a:arrs) {
			sb.append(a);
		}
		String sha1 = SecurityKit.sha1(sb.toString());
		logger.info("加密后的数据:"+sha1.equals(signature));
		if(sha1.equals(signature)) {
			resp.getWriter().println(echostr);
		}
		System.out.println("微信token验证结束..............");
	}
	
	@RequestMapping(value="/wreceive",method=RequestMethod.POST)
	public void handlerPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/xml;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		logger.info("接收的消息："+req.toString());
		String rel = WeixinMessageKit.handlerReceiveMsg(req);
		logger.info("--------rel:"+rel);
		if(!BasicKit.isEmpty(rel)) {
			resp.getWriter().write(rel);
		}
	}
	
	
	
}
