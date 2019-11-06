/**
 * 
 */
package com.yiran.member.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>验证工具类</p>
 */
public class ValidateUtils {

    /**
     * 验证 str 是否一个合法邮箱格式
     * @param str 只要中间包含“@”
     * @return
     */
    public static boolean isMail(String str) {
        if(StringUtils.isBlank(str)){
            return false;
        }
        // TODO 取出临时创建的标识
        str = VerifyDomainUtil.replaceHrySign(str);
        
        String check = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        //String check = "^([a-z0-9A-Z]+[-|._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str.trim());
        boolean isMatched = matcher.matches();
        if (isMatched) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 验证 str 是否一个合法手机格式
     * @param str 以 1 开头 11 位数字
     * @return
     */
    public static boolean isMobile(String str) {
        if(StringUtils.isBlank(str)){
            return false;
        }
        // TODO 取出临时创建的标识
        str = VerifyDomainUtil.replaceHrySign(str);
        //1\\d{10}
         String check = "^1\\d{10}$";
//         String check = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|2|3|5|6|7|8|9])\\d{8}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str.trim());
        boolean isMatched = matcher.matches();
        if (isMatched) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 验证身份证
     * @param idno 15 或 18 位数字，最后一位可以是字母X
     * @return
     */
    public static boolean isIdCard(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }

        str = str.toUpperCase();
        String check15 = "^\\d{14}[\\d|X]$";
        String check18 = "^\\d{17}[\\d|X]$";
        if(str.length()==15){
           return str.matches(check15.trim());
        }else if(str.length()==18){
            return str.matches(check18.trim());
        }else{
            return false;
        }
    }
    
    public static void main(String[] args) {
        String mail = "a@ds";
        String mobile = "11451832528";
        String id = "12345678901234567X";
        
//        System.out.println(ValidateUtils.isMail(mail));
        System.out.println(ValidateUtils.isMobile("132456782"));
//        System.out.println(ValidateUtils.isIdCard(id));
    }
}
