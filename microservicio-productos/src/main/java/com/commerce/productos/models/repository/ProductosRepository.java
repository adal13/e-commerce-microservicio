package com.commerce.productos.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commerce.commons.models.entity.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Long>{

}
