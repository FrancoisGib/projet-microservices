package com.francoisgib;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RabbitMQConsumer {
	private static final String QUEUE_NAME = "logs_queue";

	private static final String EXCHANGE_NAME = "logs";

	private static final String ROUTING_KEY = "routing_key";

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public void consumeMessages() throws Exception {
		Connection connection = RabbitMQConnection.createConnection();
		Channel channel = connection.createChannel();
		//channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		try {
			channel.exchangeDeclarePassive(EXCHANGE_NAME);
		} catch (IOException e) {
			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
		}
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
			logger.info(message);
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
	}
}