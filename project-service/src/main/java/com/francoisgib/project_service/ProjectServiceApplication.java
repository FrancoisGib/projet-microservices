package com.francoisgib.project_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class ProjectServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}
}
