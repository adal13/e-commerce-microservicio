package com.commerce.productos.mappers;
import org.springframework.stereotype.Component;

import com.commerce.commons.models.entity.Productos;
import com.commerce.productos.dto.ProductosDTO;


@Component
public class ProductosMapper {
    public ProductosDTO entityToDto(Productos productos) {
        if (productos == null) 
        {
        	return null;
        }

        ProductosDTO productoDTO = new ProductosDTO();
        productoDTO.setId(productos.getId());
        productoDTO.setNombre(productos.getNombre());
        productoDTO.setDescripcion(productos.getDescripcion());
        productoDTO.setPrecio(productos.getPrecio());
        productoDTO.setStock(productos.getStock());
        return productoDTO;
    }

    public Productos dtoToEntity(ProductosDTO productosDTO) {
        if (productosDTO == null) {
        	return null;
        }else {
        	
        	Productos productos = new Productos();
        	productos.setId(productosDTO.getId());
        	productos.setNombre(productosDTO.getNombre());
        	productos.setDescripcion(productosDTO.getDescripcion());
        	productos.setPrecio(productosDTO.getPrecio());
        	//productos.setStock(productosDTO.getStock());
        	
        	  // Manejo seguro para evitar problemas con valores nulos
            productos.setStock(productosDTO.getStock() != null ? (Integer) productosDTO.getStock() : 0);
        	
        	return productos;
        }

    }}