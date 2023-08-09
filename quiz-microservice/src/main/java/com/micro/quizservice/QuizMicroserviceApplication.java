package com.micro.quizservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizMicroserviceApplication.class, args);
	}

}
