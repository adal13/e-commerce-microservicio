package com.commerce.pedidos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.commerce.commons.models.entity.Productos;
import com.commerce.pedidos.MicroservicioPedidosApplication;
import com.commerce.pedidos.clients.ProductoClient;
import com.commerce.pedidos.dto.PedidoDTO;
import com.commerce.pedidos.models.repositories.PedidoRepository;
import com.commerce.pedidos.services.PedidoService;
import com.commerce.pedidos.services.PedidoServiceImpl;
import com.commerce.productos.models.repository.ProductosRepository;

import feign.FeignException;
import jakarta.validation.Valid;


@CrossOrigin(value = "http://localhost:4200")


@RestController
public class PedidoControllers{

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ProductoClient productoClient;
	
	
	
	@Autowired
	private PedidoServiceImpl pedidoServiceImpl;

	
	@GetMapping
	public ResponseEntity<List<Pedidos>> getAll(){
		return ResponseEntity.ok(pedidoServiceImpl.listar());
	}
	
	@GetMapping("/pedido/{pedidoId}/productos")
	public ResponseEntity<List<Productos>> getProductosByPedido(@PathVariable Long pedidoId) {
	    List<Productos> productos = pedidoServiceImpl.listarProductosPorPedido(pedidoId);
	    return ResponseEntity.ok(productos);
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
	
	@PostMapping("/pedido-dto")
	public ResponseEntity<?> create(@Valid @RequestBody PedidoDTO pedidoDTO, BindingResult result) {
		// 1. Manejo de errores de validación
	    if (result.hasErrors()) {
	        Map<String, String> errores = new HashMap<>();
	        result.getFieldErrors().forEach(error -> {
	            errores.put(error.getField(), error.getDefaultMessage());
	        });
	        return ResponseEntity.badRequest().body(errores);
	    }

	    try {
	        // 2. Intentar crear el pedido
	        Pedidos pedido = pedidoServiceImpl.crear(pedidoDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);

	    } catch (FeignException.NotFound e) {
	        // 3. Capturar errores específicos de Feign (ej: cliente no encontrado)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	            Map.of("mensaje", "Recurso no encontrado: " + e.getMessage())
	        );

	    } catch (IllegalArgumentException e) {
	        // 4. Capturar errores de lógica de negocio
	        return ResponseEntity.badRequest().body(
	            Map.of("mensaje", e.getMessage())
	        );

	    } catch (Exception e) {
	        // 5. Manejo de errores inesperados
	        return ResponseEntity.internalServerError().body(
	            Map.of("mensaje", "Error interno al procesar el pedido")
	        );
	    }
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
	
<<<<<<< HEAD
	@PutMapping("/{idPedido}/productos/{idProducto}")
	public ResponseEntity<Pedidos> addProducto(@PathVariable Long idPedido, @PathVariable Long idProducto) {
	    Pedidos pedidoActualizado = pedidoServiceImpl.addProducto(idPedido, idProducto);
	    
	    if (pedidoActualizado != null) {
	        return ResponseEntity.ok(pedidoActualizado);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

=======
	@PutMapping("/{idPedido}/{idProducto}")
	public Pedidos addProducto(Long idPedido, Long idProducto) {
	    // Buscar el pedido en la base de datos
	    Optional<Pedidos> pedidoOpt = repository.findById(idPedido);
	    if (!pedidoOpt.isPresent()) {
	        throw new RuntimeException("Pedido no encontrado");
	    }

	    // Obtener el pedido
	    Pedidos pedido = pedidoOpt.get();

	    // Verificar si el producto ya está en el pedido
	    boolean productoYaExiste = pedido.getProductos().stream()
	        .anyMatch(producto -> producto.getId().equals(idProducto));

	    if (productoYaExiste) {
	        throw new RuntimeException("El producto ya está en el pedido");
	    }

	    // Llamar a FeignClient para obtener el producto
	    Productos producto;
	    try {
	        producto = productoClient.getProductoById(idProducto);
	    } catch (FeignException.NotFound e) {
	        throw new RuntimeException("Producto no encontrado con ID: " + idProducto);
	    }

	    // Agregar producto al pedido y guardar
	    pedido.getProductos().add(producto);
	    return repository.save(pedido);
	}


>>>>>>> adal
	
}
