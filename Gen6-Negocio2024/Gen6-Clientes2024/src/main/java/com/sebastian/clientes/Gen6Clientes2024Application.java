package com.sebastian.clientes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Gen6Clientes2024Application {

	public static void main(String[] args) {
		SpringApplication.run(Gen6Clientes2024Application.class, args);
	}

	@Bean
	@Qualifier("clienteWebFlux")
	@LoadBalanced
	public WebClient.Builder crearInstancia() {
		return WebClient.builder();
	}

}
