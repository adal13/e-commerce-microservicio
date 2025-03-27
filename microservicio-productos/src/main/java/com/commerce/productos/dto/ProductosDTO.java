package com.commerce.productos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductosDTO {
	
	private Long id;
	
    @NotNull(message = "El nombre del producto es obligatorio")
    @Size(min = 1, max = 50, message = "El nombre del producto debe tener entre 1 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción del producto es obligatoria")
    @Size(min = 1, max = 100, message = "La descripción debe tener entre 1 y 100 caracteres")
    private String descripcion;

    @NotNull(message = "El precio del producto es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double precio;

    @NotNull(message = "El stock del producto es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Number stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Number getStock() {
		return stock;
	}

	public void setStock(Number  stock) {
		this.stock = stock;
	}


}
