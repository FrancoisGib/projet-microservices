package com.francoisgib;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnection {
	private static final String HOST = "localhost";
	private static final int PORT = 5672;
	private static final String USERNAME = "guest";
	private static final String PASSWORD = "guest";

	public static Connection createConnection() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);
		return factory.newConnection();
	}
}