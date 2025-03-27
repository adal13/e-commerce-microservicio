package com.commerce.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("com.commerce.commons.models.entity")
@EnableFeignClients
//@ComponentScan(basePackages = {"com.commerce.pedidos", "com.commerce.productos"})
public class MicroservicioPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPedidosApplication.class, args);
	}

}
