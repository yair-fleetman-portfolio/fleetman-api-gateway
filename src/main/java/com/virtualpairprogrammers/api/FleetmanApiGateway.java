package com.virtualpairprogrammers.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCircuitBreaker
@EnableFeignClients
public class FleetmanApiGateway {
	public static void main(String[] args) {
		System.setProperty('server.port', '8080');
		SpringApplication.run(FleetmanApiGateway.class, args);		
	}	
}

