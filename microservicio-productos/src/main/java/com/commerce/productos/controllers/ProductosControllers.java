package com.commerce.productos.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;  
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.commerce.commons.controllers.CommonController;
import com.commerce.commons.models.entity.Productos;
import com.commerce.productos.dto.ProductosDTO;



import com.commerce.productos.services.ProductosService;
import com.commerce.productos.services.ProductosServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;



@RestController
public class ProductosControllers extends CommonController<Productos, ProductosService> {
	
	public ProductosControllers(ProductosService service) {
		super(service);
	}

	private ProductosServiceImpl serviceImpl;


	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ProductosDTO productosDTO, BindingResult result, @PathVariable Long id){
		if(result.hasErrors()) {
			return this.validar(result);
		}

		Productos productosDb = service.actualizar(productosDTO, id);
		if(productosDb != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(productosDb);

		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/productos-dto")
	public ResponseEntity<?> create(@Valid @RequestBody ProductosDTO productosDTO, BindingResult result){
		if(result.hasErrors()) {
			
			return this.validar(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createProducto(productosDTO));
		
	}
	
	/*@DeleteMapping("/{id}")
	public ResponseEntity<Productos> delete(@PathVariable Long id){
		serviceImpl.deleteProductos(id);
		return ResponseEntity.ok().build();
	}*/
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<?> delete(@PathVariable Long id) {
	        Productos deletedProducto = service.deleteProductos(id);
	        if (deletedProducto != null) {
	            return ResponseEntity.ok(deletedProducto);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
	        }
	    }
}
