package com.commerce.pedidos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commerce.commons.controllers.CommonController;
import com.commerce.commons.models.entity.Pedidos;
import com.commerce.pedidos.MicroservicioPedidosApplication;
import com.commerce.pedidos.dto.PedidoDTO;
import com.commerce.pedidos.services.PedidoService;
import com.commerce.pedidos.services.PedidoServiceImpl;

import jakarta.validation.Valid;


@CrossOrigin(value = "http://localhost:4200")


@RestController
public class PedidoControllers{

	
	@Autowired
	private PedidoServiceImpl pedidoServiceImpl;

	
	@GetMapping
	public ResponseEntity<List<Pedidos>> getAll(){
		return ResponseEntity.ok(pedidoServiceImpl.listar());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedidos> getById(@PathVariable Long id){
		Optional<Pedidos> optPedido = pedidoServiceImpl.obtenerPorId(id);
		if(optPedido.isPresent()) {
			return ResponseEntity.ok(optPedido.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Pedidos> create(@RequestBody Pedidos pedidos){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoServiceImpl.crear(pedidos));
				
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedidos> update(@RequestBody Pedidos pedidos, @PathVariable Long id){
		Pedidos categoriaDb = pedidoServiceImpl.editar(pedidos, id);
		if(categoriaDb != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDb);
		}else {
			return ResponseEntity.notFound().build();
		}
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pedidos> delete(@PathVariable Long id){
		Pedidos categoriaDb = pedidoServiceImpl.eliminar(id);
		if(categoriaDb != null) {
			return ResponseEntity.status(HttpStatus.OK).body(categoriaDb);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{idCategoria}/{idProducto}")
	public ResponseEntity<Pedidos> addProducto(@PathVariable Long idCategoria, @PathVariable Long idProducto){
		Pedidos optCategoria = pedidoServiceImpl.addProducto(idCategoria, idProducto);
		if(optCategoria == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optCategoria);
	}
	
	
	
	/*
	 * @PostMapping("/pedido-dto") public ResponseEntity<?>
	 * create(@Valid @RequestBody PedidoDTO pedidoDTO, BindingResult result){
	 * if(result.hasErrors()) {
	 * 
	 * return this.validar(result); } return
	 * ResponseEntity.status(HttpStatus.CREATED).body(service.createPedidoDTO(
	 * pedidoDTO));
	 * 
	 * }
	 * 
	 * @PutMapping("/{id}") public ResponseEntity<?> update(@Valid @RequestBody
	 * PedidoDTO pedidoDTO, BindingResult result, @PathVariable Long id){
	 * if(result.hasErrors()) { return this.validar(result); }
	 * 
	 * Pedidos pedidoDb = service.actualizar(pedidoDTO, id); if(pedidoDb != null) {
	 * return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDb);
	 * 
	 * } return ResponseEntity.notFound().build(); }
	 */
	
}
