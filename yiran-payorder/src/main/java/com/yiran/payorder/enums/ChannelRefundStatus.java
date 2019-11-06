package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 *
 *
 * <p>网银充退渠道返回的充退状态</p>
 */
public enum ChannelRefundStatus {
	RefundAccept("0000","接收成功"),
	RefundCheckPass("0100","验证成功"),
	RefundBankAccept("0200","银行接收成功"),
	RefundSubmitException("0300","提交银行异常"),
	RefundSuccess("0400"," 银行处理成功"),
	RefundFailed("0500","银行处理失败"),
	RefundManualAccept("1000","人工退单处理，接收成功"),
	RefundManualCheckPass("1100","人工退单处理，已受理"),
	RefundManualSuccess("1400","人工退单处理，处理完成，成功"),
	RefundManualFailed("1500","人工退单处理，处理完成，失败"),
	RefundManualCanceled("1600","人工退单处理，处理完成，取消"),
	SystemError("-9999","系统错误");

	   /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    ChannelRefundStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static ChannelRefundStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (ChannelRefundStatus type : ChannelRefundStatus.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
