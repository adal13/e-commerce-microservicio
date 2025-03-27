package com.commerce.pedidos.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.commerce.commons.models.entity.Pedidos;
import com.commerce.commons.models.entity.Productos;

@FeignClient(name = "microservicio-pedidos")
public interface PedidoClient {
	 @GetMapping("/pedidos/{clienteId}") // Asegúrate de que esta ruta esté definida correctamente
	    List<Pedidos> getPedidosByClienteId(@PathVariable Long clienteId);

	    @PostMapping("/pedidos")
	    public Productos save(@RequestBody Productos producto);
}
