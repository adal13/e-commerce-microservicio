package com.commerce.pedidos.mappers;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.models.entity.Pedidos;
import com.commerce.commons.models.entity.Productos;
import com.commerce.pedidos.dto.PedidoDTO;

@Component
public class PedidoMapper {
	
	public PedidoDTO entityToDto(Pedidos pedido) {
		if (pedido == null) {
            return null;
        }

        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setClienteId(pedido.getCliente() != null ? pedido.getCliente().getId() : null);
        pedidoDTO.setTotal(pedido.getTotal());
        pedidoDTO.setFechaCreacion(pedido.getFechaCreacion());
        pedidoDTO.setIdEstado(pedido.getIdEstado());

        // Mapear la lista de productos a una lista de IDs
        if (pedido.getProductos() != null) {
            List<Long> productosIds = pedido.getProductos().stream()
                    .map(producto -> producto.getId()) // Suponiendo que Productos tiene un método getId()
                    .collect(Collectors.toList());
            pedidoDTO.setProductosIds(productosIds);
        }

        return pedidoDTO;
	}
	
	public Pedidos dtoToEntity(PedidoDTO pedidoDTO) {
		 if (pedidoDTO == null) {
	            return null;
	        }

	        Pedidos pedido = new Pedidos();
	        pedido.setTotal(pedidoDTO.getTotal());
	        pedido.setFechaCreacion(pedidoDTO.getFechaCreacion());
	        pedido.setIdEstado(pedidoDTO.getIdEstado());

	        // Mapear cliente
	        if (pedidoDTO.getClienteId() != null) {
	            Cliente cliente = new Cliente();
	            cliente.setId(pedidoDTO.getClienteId());
	            pedido.setCliente(cliente);
	        }

	        // Mapear productos a partir de los IDs
	        if (pedidoDTO.getProductosIds() != null && !pedidoDTO.getProductosIds().isEmpty()) {
	            List<Productos> productos = pedidoDTO.getProductosIds().stream()
	                    .map(productoId -> {
	                        Productos producto = new Productos();
	                        producto.setId(productoId);
	                        return producto; // Asegúrate de tener el método setId en Productos
	                    })
	                    .collect(Collectors.toList());
	            pedido.setProductos(productos);
	        }

	        return pedido;
	}
}
