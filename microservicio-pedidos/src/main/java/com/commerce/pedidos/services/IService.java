package com.commerce.pedidos.services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface IService<T> {
	List<T> listar();
	Page<T> listar(Pageable page);
	Optional<T> obtenerPorId(Long id);
	T crear(T pedido);
	T editar(T pedido, Long id);
	T eliminar(Long id);
}
