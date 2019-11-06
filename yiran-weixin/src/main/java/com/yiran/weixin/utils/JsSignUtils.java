package com.yiran.weixin.utils;

import java.util.UUID;

import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.mapper.WeixinSettingMapper;
import com.yiran.weixin.servlet.BeanFactoryContext;

import java.util.Map;
import java.util.HashMap;
import java.util.Formatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
/**
 * 微信JS-SDK签名工具类
 * @author pandaa
 *
 */
public class JsSignUtils {

    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("string1:"+string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
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
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
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

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    public static Map<String, String> main(String jsapiTicket,String posturl) {
        String jsapi_ticket = jsapiTicket;

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = posturl;
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
           System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        return ret;
    };

    public static void main(String[] args) {
		main("sM4AOVdWfPE4DxkXGEs8VFt_7fn1bRBHZniHMYO2vAUNt5v6qiUKtASpCuxEHVeadLJfANvgXevWhfDxCwgyfw", "http://www.baidu.com");
	}
    
}
