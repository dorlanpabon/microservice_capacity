package com.pragma.powerup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;


@SpringBootApplication
@EnableReactiveFeignClients(basePackages = "com.pragma.powerup.infrastructure.output.http.client")
public class PowerUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerUpApplication.class, args);
	}

}
