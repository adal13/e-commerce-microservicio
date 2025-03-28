package com.commerce.clientes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.clientes.clients.PedidoClient;
import com.commerce.clientes.dto.ClienteDTO;
import com.commerce.clientes.models.repositories.ClienteRepository;
import com.commerce.clientes.services.ClienteService;
import com.commerce.commons.controllers.CommonController;
import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.models.entity.Pedidos;

import jakarta.validation.Valid;

@RestController
public class ClienteController extends CommonController<Cliente, ClienteService> {
	
	private final PedidoClient pedidoCliente;
	private final ClienteRepository repository;

	public ClienteController(PedidoClient pedidoCliente, ClienteRepository repository, ClienteService service) {
		super(service);
		this.pedidoCliente = pedidoCliente;
		this.repository = repository;
	}


	
	@PostMapping("/cliente-dto")
	public ResponseEntity<?> create(@Valid @RequestBody ClienteDTO clienteDto, BindingResult result) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(clienteDto));
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ClienteDTO clienteDTO,
			BindingResult result, @PathVariable Long id){
		 // 1. Validar errores del DTO
	    if (result.hasErrors()) {
	        return this.validar(result); // Retorna errores de validación
	    }

	    // 2. Actualizar el cliente
	    Cliente clienteActualizado = service.actualizar(clienteDTO, id);

	    // 3. Manejar respuesta
	    if (clienteActualizado != null) {
	        return ResponseEntity.ok(clienteActualizado); // HTTP 200 OK con el recurso actualizado
	    } else {
	        return ResponseEntity.notFound().build(); // HTTP 404 si no existe el cliente
	    }
	}
	
}
