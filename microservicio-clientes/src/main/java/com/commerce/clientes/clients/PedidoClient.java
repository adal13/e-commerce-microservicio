package com.commerce.clientes.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.commerce.commons.models.entity.Pedidos;
import com.commerce.commons.models.entity.Productos;

@FeignClient(name = "microservicio-pedidos")
public interface PedidoClient {
	
	@GetMapping("/producto/{pedidosIds}")
	List<Pedidos> getProductoById(@PathVariable List<Long> pedidosIds);

	@PostMapping("/pedidos")
    public Productos save(@RequestBody Productos producto);

	@GetMapping("/listarProductos/{id}")
	List<Pedidos> getListProductById(Long id);
	

	
	// ✅ Obtener todos los pedidos
    @GetMapping("/pedidos")
    List<Pedidos> getTodosLosPedidos();

    // ✅ Obtener los pedidos de un cliente específico
    @GetMapping("/pedidos/cliente/{clienteId}")
    List<Pedidos> getPedidosByCliente(@PathVariable Long clienteId);

}
