package com.yiran.api.response;

import java.io.Serializable;
/**
 *  银行卡卡Bin查询响应
 * @author Administrator
 *
 */
public class BankBinResponseVo implements Serializable{

	private static final long serialVersionUID = 2107499339179364892L;
	
	//交易结果代码
	private String ret_code;
	
	//交易结果描述  交易成功
	private String ret_msg;
	
	//签名方式
    private String sign_type;
    //签名
    private String sign;
    //所属银行编号
    private String bank_code;
    //所属银行名称
    private String bank_name;
    //银行卡类型
    private String card_type;
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BankBinResponseVo [ret_code=" + ret_code + ", ret_msg="
				+ ret_msg + ", sign_type=" + sign_type + ", sign=" + sign
				+ ", bank_code=" + bank_code + ", bank_name=" + bank_name
				+ ", card_type=" + card_type + "]";
	}
    
    
    

}
