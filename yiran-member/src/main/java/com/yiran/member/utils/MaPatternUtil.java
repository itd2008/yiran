package com.yiran.member.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yiran.common.utils.StringUtils;


public class MaPatternUtil {
	private static String defaultReplacement="*";//掩码
	private static String emailReplacement="******";//掩码

	
	
	
	/**
	 * 根据正则表达式计算得到掩码
	 * @param filedValue 明文
	 * @param regex		 表达式	
	 * @return
	 */
	public static String getFiledMask(String filedValue,String regex){
		if(StringUtils.isBlank(filedValue) ){
			return null;
		}
		if(StringUtils.isBlank(regex)){
			return filedValue;
		}
		Pattern p = Pattern.compile(regex); // 正则表达式 
		Matcher m = p.matcher(filedValue); // 操作的字符串
		if(m.find()){
			filedValue=getMatcherMask(m,filedValue,null);
		}
		return filedValue;
	}
	
	/**
	 * 替换匹配的值为传入的值
	 * @param m
	 * @param filedValue
	 * @param defaultReplaceValue
	 * @return
	 */
	private static String getMatcherMask(Matcher m,String filedValue,String defaultReplaceValue){
		StringBuffer sf=new StringBuffer();
		
			for(int i = 1; i <= m.groupCount(); i++){
				if(StringUtils.isBlank(defaultReplaceValue)){
					defaultReplaceValue=getReplacement(m.group(i).length(),defaultReplacement);
				}
				if(m.start(i) ==0){
					 sf.append(defaultReplaceValue);
					 if(filedValue.length()>m.end(i)){
						 sf.append(filedValue.substring(m.end(i), filedValue.length()));
					 }
				}else{
					 sf.append(filedValue.substring(0,m.start(i)));
					 sf.append(defaultReplaceValue);
					 if(filedValue.length()>m.end(i)){
						 sf.append(filedValue.substring(m.end(i), filedValue.length()));
					 }
				}
				 if(null!=sf && sf.length()>0){
					 filedValue=sf.toString();
				 }
				
//				filedValue = filedValue.replace(m.group(i), getReplacement(m.group(i).length(),defaultReplacement));
			}
		
		return filedValue;
	}
	
	/**
	 * 对email进行掩码，特殊处理
	 * @param email
	 * @param regex
	 * @return
	 */
	public static String getEmailMask(String email,String regex){
		if(StringUtils.isBlank(email) ){
			return null;
		}
		if(StringUtils.isBlank(regex)){
			return email;
		}
		Pattern p = Pattern.compile(regex); // 正则表达式 
		Matcher m = p.matcher(email); // 操作的字符串	
		if(m.find()){
//				email = email.replace(m.group(i), emailReplacement);
				email=getMatcherMask(m,email,emailReplacement);
		}else{
			 p = Pattern.compile("@"); // 正则表达式 
			 m = p.matcher(email); // 操作的字符串
			 StringBuffer sf=new StringBuffer();
			 if(m.find()){
				 if(m.start()==1){
					 sf.append(emailReplacement);
					 sf.append(email.substring(m.end()-1, email.length()));
				 }
				 if(m.start()==2){
					 sf.append(email.substring(0, 1));
					 sf.append(emailReplacement);
					 sf.append(email.substring(m.end()-2, email.length()));

				 }
			 }
			 if(null!=sf && sf.length()>0){
				 email=sf.toString();
			 }
		}
		return email;
	}
	/**
	 * 根据需要掩码的情况设置掩码，适用于固定长度的字段
	 * @param value 明文
	 * @param start 掩码起始位置
	 * @param end	掩码结束位置
	 * @return
	 */
	public static String getFiledMask(String value,int start,int end){
		return getFiledMask( value,start , end,defaultReplacement);
	}
	
	/**
	 * 根据需要掩码的情况设置掩码，适用于固定长度的字段
	 * @param value 明文
	 * @param start 掩码起始位置
	 * @param end	掩码结束位置
	 * @param replacement	掩码
	 * @return
	 */
	public static String getFiledMask(String value,int start,int end,String replacement){
		StringBuffer sf=new StringBuffer();
		if(start!=0){
			sf.append(value.substring(0, start));
		}
		
		for(int i=0;i<end-start;i++ ){
			sf.append(replacement);
		}
		if(value.length()>end){
			sf.append(value.substring(end, value.length()));
		}
		return sf.toString();
	}	
	
	public static String getFiledMaskByDisplay(String value,int start,int end,String replacement){
		StringBuffer sf=new StringBuffer();
		if(start!=0){
			for(int i=0;i<start;i++){
				sf.append(replacement);
			}
		}
		sf.append(value.substring(start, end));
		
		if(value.length()>end){
			for(int i=0;i<value.length()-end;i++){
				sf.append(replacement);
			}
		}
		return sf.toString();
	}	
	public static String getReplacement(int length,String replacement){
		StringBuffer sf=new StringBuffer();
		for(int i=0;i<length;i++){
			sf.append(replacement);
		}
		return sf.toString();

	}
	
	public  static void main(String[] args){
		
		
		
		String text = "888888888888";
		System.out.println(" 手机号码 :"+MaPatternUtil.getFiledMask(text, "[0-9]{2}([0-9]*)[0-9]{2}"));
		text = "138888";

		  String text1 = "88888888888832(1)";
	       System.out.println(" 手机号码 :"+MaPatternUtil.getFiledMask(text1, "[0-9]{1}([0-9]*)[0-9()]{3}"));
	       text = "!@@#中a13d中文ds%$#@@!af(@##%%#@中)#@#";
	       System.out.println(MaPatternUtil.getFiledMask(text, "[\\S]{1}([\\S]*)[\\S]{3}"));

	        
		Pattern p = Pattern.compile("1[0-9]{1}([0-9]*)[0-9]{2}"); // 正则表达式 
		 Matcher m = p.matcher(text); // 操作的字符串
			StringBuffer sf =new StringBuffer();

			if(m.find()){
				for(int i = 1; i <= m.groupCount(); i++){
					if(m.start(i) ==0){
						 sf.append(getReplacement(m.group(i).length(),defaultReplacement));
						 if(text.length()>m.end(i)){
							 sf.append(text.substring(m.end(i), text.length()));
						 }
					}else{
						 sf.append(text.substring(0,m.start(i)));
						 sf.append(getReplacement(m.group(i).length(),defaultReplacement));
						 if(text.length()>m.end(i)){
							 sf.append(text.substring(m.end(i), text.length()));
						 }
					}
					text =sf.toString();

					
					
					System.out.println(m.start(i) +"  "+m.end(i)+"  "+text.split(m.group(i)).length);
					
//					if(text.split(m.group(i)).length>1){
//						StringBuffer sf =new StringBuffer();
//						 if(m.start(i)==0){
//							 sf.append(getReplacement(m.group(i).length(),defaultReplacement));
//							
//							 if(text.length()>m.end(i))
//								 sf.append(text.substring(m.end(i), text.length()));
//						 }else{
//							 sf.append(text.substring(0,m.start(i)));
//							 sf.append(getReplacement(m.group(i).length(),defaultReplacement));
//							 if(text.length()>m.end(i))
//								 sf.append(text.substring(m.end(i), text.length()));
//						 }
//						text =sf.toString();
//
//					}else{
//						text =text.replace(m.group(i), getReplacement(m.group(i).length(),defaultReplacement));
//					}
				}
			}
		
		System.out.println(" 手机号码 :"+text);
		
		text = "6222766699343453495347";
		//真实姓名
		System.out.println(" 银行卡 :"+MaPatternUtil.getFiledMask(text, "([0-9]*)[0-9]{4}"));
		
		 text = "42082119881026253X";
		 p = Pattern.compile("[0-9]{2}([0-9]*)[0-9]{2}"); // 正则表达式 
		 m = p.matcher(text); // 操作的字符串
		
		if(m.find()){
			for(int i = 1; i <= m.groupCount(); i++){
				text = text.replace(m.group(i), MaPatternUtil.getReplacement(m.group(i).length(),"*"));

			}
		}
		System.out.println(text);
		
		
		text = "许依然";
		//真实姓名
		System.out.println(MaPatternUtil.getFiledMask(text, "[\u4E00-\u9FFF]{1}([\u4E00-\u9FFFa-zA-Z]*)"));
		
		//email
		 text = "cnd@163.com";
		 p = Pattern.compile("[0-9a-z.A-Z]([0-9a-z.A-Z]+)[0-9a-z.A-Z]@[0-9a-z.A-Z]+"); // 正则表达式 
		 m = p.matcher(text); // 操作的字符串
		
		if(m.find()){
			System.out.println(" count :"+m.groupCount());
			for(int i = 1; i <= m.groupCount(); i++){
				System.out.println("dd  :"+m.group(i));
				if(StringUtils.isBlank(m.group(i))){
					text = text.replaceFirst(m.group(i), "******");

				}else{
					if(m.group(i).length()<=6){
						text = text.replace(m.group(i),"******");
					}else{
						text = text.replace(m.group(i), MaPatternUtil.getReplacement(m.group(i).length(),"*"));

					}

				}


			}
		}
		System.out.println(text);
		text = "chenfei@163.com";		 
		System.out.println(MaPatternUtil.getEmailMask(text,"[0-9a-z.A-Z]([0-9a-z.A-Z]+)[0-9a-z.A-Z]@[0-9a-z.A-Z]+"));
		text = "c@163.com";		 
		System.out.println(MaPatternUtil.getEmailMask(text,"[0-9a-z.A-Z]([0-9a-z.A-Z]+)[0-9a-z.A-Z]@[0-9a-z.A-Z]+"));
	}
	
	
}
