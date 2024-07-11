package com.francoisgib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		RabbitMQConsumer consumer = new RabbitMQConsumer();
		try {
			consumer.consumeMessages();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}
}