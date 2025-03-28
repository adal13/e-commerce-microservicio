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
<<<<<<< HEAD
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
=======
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

>>>>>>> adal
	}

	@Override
	@Transactional
	public Pedidos editar(Pedidos entity, Long id) {
<<<<<<< HEAD
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
=======
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
>>>>>>> adal
		        }

		        return repository.save(pedidosDb);
		    } else {
<<<<<<< HEAD
		        throw new RuntimeException("Pedido no encontrado");
=======
		        throw new RuntimeException("Pedido no encontrado con ID: " + id);
>>>>>>> adal
		    }
	}


	@Override
	@Transactional
	public Pedidos eliminar(Long id) {
<<<<<<< HEAD
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



=======
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

>>>>>>> adal
	
	public List<Productos> listarProductosPorPedido(Long pedidoId) {
		  return repository.findById(pedidoId)
			        .map(Pedidos::getProductos)
			        .orElse(new ArrayList<>());
	}
}
