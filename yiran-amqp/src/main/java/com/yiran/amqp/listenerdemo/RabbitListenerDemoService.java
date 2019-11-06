package com.yiran.amqp.listenerdemo;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerDemoService {

	/*@RabbitListener(queues="test_pay_duilie")
	public void receive(Map<String,Object> map){
		System.out.println("收到消息:"+map);
	}*/
}
