package com.commerce.clientes.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.commerce.commons.models.entity.Productos;

@FeignClient(name = "microservicio-productos")
public interface PediProductoClient {

	 @GetMapping("/productos/pedido/{pedidoId}")
	    List<Productos> getProductosByPedido(@PathVariable Long pedidoId);
}
