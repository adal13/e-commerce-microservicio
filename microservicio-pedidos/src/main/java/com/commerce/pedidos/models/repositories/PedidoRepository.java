package com.commerce.pedidos.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commerce.commons.models.entity.Pedidos;


public interface PedidoRepository extends JpaRepository<Pedidos, Long>{

}
