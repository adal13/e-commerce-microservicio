package com.commerce.commons.services;

import java.util.List;
import java.util.Optional;

public interface CommonService<E> {
	List<E> listar();
	Optional<E> obtenerPorId(Long id);
	E crear(E entity);
	Optional<E> eliminarPorId(Long id);
	
}
