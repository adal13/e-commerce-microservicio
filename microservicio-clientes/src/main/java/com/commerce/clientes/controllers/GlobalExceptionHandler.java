package com.commerce.clientes.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityVilolationException(DataIntegrityViolationException e){
		Throwable cause = e.getRootCause();
		if (cause != null && cause.getMessage() != null) {
			return ResponseEntity.badRequest().body("Error de integridad: " + cause.getMessage());
			}
		return ResponseEntity.badRequest().body("Error en la integridad de los datos");
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstrainViolationExceptiion(ConstraintViolationException e){
		return ResponseEntity.badRequest().body("Violacion de restriccion" + e.getMessage());
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException e){
		return ResponseEntity.badRequest().body("Error" + e.getMessage());
	}		
}
