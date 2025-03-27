package com.commerce.clientes.services;

import com.commerce.clientes.dto.ClienteDTO;
import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.services.CommonService;

public interface ClienteService extends CommonService<Cliente> {
	Cliente actualizar(ClienteDTO clienteDTO, Long id);
	
	Cliente crear(ClienteDTO clienteDTO);

	
	
}
