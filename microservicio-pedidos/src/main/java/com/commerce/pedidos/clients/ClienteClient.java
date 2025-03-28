package com.commerce.pedidos.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.commerce.commons.models.entity.Cliente;

@FeignClient("microservicio-clientes")
public interface ClienteClient {
	  //@GetMapping("/clientes/{id}")
	  //Cliente getClienteById(@PathVariable("id") Long id);
	  
	  @GetMapping("/{id}")
	  Cliente getClienteById(@PathVariable("id") Long id);
}
