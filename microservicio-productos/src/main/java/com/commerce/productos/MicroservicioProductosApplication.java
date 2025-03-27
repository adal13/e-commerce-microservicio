package com.commerce.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EntityScan("com.commerce.commons.models.entity")
public class MicroservicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioProductosApplication.class, args);
	}

}
