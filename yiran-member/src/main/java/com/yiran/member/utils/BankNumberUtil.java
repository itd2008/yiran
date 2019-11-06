package com.yiran.member.utils;

import org.apache.commons.lang3.StringUtils;

public class BankNumberUtil {
	private static int i = 0;
	/**
	 * 需要传入一个前缀：6、8、9中的一个。
	 * 其中：6：类型1，8：类型2，9：类型3 【根据自己的业务定义】
	 * 其他则会返回异常
	 * @param prefix
	 * @return
	 */
	public synchronized static String getBrankNumber(String prefix){
		if(StringUtils.isNotBlank(prefix)){
			if("689".indexOf(prefix)>=0&&prefix.length()==1){
				String st = "622"+prefix+getUnixTime();
				return st+getBankCardCheckCode(st);
			}else{
				System.out.println("银行卡号前缀有误");
			}
		}else{
			System.out.println("银行卡号去前缀不能是空");
		}
		return null;
	}
	
	/***
	 * 获取当前系统时间戳 并截取 
	 * @return
	 */
	private synchronized static String getUnixTime(){
		try {
			Thread.sleep(10);//线程同步执行，休眠10毫秒 防止卡号重复
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i++;i=i>100?i%10:i;
		return ((System.currentTimeMillis()/100)+"").substring(1)+(i%10);
	}
	
	/**
     * 校验银行卡卡号
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
         char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
         if(bit == 'N'){
             return false;
         }
         return cardId.charAt(cardId.length() - 1) == bit;
    }
    
    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;           
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }
    
    public static void main(String[] args) {
		System.out.println(getBrankNumber("9"));
	}
}
