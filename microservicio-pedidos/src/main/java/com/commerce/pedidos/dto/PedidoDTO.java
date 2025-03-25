package com.commerce.pedidos.dto;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class PedidoDTO {
	private Long id;

    @NotNull(message = "El cliente es obligatorio")
    @Min(value = 1, message = "El valor del id del pedido no puede ser menor que 1")
    private Long clienteId; // Solo pasamos el ID del cliente

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor que 0")
    private Double total;

    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha de creación no puede ser futura")
    private LocalDate fechaCreacion;

    @NotNull(message = "El estado es obligatorio")
    private Integer idEstado;

    @NotNull(message = "Debe haber al menos un producto en el pedido")
    private Long productosIds; // Lista de IDs de productos en lugar de objetos completos

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
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

	public Long getProductosIds() {
		return productosIds;
	}

	public void setProductosIds(Long productosIds) {
		this.productosIds = productosIds;
	}
}
