package com.commerce.pedidos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.models.entity.Pedidos;
import com.commerce.commons.models.entity.Productos;
import com.commerce.pedidos.clients.ClienteClient;
import com.commerce.pedidos.clients.ProductoClient;
import com.commerce.pedidos.dto.PedidoDTO;
import com.commerce.pedidos.models.repositories.PedidoRepository;

import feign.FeignException;

@Service
public class PedidoServiceImpl implements IService<Pedidos>{
	
	@Autowired
	private PedidoRepository repository;
	
	private final ProductoClient productoCliente;
	
	private final ClienteClient clienteClient;
	

	public PedidoServiceImpl(ProductoClient productoCliente, ClienteClient clienteClient) {
		this.productoCliente = productoCliente;
		this.clienteClient = clienteClient;
	}

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
		 if (pedidoDTO.getClienteId() == null) {
		        throw new IllegalArgumentException("El cliente es obligatorio");
		    }

		    Pedidos pedido = new Pedidos();

		    Cliente cliente = clienteClient.getClienteById(pedidoDTO.getClienteId());
		    if (cliente == null) {
		        throw new RuntimeException("Cliente no encontrado");
		    }

		    List<Productos> productos = new ArrayList<>();
		    if (pedidoDTO.getProductosIds() != null && !pedidoDTO.getProductosIds().isEmpty()) {
		        for (Long productoId : pedidoDTO.getProductosIds()) {
		            try {
		                Productos producto = productoCliente.getProductoById(productoId);
		                if (producto != null) {
		                    productos.add(producto);
		                } else {
		                    throw new RuntimeException("Producto no encontrado con ID: " + productoId);
		                }
		            } catch (FeignException.NotFound e) {
		                throw new RuntimeException("Producto no encontrado con ID: " + productoId);
		            }
		        }
		    }

		    pedido.setCliente(cliente);
		    pedido.setTotal(pedidoDTO.getTotal());
		    pedido.setFechaCreacion(pedidoDTO.getFechaCreacion());
		    pedido.setIdEstado(pedidoDTO.getIdEstado());

		    pedido.setProductos(productos);

		    return repository.save(pedido);

	}

	@Override
	@Transactional
	public Pedidos editar(Pedidos entity, Long id) {
		 Optional<Pedidos> optPedidos = repository.findById(id);
		    
		    if (optPedidos.isPresent()) {
		        Pedidos pedidosDb = optPedidos.get();

		        // Actualizar solo los campos necesarios
		        if (entity.getCliente() != null) {
		            pedidosDb.setCliente(entity.getCliente());
		        }
		        if (entity.getTotal() != null) {
		            pedidosDb.setTotal(entity.getTotal());
		        }
		        if (entity.getFechaCreacion() != null) {
		            pedidosDb.setFechaCreacion(entity.getFechaCreacion());
		        }
		        if (entity.getIdEstado() != null) {
		            pedidosDb.setIdEstado(entity.getIdEstado());
		        }
		        if (entity.getProductos() != null && !entity.getProductos().isEmpty()) {
		            pedidosDb.setProductos(entity.getProductos());
		        }

		        return repository.save(pedidosDb);
		    } else {
		        throw new RuntimeException("Pedido no encontrado con ID: " + id);
		    }
	}

	@Override
	@Transactional
	public Pedidos eliminar(Long id) {
		  Optional<Pedidos> optPedido = repository.findById(id);
		    if (optPedido.isPresent()) {
		        Pedidos pedido = optPedido.get();
		        pedido.setIdEstado(4); // Estado "Eliminado"
		        return repository.save(pedido);
		    } else {
		        throw new RuntimeException("Pedido no encontrado con ID: " + id);
		    }
	}
	
	
	public Pedidos addProducto(Long idPedido, Long idProducto) {
	    // Buscar el pedido en la base de datos
	    Optional<Pedidos> pedidoOpt = repository.findById(idPedido);
	    if (!pedidoOpt.isPresent()) {
	        return null; // O lanzar una excepción
	    }

	    // Llamar a FeignClient para obtener el producto
	    Productos producto;
	    try {
	        producto = productoCliente.getProductoById(idProducto);
	    } catch (FeignException.NotFound e) {
	        return null; // O manejar el error adecuadamente
	    }

	    // Agregar producto al pedido y guardar
	    Pedidos pedido = pedidoOpt.get();
	    pedido.getProductos().add(producto);
	    return repository.save(pedido);
	}

	
	public List<Productos> listarProductosPorPedido(Long pedidoId) {
	    return repository.findById(pedidoId).map(Pedidos::getProductos).orElse(new ArrayList<>());
	}



}
