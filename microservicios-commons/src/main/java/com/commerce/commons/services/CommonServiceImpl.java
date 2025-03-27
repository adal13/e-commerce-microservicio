package com.commerce.commons.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl <E, R extends JpaRepository<E, Long>> implements CommonService<E>{

	protected final R repository;
	
	public CommonServiceImpl(R repository) {
		 this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<E> listar() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> obtenerPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public E crear(E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public Optional<E> eliminarPorId(Long id) {
		Optional<E> entity = repository.findById(id);
		if(entity.isPresent()) {
			repository.deleteById(id);
			
			return entity;
		}
		return Optional.empty();
	}

}
