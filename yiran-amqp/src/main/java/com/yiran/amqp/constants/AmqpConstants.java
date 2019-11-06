package com.yiran.amqp.constants;
/**
 * 常量
 * @author pandaa
 *
 */
public final class AmqpConstants {
	/**
	 * MQ消息发送状态
	 */
	public static final String RABBITMQ_SENDING = "0"; //发送中
    
    public static final String RABBITMQ_SEND_SUCCESS = "1"; //成功
     
    public static final String RABBITMQ_SEND_FAILURE = "2"; //失败
     
    public static final int RABBITMQ_TIMEOUT = 1; /*分钟超时单位：min*/

}
