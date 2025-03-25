package com.commerce.productos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.commerce.commons.models.entity.Productos;
import com.commerce.commons.services.CommonServiceImpl;
import com.commerce.productos.dto.ProductosDTO;
import com.commerce.productos.mappers.ProductosMapper;
import com.commerce.productos.models.repository.ProductosRepository;


@Service
public class ProductosServiceImpl extends CommonServiceImpl<Productos, ProductosRepository> 
implements ProductosService{
	
	private final ProductosMapper mapper;
	
	@Autowired
	private ProductosRepository repository;
	

	public ProductosServiceImpl(ProductosMapper mapper) {
		super();
		this.mapper=mapper;
	}
	
	@Override
	@Transactional
	public Productos actualizar(ProductosDTO productosDTO, Long id) {
		Optional<Productos> opt = repository.findById(id);
		if(opt.isPresent()) {
			Productos productos = mapper.dtoToEntity(productosDTO);
		
			productos.setId(id);
			return repository.save(productos);
			
		}
		
		return null;
	}

	@Override
	@Transactional
	public Productos createProducto(ProductosDTO productosDTO) {
		try {
			Productos producto = mapper.dtoToEntity(productosDTO);
			repository.save(producto);
			return repository.save(producto);
			
		}catch(Exception e){
			throw new RuntimeException("Error al guardar el producto: " + e.getMessage(), e);
		}
		
	}

	@Override
	@Transactional
	public Productos deleteProductos(Long id) {
			Optional<Productos> producto = repository.findById(id);
			if(producto.isPresent()) {
				repository.deleteById(id);
				return producto.get();
		    }else {
		    	return null;
		    }
			
	}




	
}
	
	
