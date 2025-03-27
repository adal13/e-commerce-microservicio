package com.commerce.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.commerce.commons.models.entity.Pedidos;
import com.commerce.pedidos.dto.PedidoDTO;

public interface IService<T> {
	List<T> listar();
	Page<T> listar(Pageable page);
	Optional<T> obtenerPorId(Long id);
	T editar(T pedido, Long id);
	T eliminar(Long id);
	Pedidos crear(PedidoDTO pedidoDTO);
	Pedidos addProducto(Long idPedido, Long idProducto);
}
