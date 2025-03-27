package com.commerce.pedidos.mappers;
import org.springframework.stereotype.Component;

import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.models.entity.Pedidos;
import com.commerce.pedidos.dto.PedidoDTO;

@Component
public class PedidoMapper {
	
	public PedidoDTO entityToDto(Pedidos pedido) {
		if(pedido == null) {
			return null;
		}
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setClienteId(pedido.getCliente() != null ? pedido.getCliente().getId() : null );
		pedidoDTO.setTotal(pedido.getTotal());
		pedidoDTO.setFechaCreacion(pedido.getFechaCreacion());
		pedidoDTO.setIdEstado(pedido.getIdEstado());
		pedidoDTO.setProductosIds(pedido.getProductos() != null ? ((Cliente) pedido.getProductos()).getId() : null);
		
		return pedidoDTO;
	}
	
	public Pedidos dtoToEntity(PedidoDTO pedidoDTO) {
		if(pedidoDTO == null) {
			return null;
		}
		
		Pedidos pedido = new Pedidos();
		pedido.setTotal(pedidoDTO.getTotal());
		pedido.setFechaCreacion(pedidoDTO.getFechaCreacion());
		pedido.setIdEstado(pedidoDTO.getIdEstado()); 
		
		if (pedidoDTO.getClienteId() != null) {
			Cliente cliente = new Cliente();
	        cliente.setId(pedidoDTO.getClienteId());
	        pedido.setCliente(cliente);
		}
		
		return pedido;
	}
	
	
}
