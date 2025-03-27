package com.commerce.clientes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.clientes.clients.PedidoClient;
import com.commerce.clientes.dto.ClienteDTO;
import com.commerce.clientes.models.repositories.ClienteRepository;
import com.commerce.commons.models.entity.Cliente;
import com.commerce.commons.models.entity.Pedidos;
import com.commerce.commons.services.CommonServiceImpl;

@Service
public class ClienteServiceImpl  extends CommonServiceImpl<Cliente, ClienteRepository> implements ClienteService{
	
	private final PedidoClient pedidoCliente;
	
	public ClienteServiceImpl(ClienteRepository repository, PedidoClient pedidoCliente) {
		super(repository);
		this.pedidoCliente = pedidoCliente;
	}

	@Override
	@Transactional
	public Cliente actualizar(ClienteDTO clienteDTO, Long id) {
	    Optional<Cliente> opt = repository.findById(id);
	    
	    if (opt.isPresent()) {
	        Cliente cliente = opt.get();
	        
	        cliente.setNombre(clienteDTO.getNombre());
	        cliente.setApellido(clienteDTO.getApellido());
	        cliente.setDireccion(clienteDTO.getDireccion());
	        cliente.setEmail(clienteDTO.getEmail());
	        cliente.setTelefono(clienteDTO.getTelefono());

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
		//cliente.setPedidos(clienteDTO.setPedidosIds(null));
		
		
		 // Si el cliente tiene una lista de pedidos, se obtiene la lista de pedidos desde el microservicio
        if (clienteDTO.getPedidosIds() != null && !clienteDTO.getPedidosIds().isEmpty()) {
            List<Pedidos> pedidos = pedidoCliente.getProductoById(clienteDTO.getPedidosIds());
            cliente.setPedidos(pedidos);

            // Establecer la relación bidireccional
            for (Pedidos pedido : pedidos) {
                pedido.setCliente(cliente);
            }
        }
						
		return repository.save(cliente);
	};
}
