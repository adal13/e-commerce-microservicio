package com.commerce.commons.models.entity;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "PEDIDOS")
public class Pedidos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEDIDO_SEQ")
	@SequenceGenerator(name = "PEDIDO_SEQ", sequenceName = "PEDIDO_SEQ", allocationSize=1)
	@Column(name = "ID_PEDIDO")
	private Long id;
	
	// @Column(name = "ID_CLIENTE")
    // private Cliente idCliente;
	@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	@NotNull(message = "El cliente es obligatorio")
	@JsonBackReference
    private Cliente cliente; // ¡Usa el objeto Cliente, no solo el ID!

    @Column(name = "TOTAL")
    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", message = "El total debe ser mayor que 0")
    private Double total;

    @Column(name = "FECHA_CREACION")
    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha de creación no puede ser futura")
    private LocalDate fechaCreacion;

    @Column(name = "ID_ESTADO")
    @NotNull(message = "El estado es obligatorio")
    private Integer idEstado;
    
    @JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "PEDIDOS_PRODUCTOS", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO"), // Columna que referencia a PEDIDOS
        inverseJoinColumns = @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO") // Columna que referencia a PRODUCTOS
    )
    @JsonManagedReference
    private List<Productos> productos; // Usa Set para evitar duplicados
    

	public Pedidos() {
		this.productos = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}

	public void removeProducto(Productos producto) {
		this.productos.remove(producto);
	}
	
	public void addProducto(Productos prouductos) {
		this.productos.add(prouductos);
	}
        
}
