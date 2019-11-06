package com.yiran.weixin.service;


/**
 * 发送模板消息
 * @author pandaa
 *
 */
public interface IPushMessageService {
	
	/**
	 * 发送支付成功消息
	 * @param orderMaster
	 */
	public void pushPayMessage(String jsonStr);
	/**
	 * 发送退款成功消息
	 * @param orderMaster
	 */
	public void pushRefundMessage(String jsonStr);
	/**
	 * 发送新订单通知
	 * @param jsonStr
	 */
	public void pushNewOrderNotification(String jsonStr);
	/**
	 * 商品已发出通知
	 * @param jsonStr
	 */
	public void pushCommoditHasBeenNotified(String jsonStr);
	
}
