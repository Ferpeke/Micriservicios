package com.sebastian.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Gen6EurekaServer2024Application {

	public static void main(String[] args) {
		SpringApplication.run(Gen6EurekaServer2024Application.class, args);
	}

}
