package com.commerce.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.commerce.commons.models.entity"})
public class MicroservicioClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioClientesApplication.class, args);
		System.out.print("-----------------------si entra-------");
	}

}
