package com.yiran.weixin.kit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WeixinMessageKit {
	private static final Logger logger = LoggerFactory.getLogger(WeixinMessageKit.class);
	public static String handlerReceiveMsg(HttpServletRequest req) throws IOException {
		
		Map<String,String> msgMap = reqMsg2Map(req);
		logger.info("接收的消息转换为map集合:"+msgMap);
		if(!DuplicateMessageKit.checkDuplicate(msgMap)) {
			String rel = DuplicateMessageKit.getRel(msgMap);
			return rel;
		}
		String msgType = msgMap.get("MsgType");
		if("event".equals(msgType.trim())) {
			msgMap.put("clientIp", getClientIpAddress(req));
			String rel = WeixinEventKit.handlerEventMsg(msgMap);
			DuplicateMessageKit.setRel(msgMap, rel);
			return rel;
		} else {
			//用户发送的消息
			return hanlderCommonMsg(msgMap);
		}
		
	}

	private static String hanlderCommonMsg(Map<String, String> msgMap) throws IOException {
		String openid=msgMap.get("FromUserName");
		//TODO:这里需要修改
		String content="欢迎关注公众平台";
		return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap,content));
	}

	@SuppressWarnings("unchecked")
	private static Map<String,String> reqMsg2Map(HttpServletRequest req) throws IOException {
		String xml = req2xml(req);
		System.out.println("....WeixinMessageKit.reqMsg2Map......:" + xml);
		try {
			Map<String,String> maps = new HashMap<String, String>();
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			List<Element> eles = root.elements();
			for(Element e:eles) {
				maps.put(e.getName(), e.getTextTrim());
			}
			return maps;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static String req2xml(HttpServletRequest req) throws IOException {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String str = null;
		StringBuffer sb = new StringBuffer();
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	public static String map2xml(Map<String, Object> map) throws IOException {
		Document d = DocumentHelper.createDocument();
		Element root = d.addElement("xml");
		Set<String> keys = map.keySet();
		for(String key:keys) {
			Object o = map.get(key);
			if(o instanceof String) {
				String t = (String)o;
//				System.out.println(t);
				if(t.indexOf("<a")>=0) {
					root.addElement(key).addCDATA(t); 
				} else {
					root.addElement(key).addText((String)o);
				}
			} else {
				
			}
			
		}
		StringWriter sw = new StringWriter();
		XMLWriter xw = new XMLWriter(sw);
		xw.setEscapeText(false);
		xw.write(d);
		return sw.toString();
	}
	/**
	 * 获取IP
	 * @param request
	 * @return
	 */
	protected static String getClientIpAddress(HttpServletRequest request) {  
        String clientIp = request.getHeader("x-forwarded-for");  
        if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {  
            clientIp = request.getHeader("Proxy-Client-IP");  
        }  
        if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {  
            clientIp = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {  
            clientIp = request.getRemoteAddr();  
        }  
        return clientIp;  
    }  
}
