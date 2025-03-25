package com.commerce.clientes.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.clientes.dto.ClienteDTO;
import com.commerce.clientes.models.repositories.ClienteRepository;
import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.services.CommonServiceImpl;

@Service
public class ClienteServiceImpl  extends CommonServiceImpl<Cliente, ClienteRepository> implements ClienteService{

	@Override
	@Transactional
	public Cliente actualizar(ClienteDTO clienteDTO, Long id) {
		Optional<Cliente> opt=repository.findById(id);
		
		if(opt.isPresent()) {
			Cliente cliente = opt.get();
			cliente.setNombre(clienteDTO.getNombre());
			cliente.setApellido(clienteDTO.getApellido());
			cliente.setDireccion(clienteDTO.getDireccion());
			cliente.setEmail(clienteDTO.getEmail());
			cliente.setTelefono(clienteDTO.getTelefono());
			
			//clienteDb.setId(id);		
			return repository.save(cliente);					
		}
		return null;  
	}
	
	@Override
	@Transactional
	public Cliente crear(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setApellido(clienteDTO.getApellido());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setTelefono(clienteDTO.getTelefono());
		cliente.setDireccion(clienteDTO.getDireccion());
						
		return repository.save(cliente);
	};
}
