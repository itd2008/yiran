package com.yiran.paychannel.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * 手工冲退类型
 *
 */
public enum ManualRefundType {
	NON_SUPPORT_AUTO_REFUND("nonSupportAutoRefund", "订单机构不支持接口退款"),
	REFUND_DELAY_DATE("refundDateLimit", "订单退款超期"),
	REFUND_MAX_THAN_AMOUNT_LIMIT("refundAmountLimit", "订单退款超额"),
	REFUND_NON_RETURN_RESULT("refundNonResult","请清算确认是否已退款"),
	AUTO_REFUND_FAILED("autoRefundFailed","直连退款失败"),
	OTHERS("others","其它"),
	;

	public static final String SPLIT_CHARACTER = "|||";
	public static final String SPLIT_CHARACTER_PARASE = "\\|\\|\\|";
	/** 代码 */
	private final String code;
	/** 信息 */
	private final String message;


	ManualRefundType(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 通过代码获取ENUM
	 *
	 * @param code
	 * @return
	 */
	public static ManualRefundType getByCode(String code) {
		if (StringUtil.isBlank(code)) {
			return null;
		}

		String[] strList = code.split(SPLIT_CHARACTER_PARASE);
		if(strList != null && strList.length > 0){
			for (ManualRefundType status : ManualRefundType.values()) {
				if (status.getCode().equalsIgnoreCase(strList[0])) {
					return status;
				}
			}
		}

		return null;
	}
	
	public static String replaceMessage(String message){
		if (StringUtil.isBlank(message)) {
			return message;
		}
		String[] strList = message.split(SPLIT_CHARACTER_PARASE);
		
		if(strList != null && strList.length > 0){
			ManualRefundType replaceCode = getByCode(strList[0]);
			if(replaceCode== null){
				return message;
			}
			String replaceMessage =  message.replaceFirst(strList[0], replaceCode.getMessage());
			return replaceMessage;
		}
		return message;
	}
	
	public static void main(String[] args){
		System.out.print(replaceMessage("autoRefundFailed" + SPLIT_CHARACTER +  "123"));
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}


}
