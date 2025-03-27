package com.commerce.pedidos.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commerce.commons.models.entity.Pedidos;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Long>{

}
	