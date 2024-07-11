package com.francoisgib.project_service.config.rabbit_mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQLogConfig {
	public static final String QUEUE = "logs_queue";

	public static final String EXCHANGE = "logs";

	public static final String ROUTING_KEY = "routing_key";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
}
