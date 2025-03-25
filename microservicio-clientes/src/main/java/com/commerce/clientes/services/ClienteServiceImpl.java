package com.commerce.clientes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.clientes.models.repositories.ClienteRepository;
import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.services.CommonServiceImpl;

@Service
public class ClienteServiceImpl  extends CommonServiceImpl<Cliente, ClienteRepository> implements ClienteService{

	@Override
	@Transactional
	public Cliente actualizar(Cliente cliente, Long id) {
		Optional<Cliente> optCliente = 
		repository.findById(id);
		
		if (optCliente.isPresent()) {
			Cliente clienteDb = optCliente.get();
			clienteDb.setNombre(cliente.getNombre());
			clienteDb.setApellido(cliente.getApellido());
			clienteDb.setEmail(cliente.getEmail());
			clienteDb.setTelefono(cliente.getTelefono());
			clienteDb.setDireccion(cliente.getDireccion());
			repository.save(clienteDb);
			return clienteDb;								
		}
			return null;
	}

}
