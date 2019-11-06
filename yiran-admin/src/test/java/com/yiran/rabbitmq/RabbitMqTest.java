package com.yiran.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.yiran.amqp.domain.AmqoRequrst;
import com.yiran.amqp.service.IAmqpService;
import com.yiran.base.BaseJunit;

import cn.hutool.core.lang.UUID;

public class RabbitMqTest extends BaseJunit{

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	private IAmqpService amqpService;
	/**
	 * 点对点
	 */
	@Test
	public void test1(){
		//message需要自己构造，可以定制消息体内容和消息头
		//rabbitTemplate.send(exchange, routingKey, message);
		
		//object默认被当成消息体，只需要传入要发送的对象，自动序列化发送给rabbit
		//rabbitTemplate.convertAndSend(exchange, routingKey, object);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "测试监听11");
		Users user = new Users();
		user.setUsername("许盼1");
		user.setPassword("123456");
		map.put("user", user);
		//消息被序列化后发送
		//rabbitTemplate.convertAndSend("exchange.direct", "atguigu", map);
		AmqoRequrst requrst = new AmqoRequrst();
    	requrst.setMessageId(UUID.randomUUID().toString());
    	requrst.setExchange("pay_fundchannel_order_exchanges1");
    	requrst.setRoutingKey("pay_fundchannel_order_route_key");
    	requrst.setSave(true);
    	requrst.setMap(map);
		amqpService.sendMessage(requrst);
		System.out.println("消息发送完毕");
	}
	
	@Test
	public void test2(){
		
		Object obj = rabbitTemplate.receiveAndConvert("pay_fundchannel_queue");
		System.out.println(obj.getClass());
		System.out.println(obj);
		
	}
}
