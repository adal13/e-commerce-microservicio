package com.commerce.pedidos.clients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.commerce.commons.models.entity.Pedidos;

@FeignClient(name = "microservicio-productos")
public interface PedidoClient {
	@GetMapping("/{id}")
	public Optional<Pedidos> getPedidosById(@PathVariable Long id);
}
