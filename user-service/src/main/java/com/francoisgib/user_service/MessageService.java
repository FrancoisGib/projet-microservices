package com.francoisgib.user_service;

import com.francoisgib.user_service.config.rabbit_mq.MQLogConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageService {
	private final RabbitTemplate rabbitTemplate;

	public void sendLogMessage(String message) {
		rabbitTemplate.convertAndSend(MQLogConfig.EXCHANGE, MQLogConfig.ROUTING_KEY, "User Service : " + message);
	}
}
