package com.commerce.pedidos.services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commerce.commons.models.entity.Pedidos;
import com.commerce.pedidos.models.repositories.PedidoRepository;

@Service
public class PedidoServiceImpl implements IService<Pedidos>{
	
	@Autowired
	private PedidoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Pedidos> listar() {
		return repository.findAll();
	}

	@Override
	public Page<Pedidos> listar(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pedidos> obtenerPorId(Long id) {
		
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Pedidos crear(Pedidos entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public Pedidos editar(Pedidos entity, Long id) {
		Optional<Pedidos> optPedidos = repository.findById(id);
		if(optPedidos.isPresent()) {
			Pedidos pedidosDb = optPedidos.get();
			pedidosDb.setCliente(entity.getCliente());
			pedidosDb.setTotal(entity.getTotal());
			pedidosDb.setFechaCreacion(entity.getFechaCreacion());
			pedidosDb.setIdEstado(entity.getIdEstado());
			return repository.save(pedidosDb);
		}else {
			return null;
		}
	}

	@Override
	@Transactional
	public Pedidos eliminar(Long id) {
		Optional<Pedidos> optPedido = repository.findById(id);
		if(optPedido.isPresent()) {
			repository.deleteById(id);
			return optPedido.get();
		}else {
			return null;
		}
	}

	
	
	
	/*
	 * private final PedidoMapper mapper;
	 * 
	 * public PedidoServiceImpl(PedidoMapper mapper) { super(); this.mapper =
	 * mapper; }
	 * 
	 * 
	 * @Override
	 * 
	 * @Transactional public Pedidos createPedidoDTO(PedidoDTO pedidoDTO) { try {
	 * Pedidos pedido = mapper.dtoToEntity(pedidoDTO);
	 * System.out.println("🛠 Vuelo convertido desde DTO: " + pedido); return
	 * repository.save(pedido); }catch(Exception e){ throw new
	 * RuntimeException("Error al guardar el vuelo: " + e.getMessage(), e); } }
	 * 
	 * @Override
	 * 
	 * @Transactional public Pedidos actualizar(PedidoDTO pedidoDTO, Long id) {
	 * Optional<Pedidos> optPedidos = repository.findById(id);
	 * if(optPedidos.isPresent()) { Pedidos pedido = mapper.dtoToEntity(pedidoDTO);
	 * pedido.setId(id); return repository.save(pedido); } return null; }
	 */
	
}
