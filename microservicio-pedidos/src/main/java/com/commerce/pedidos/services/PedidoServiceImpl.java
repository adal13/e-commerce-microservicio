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

@Service
public class PedidoServiceImpl implements IService<Pedidos>{
	
	@Autowired
	private PedidoRepository repository;
	
	private final ProductoClient productoCliente;
	

	public PedidoServiceImpl(ProductoClient productoCliente) {
		this.productoCliente = productoCliente;
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
		// return repository.save(entity);
		
		 Pedidos pedido = new Pedidos();
		 
		 
		 Productos producto = productoCliente
				 .getProductoById(pedidoDTO.getClienteId())
				 .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
		    // Mapear manualmente los campos del DTO a la entidad
		    //pedido.setCliente(pedidoDTO.getClienteId());
		 	// pedido.setCliente(producto);
		 
		    pedido.setTotal(pedidoDTO.getTotal());
		    pedido.setFechaCreacion(pedidoDTO.getFechaCreacion());
		    pedido.setIdEstado(pedidoDTO.getIdEstado());
		    // Otros campos...
		    return repository.save(pedido);
	}

	@Override
	@Transactional
	public Pedidos editar(Pedidos entity, Long id) {
		Optional<Pedidos> optPedidos = repository.findById(id);
		if(optPedidos.isPresent()) {
			Pedidos pedidosDb = optPedidos.get();
			pedidosDb.setCliente(entity.getCliente());
			pedidosDb.setTotal(entity.getTotal());
			pedidosDb.setFechaCreacion(entity.getFechaCreacion());
			pedidosDb.setIdEstado(entity.getIdEstado());
			return repository.save(pedidosDb);
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public Pedidos eliminar(Long id) {
		Optional<Pedidos> optPedido = repository.findById(id);
		if(optPedido.isPresent()) {
			repository.deleteById(id);
			return optPedido.get();
		}else {
			return null;
		}
	}

	/*@Override
	@Transactional
	public Pedidos addProducto(Long idPedido, Long idProducto) {
	    Pedidos pedido = null;

	    Optional<Pedidos> optPedido = repository.findById(idPedido);
	    
	    if (optPedido.isPresent()) {
	        Optional<Productos> optProducto = productoCliente.getProductoById(idProducto);

	        if (optProducto.isPresent()) {
	            pedido = optPedido.get();
	            Productos producto = optProducto.get();

	            // Agregar producto al pedido
	            pedido.addProducto(producto);
	            pedido = repository.save(pedido);

	            // Relacionar el producto con el pedido (si aplica)
	            producto.setPedidos((List<Pedidos>) pedido); // Asegúrate de tener este método en Productos
	            producto = productoCliente.save(producto);
	        }
	    }

	    return pedido;
	}*/
	
	
	public Pedidos addProducto(Long idPedido, Long idProducto) {
	    Optional<Pedidos> pedidoOpt = repository.findById(idPedido);
	    if (!pedidoOpt.isPresent()) {
	        return null; // O lanzar una excepción, dependiendo del comportamiento que desees
	    }

	    Optional<Productos> productoOpt = productoCliente.getProductoById(idProducto);
	    if (!productoOpt.isPresent()) {
	        return null; // O lanzar una excepción si el producto no se encuentra
	    }

	    Pedidos pedido = pedidoOpt.get();
	    Productos producto = productoOpt.get();
	    
	    pedido.getProductos().add(producto);
	    return repository.save(pedido); // Guarda y devuelve el pedido actualizado
	}
	
	public List<Productos> listarProductosPorPedido(Long pedidoId) {
	    return repository.findById(pedidoId).map(Pedidos::getProductos).orElse(new ArrayList<>());
	}



}
