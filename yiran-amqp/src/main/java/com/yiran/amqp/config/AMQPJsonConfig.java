package com.yiran.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPJsonConfig {
	
	@Bean
	public MessageConverter mssageConverter(){
		
		return new Jackson2JsonMessageConverter();
	}

}
