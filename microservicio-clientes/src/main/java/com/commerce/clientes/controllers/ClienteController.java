package com.commerce.clientes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.clientes.dto.ClienteDTO;
import com.commerce.clientes.services.ClienteService;
import com.commerce.commons.controllers.CommonController;
import com.commerce.commons.models.entity.Cliente;

import jakarta.validation.Valid;

@RestController
public class ClienteController extends CommonController<Cliente, ClienteService> {
	
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
		if (result.hasErrors()) {
			return this.validar(result);
		}
		
		Cliente clienteDb = service.actualizar(clienteDTO, id);
		
		if (clienteDb != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteDb);
		}
		return ResponseEntity.notFound().build();
	}
	
}
