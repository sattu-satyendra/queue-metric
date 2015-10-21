package com.kaarma.test.queue_metric.configuration;

import java.util.Properties;

import org.hibernate.validator.cfg.context.MethodConstraintMappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.codahale.metrics.MetricRegistry;


@Component
public class QueueMetric {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(QueueMetric.class);
	
	@Autowired
	private GaugeService  gaugeService;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private RabbitAdmin rabbitAdmin;
	
	
	@Scheduled(initialDelay=1000, fixedDelay = 5000)
	public void fixedDelayJob(){
		Properties property = rabbitAdmin.getQueueProperties(queue.getName());
		int size =  (int) property.get(RabbitAdmin.QUEUE_MESSAGE_COUNT);
		
		LOGGER.info(rabbitAdmin.getQueueProperties(queue.getName()).toString());
		gaugeService.submit("queue.size", size);
		
	}
	
	
	
	

}
