package com.yiran.quartz.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yiran.amqp.constants.AmqpConstants;
import com.yiran.amqp.domain.AmqoRequrst;
import com.yiran.amqp.domain.MqMessageLog;
import com.yiran.amqp.service.IAmqpService;
import com.yiran.amqp.service.IMqMessageLogService;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
/**
 * 重新发送失败的MQ消息  若果这个消息发送三次还是失败就不在发送
 * @author pandaa
 *
 */
@Component("retryMessageTasker")
public class RetryMessageTasker {
	private static final Logger logger = LoggerFactory.getLogger(RetryMessageTasker.class);
	@Autowired
	private IMqMessageLogService mqMessageLogService;
	@Autowired
	private IAmqpService amqpService;
	
	public void reSend(){
		 logger.debug(">>>>>>执行重新发送失败的MQ消息 定时任务开始<<<<<<");
		 List<MqMessageLog> list = mqMessageLogService.query4StatusAndTimeoutMessage();
		 logger.debug("查询发送失败的MQ消息数量:【{}】,数据:【{}】",list.size(),JSON.toJSONString(list));
		 list.forEach(messageLog -> {
	            if(messageLog.getTryCount() >= 3){
	                //update fail message 
	            	mqMessageLogService.changeMQMessageLogStatus(messageLog.getMessageId(), AmqpConstants.RABBITMQ_SEND_FAILURE, new Date());
	            } else {
	                // resend 
	            	//mqMessageLogService.update4ReSend(messageLog.getMessageId(),  new Date());
	                
	                try {
	                	AmqoRequrst requrst = new AmqoRequrst();
	                	requrst.setMessageId(messageLog.getMessageId());
	                	requrst.setExchange(messageLog.getExchange());
	                	requrst.setRoutingKey(messageLog.getRoutingKey());
	                	requrst.setSave(true);
	                	Map<String,Object> mapData = JSON.parseObject(messageLog.getMessage(),Map.class); 
	                	requrst.setMap(mapData);
	                	amqpService.sendMessage(requrst);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    System.err.println("-----------异常处理-----------");
	                }
	            }            
	        });
		 logger.debug(">>>>>>执行重新发送失败的MQ消息 定时任务结束<<<<<<");
	}

}
