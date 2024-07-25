package com.francoisgib.dockerservice;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DockerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerServiceApplication.class, args);
    }

    @Bean
    DockerClient dockerClient() {
        return DockerClientBuilder.getInstance(
                        DefaultDockerClientConfig.createDefaultConfigBuilder()
                                .withDockerHost("tcp://docker-dind:2375")
                                .build())
                .build();
    }
}
