package com.yiran.api.mock;

import com.yiran.payorder.domain.ChannelFundResult;

/**
 * mock数据结果
 * @author Administrator
 *
 */
public class MockResultData {
	
	public static ChannelFundResult mockResule(ChannelFundResult result){
		//获取随机数
		int code = MathRandomUtil.PercentageRandom();
		if(code == 0){//成功
			result.setSuccess(true);
			result.setApiResultCode("0000");
			result.setApiResultSubCode("SUCCESS");
			result.setApiResultMessage("注意：当前为mock数据！：交易成功");
			result.setResultMessage("注意：当前为mock数据！：交易成功");
			result.setApiResultSubMessage("注意：当前为mock数据！：交易成功,代扣成功");
		}else if(code == 1){//余额不足
			result.setSuccess(false);
			result.setApiResultCode("6001");
			result.setApiResultSubCode("6001");
			result.setApiResultMessage("注意：当前为mock数据！：交易失败,余额不足");
			result.setResultMessage("注意：当前为mock数据！：交易失败,余额不足");
			result.setApiResultSubMessage("注意：当前为mock数据！：交易失败,余额不足");
		}else if(code == 2){//
			result.setSuccess(false);
			result.setApiResultCode("0000");
			result.setApiResultSubCode("PROCESSING");
			result.setApiResultMessage("注意：当前为mock数据！：银行处理中");
			result.setResultMessage("注意：当前为mock数据！：银行处理中");
			result.setApiResultSubMessage("注意：当前为mock数据！：银行处理中");
		}else if(code == 3){
			result.setSuccess(false);
			result.setApiResultCode("1103");
			result.setApiResultSubCode("1103");
			result.setApiResultMessage("注意：当前为mock数据！：您的卡已过期或者您输入的有效期不正确");
			result.setResultMessage("注意：当前为mock数据！：您的卡已过期或者您输入的有效期不正确");
			result.setApiResultSubMessage("注意：当前为mock数据！：您的卡已过期或者您输入的有效期不正确");
		}else if(code == 4){
			result.setSuccess(false);
			result.setApiResultCode("1104");
			result.setApiResultSubCode("1104");
			result.setApiResultMessage("注意：当前为mock数据！：卡密码错误，请重试");
			result.setResultMessage("注意：当前为mock数据！：卡密码错误，请重试");
			result.setApiResultSubMessage("注意：当前为mock数据！：交易失败,账户未加办代收付标志");
		}else if(code == 5){
			result.setSuccess(false);
			result.setApiResultCode("1114");
			result.setApiResultSubCode("1114");
			result.setApiResultMessage("注意：当前为mock数据！：手机号有误");
			result.setResultMessage("注意：当前为mock数据！：手机号有误");
			result.setApiResultSubMessage("注意：当前为mock数据！：手机号有误");
		}else if(code == -1){
			result.setSuccess(false);
			result.setApiResultCode("9911");
			result.setApiResultSubCode("9911");
			result.setApiResultMessage("注意：当前为mock数据！：金额超过指定额度");
			result.setResultMessage("注意：当前为mock数据！：金额超过指定额度");
			result.setApiResultSubMessage("注意：当前为mock数据！：金额超过指定额度");
		}
		return result;
	}

	public static void main(String[] args) {
		ChannelFundResult result=new ChannelFundResult();
		MockResultData.mockResule(result);
		System.out.println(result.getApiResultCode());
		System.out.println(result.getApiResultSubCode());
		System.out.println(result.getResultMessage());
		System.out.println(result.isSuccess());
		
	}
}
