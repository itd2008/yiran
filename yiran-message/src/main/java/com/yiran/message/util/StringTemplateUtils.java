package com.yiran.message.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import cn.hutool.core.util.RandomUtil;

/**
 * String模板工具类
 * @author pandaa
 *
 */
public class StringTemplateUtils {

	public static final String DEF_REGEX="\\$\\{(.+?)\\}";
    
    public static String render(String template, Map<String, String> data) {
        return render(template,data,DEF_REGEX);
    }
     /**
      * 替换模板参数
      * @param template
      * @param data
      * @param regex
      * @return
      */
    public static String render(String template, Map<String, String> data,String regex) {
        if(StringUtils.isBlank(template)){
            return "";
        }
        if(StringUtils.isBlank(regex)){
            return template;
        }
        if(data == null || data.size() == 0){
            return template;
        }
        try {
            StringBuffer sb = new StringBuffer();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(template);
            while (matcher.find()) {
                String name = matcher.group(1);// 键名
                String value = data.get(name);// 键值
                if (value == null) {value = "";}
                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    
    public static void main(String[] args) {
    	String template="尊敬的${userName}用户，您正在登录依然管理系统，您的短信验证码是：${code},请不要泄露给其他人。流水号${msgOrderNo}";
        Map<String, String> data = new HashMap<String, String>();
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
        String msgOrderNo = RandomUtil.randomString(32);
        data.put("userName", "admin");
        data.put("code", verifyCode);
        data.put("msgOrderNo", msgOrderNo);
        System.out.println(render(template,data));
	}
}
