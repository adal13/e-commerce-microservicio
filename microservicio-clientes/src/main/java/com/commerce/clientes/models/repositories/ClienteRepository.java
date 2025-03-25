package com.commerce.clientes.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.commerce.commons.models.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

