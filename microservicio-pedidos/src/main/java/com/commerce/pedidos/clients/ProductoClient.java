package com.commerce.pedidos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.commerce.commons.models.entity.Productos;


@FeignClient(name = "microservicio-productos")
public interface ProductoClient {
<<<<<<< HEAD
	@GetMapping("/productos/{id}")
	public Productos getProductoById(@PathVariable Long id);

	@PostMapping("/")
    public Productos save(@RequestBody Productos producto);
=======
	@GetMapping("/{id}")
	Productos getProductoById(@PathVariable Long id);
	
	@PostMapping("/")
    public Productos save(@RequestBody Productos producto);
	//public Productos save(Productos producto);
	
	
>>>>>>> adal
}
