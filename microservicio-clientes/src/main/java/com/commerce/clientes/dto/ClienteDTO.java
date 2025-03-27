package com.commerce.clientes.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class ClienteDTO {
    private Long id;
	
    @NotBlank(message = "El nombre de la persona es obligatorio")
    @Size(min = 1, max = 50, message = "El nombre de la persona debe tener entre 1 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido de la persona es obligatorio")
    @Size(min = 1, max = 50, message = "El apellido debe tener entre 1 y 50 caracteres")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(min = 1, max = 100, message = "El email debe tener entre 1 y 100 caracteres")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 20, message = "El teléfono debe tener entre 7 y 20 dígitos")
    private String telefono;

    @Size(min = 1, max = 100, message = "La dirección debe tener entre 1 y 100 caracteres")
    private String direccion;

    @Null(message = "Debe haber al menos un producto en el pedido")
    private List<Long> pedidosIds; // Lista de IDs de productos en lugar de objetos completos
    
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Long> getPedidosIds() {
		return pedidosIds;
	}

	public void setPedidosIds(List<Long> pedidosIds) {
		this.pedidosIds = pedidosIds;
	}

	
	
    
}
