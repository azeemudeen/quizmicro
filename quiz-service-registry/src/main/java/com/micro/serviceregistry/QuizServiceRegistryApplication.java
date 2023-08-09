package com.micro.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class QuizServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceRegistryApplication.class, args);
	}

}
