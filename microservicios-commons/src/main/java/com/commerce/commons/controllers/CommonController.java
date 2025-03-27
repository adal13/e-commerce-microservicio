package com.commerce.commons.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.commerce.commons.services.CommonService;

import jakarta.validation.Valid;


public class CommonController<E, S extends CommonService<E>> {
	@Autowired
	protected S service;
	
	
	
	public CommonController(S service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<E>> getAll(){
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<E> getById(@PathVariable Long id){
		Optional<E> entity = service.obtenerPorId(id);
		if(entity.isPresent()) {
			return ResponseEntity.ok(entity.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody E entity, BindingResult result){
		if(result.hasErrors()) {
			return this.validar(result);
		}
		E entityDb = service.crear(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<E> entity = service.eliminarPorId(id);
		if(entity.isPresent()) {
			return ResponseEntity.ok().body(entity.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	protected ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err->{
			errores.put(err.getField(), err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
}
