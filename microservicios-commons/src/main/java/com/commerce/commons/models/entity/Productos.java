package com.commerce.commons.models.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PRODUCTOS")
public class Productos {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTO_SEQ")
	@SequenceGenerator(name = "PRODUCTO_SEQ", sequenceName = "PRODUCTO_SEQ", allocationSize=1)
	@Column(name = "ID_PRODUCTO")
	private Long id;
	
	@Column(name = "NOMBRE")
    @NotNull(message = "El nombre del producto es obligatorio")
    @Size(min = 1, max = 50, message = "El nombre del producto debe tener entre 1 y 50 caracteres")
    private String nombre;

    @Column(name = "DESCRIPCION")
    @NotBlank(message = "La descripción del producto es obligatoria")
    @Size(min = 1, max = 100, message = "La descripción debe tener entre 1 y 100 caracteres")
    private String descripcion;

    @Column(name = "PRECIO")
    @NotNull(message = "El precio del producto es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double precio;

    @Column(name = "STOCK")
    @NotNull(message = "El stock del producto es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;
    
    @ManyToMany(mappedBy = "productos") 
    private Set<Pedidos> pedidos = new HashSet<>();

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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Set<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}
    
    

    
    
}
 	