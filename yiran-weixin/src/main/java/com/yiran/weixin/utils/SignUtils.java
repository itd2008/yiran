package com.yiran.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.yiran.weixin.entity.WeixinCardExt;
/**
 * 签名工具类
 * @author pandaa
 *
 */
public class SignUtils {
	/**
     * 生成待签名串
     * @param paramMap
     * @return
     * @author 
     */
    public static String genSignData(JSONObject jsonObject)
    {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if ("sign".equals(key))
            {
                continue;
            }
            String value = jsonObject.getString(key);
            // 空串不参与签名
            if (isnull(value))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }
    
    public static String getWeiXinCardData(Object obj){
    	WeixinCardExt wc =(WeixinCardExt)obj;
    	List<String> list = new ArrayList<>();
		list.add(wc.getApi_ticket());
		list.add(wc.getTimestamp());
		list.add(wc.getNonce_str());
		list.add(wc.getCard_id());
		list.add(wc.getCode());
		list.add(wc.getOpenid());
		//Collections.sort(list);
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sBuffer.append(list.get(i));
		}
		return sBuffer.toString();
    }
    
    /**
     * str空判断
     * @param str
     * @return
     * @author guoyx
     */
    public static boolean isnull(String str)
    {
        if (null == str || str.equalsIgnoreCase("null") || str.equals(""))
        {
            return true;
        } else
            return false;
    }
    /**
     * 获取加密原数据
     * @param jsapi_ticket
     * @param url
     * @return
     */
    public static Map<String,String> getJsapiSignDate(String jsapi_ticket, String url){
    	 Map<String, String> ret = new HashMap<String, String>();
    	String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("string1:"+string1);
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signDate", string1);
        return ret;
    }
    
    public static Map<String, String> sign(String signDate) {
        Map<String, String> ret = new HashMap<String, String>();
        String signature = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(signDate.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    public static void main(String[] args) {
		System.out.println(create_nonce_str().replaceAll("-", "").length());
	}
}
