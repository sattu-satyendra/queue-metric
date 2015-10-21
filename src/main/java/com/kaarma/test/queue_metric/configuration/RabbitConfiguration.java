package com.kaarma.test.queue_metric.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
	
	final static String queueName = "metricqueue";
	
	@Autowired
	ConnectionFactory rabbitConnectionFactory;
	
	
	@Bean
	Queue queue() {
		Map<String, Object> args = new HashMap<String, Object>();
		Queue queue = new Queue(queueName,true,false,false,args);
		return queue;
	}
	
	
	
	@Bean
	RabbitAdmin rabbitAdmin(){
		RabbitAdmin rabbitAdmin=new RabbitAdmin(rabbitConnectionFactory);
		return rabbitAdmin;
	};

	
	

}
