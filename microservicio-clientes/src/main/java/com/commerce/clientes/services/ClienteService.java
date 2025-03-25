package com.commerce.clientes.services;

import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.services.CommonService;

public interface ClienteService extends CommonService<Cliente> {
	Cliente actualizar(Cliente cliente, Long id);
}
