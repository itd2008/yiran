package com.yiran.amqp.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.yiran.amqp.constants.AmqpConstants;
import com.yiran.amqp.domain.AmqoRequrst;
import com.yiran.amqp.domain.MqMessageLog;
import com.yiran.amqp.service.IAmqpService;
import com.yiran.amqp.service.IMqMessageLogService;
import com.yiran.common.base.ResultWrapper;
import com.yiran.common.utils.DateUtils;

import cn.hutool.json.JSONUtil;

@Service
public class AmqpServiceImpl implements IAmqpService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private IMqMessageLogService mqMessageLogService;
	
	//回调函数: confirm确认
    final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            String messageId = correlationData.getId();
            if(ack){
               //如果confirm返回成功 则进行更新
               mqMessageLogService.changeMQMessageLogStatus(messageId, AmqpConstants.RABBITMQ_SEND_SUCCESS, new Date());
            } else {
            	MqMessageLog messageLog = mqMessageLogService.selectMqMessageLogById(messageId);
            	messageLog.setStatus("2");
            	mqMessageLogService.updateMqMessageLog(messageLog);
                //失败则进行具体的后续操作:重试 或者补偿等手段
                System.err.println("异常处理...");
            }
        }
    };
	@Override
	public ResultWrapper<Map<String, Object>> sendMessage(AmqoRequrst requrst) {
		try {
			if(requrst.isSave()){
				
				//根据messageId获取对象是否已经存在
				MqMessageLog messageLog = mqMessageLogService.selectMqMessageLogById(requrst.getMessageId());
				if(messageLog !=null){//消息已经存在，重发的消息
					Map<String, Object> dataMap = JSON.parseObject(messageLog.getMessage(),Map.class); ;
					messageLog.setStatus("0");
					messageLog.setTryCount(messageLog.getTryCount()+1);
					mqMessageLogService.updateMqMessageLog(messageLog);
					//消息唯一ID
			        CorrelationData correlationData = new CorrelationData(requrst.getMessageId());
			        rabbitTemplate.setConfirmCallback(confirmCallback);
					rabbitTemplate.convertAndSend(messageLog.getExchange(), messageLog.getRoutingKey(), dataMap,correlationData);
				}else{//新消息
					//保存到数据库
					MqMessageLog mqMessageLog = new MqMessageLog();
					// 消息唯一ID
					mqMessageLog.setMessageId(requrst.getMessageId());
			        // 保存消息整体 转为JSON 格式存储入库
					mqMessageLog.setMessage(JSONUtil.toJsonStr(requrst.getMap()));
			        // 设置消息状态为0 表示发送中
					mqMessageLog.setStatus("0");
			        // 设置消息未确认超时时间窗口为 一分钟 
					mqMessageLog.setNextRetry(DateUtils.addMinutes(new Date(), AmqpConstants.RABBITMQ_TIMEOUT));
					mqMessageLog.setCreateTime(new Date());
					mqMessageLog.setUpdateTime(new Date());
					mqMessageLog.setExchange(requrst.getExchange());
					mqMessageLog.setRoutingKey(requrst.getRoutingKey());
					mqMessageLogService.insertMqMessageLog(mqMessageLog);
			        //消息唯一ID
			        CorrelationData correlationData = new CorrelationData(requrst.getMessageId());
			        rabbitTemplate.setConfirmCallback(confirmCallback);
					rabbitTemplate.convertAndSend(requrst.getExchange(), requrst.getRoutingKey(), requrst.getMap(),correlationData);
				}
				
				return ResultWrapper.ok();
			}else{
				rabbitTemplate.convertAndSend(requrst.getExchange(), requrst.getRoutingKey(), requrst.getMap());
				return ResultWrapper.ok();
			}
			
			
		} catch (AmqpException e) {
			return ResultWrapper.error(e);
		}
	}

	@Override
	public ResultWrapper<Map<String, Object>> receive(AmqoRequrst requrst) {
		try {
			Object obj = rabbitTemplate.receiveAndConvert(requrst.getQueueName());
			return ResultWrapper.ok().putData(obj);
		} catch (AmqpException e) {
			return ResultWrapper.error(e);
		}
	}

	
}
