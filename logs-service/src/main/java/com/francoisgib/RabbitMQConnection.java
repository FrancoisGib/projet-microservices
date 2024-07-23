package com.francoisgib;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnection {
	private static final int PORT = 5672;
	private static final String USERNAME = "guest";
	private static final String PASSWORD = "guest";

	public static Connection createConnection() throws Exception {
		String host = System.getenv("RABBITMQ_HOST");
		if (host == null) {
			host = "localhost";
		}
		System.out.println("RABBITMQ_HOST: " + host);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(PORT);
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);
		return factory.newConnection();
	}
}