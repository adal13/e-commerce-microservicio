package com.commerce.pedidos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.commerce.commons.models.entity.Pedidos;
import com.commerce.commons.models.entity.Productos;
import com.commerce.pedidos.clients.ProductoClient;
import com.commerce.pedidos.dto.PedidoDTO;
import com.commerce.pedidos.models.repositories.PedidoRepository;

import feign.FeignException;

@Service
public class PedidoServiceImpl implements IService<Pedidos>{
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ProductoClient productoCliente;

	@Override
	@Transactional(readOnly = true)
	public List<Pedidos> listar() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pedidos> listar(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pedidos> obtenerPorId(Long id) {
		
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Pedidos crear(PedidoDTO pedidoDTO) {
		Pedidos pedido = new Pedidos();

	    Productos producto = productoCliente.getProductoById(pedidoDTO.getClienteId());
	    if (producto == null) {
	        throw new RuntimeException("Producto no encontrado");
	    }

	    pedido.setTotal(pedidoDTO.getTotal());
	    pedido.setFechaCreacion(pedidoDTO.getFechaCreacion());
	    pedido.setIdEstado(pedidoDTO.getIdEstado());
	    // Aquí debes añadir el producto al pedido si la relación está definida correctamente
	    pedido.getProductos().add(producto);

	    return repository.save(pedido);
	}

	@Override
	@Transactional
	public Pedidos editar(Pedidos entity, Long id) {
		  Optional<Pedidos> optPedidos = repository.findById(id);
		    if (optPedidos.isPresent()) {
		        Pedidos pedidosDb = optPedidos.get();
		        pedidosDb.setCliente(entity.getCliente());
		        pedidosDb.setTotal(entity.getTotal());
		        pedidosDb.setFechaCreacion(entity.getFechaCreacion());
		        pedidosDb.setIdEstado(entity.getIdEstado());

		        // Si hay productos en la actualización, reemplázalos
		        if (entity.getProductos() != null) {
		            pedidosDb.getProductos().clear();
		            pedidosDb.getProductos().addAll(entity.getProductos());
		        }

		        return repository.save(pedidosDb);
		    } else {
		        throw new RuntimeException("Pedido no encontrado");
		    }
	}


	@Override
	@Transactional
	public Pedidos eliminar(Long id) {
	    Optional<Pedidos> optPedido = repository.findById(id);
	    if(optPedido.isPresent()) {
	        Pedidos pedido = optPedido.get();
	        repository.delete(pedido);  // Esto eliminará los productos en cascada
	        return pedido;
	    } else {
	        throw new RuntimeException("Pedido no encontrado");
	    }
	}

	
	@Override
	@Transactional
	public Pedidos addProducto(Long idPedido, Long idProducto) {
	    Optional<Pedidos> optPedido = repository.findById(idPedido);
	    if (!optPedido.isPresent()) {
	        System.out.println("Pedido con ID " + idPedido + " no encontrado.");
	        return null;
	    }

	    Pedidos pedido = optPedido.get();

	    try {
	        // Verificar si el producto existe antes de agregarlo
	        Productos producto = productoCliente.getProductoById(idProducto);
	        if (producto == null) {
	            System.out.println("Producto con ID " + idProducto + " no encontrado en la BD.");
	            return null;
	        }

	        pedido.getProductos().add(producto);
	        return repository.save(pedido);
	        
	    } catch (FeignException e) {
	        System.out.println("Error al buscar producto con ID " + idProducto + ": " + e.getMessage());
	        return null;
	    }
	}



	
	public List<Productos> listarProductosPorPedido(Long pedidoId) {
		  return repository.findById(pedidoId)
			        .map(Pedidos::getProductos)
			        .orElse(new ArrayList<>());
	}
}
