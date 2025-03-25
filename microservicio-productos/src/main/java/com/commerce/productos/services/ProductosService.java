package com.commerce.productos.services;

import com.commerce.commons.models.entity.Productos;
import com.commerce.commons.services.CommonService;
import com.commerce.productos.dto.ProductosDTO;


public interface ProductosService extends CommonService<Productos> { 
	Productos actualizar(ProductosDTO productosDTO, Long id);
	Productos createProducto(ProductosDTO productosDTO);
	Productos deleteProductos(Long id);
	}
