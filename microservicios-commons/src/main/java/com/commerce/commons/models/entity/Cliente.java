package com.commerce.commons.models.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CLIENTES")
public class Cliente {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_SEQ")
		@SequenceGenerator(name = "CLIENTE_SEQ", sequenceName = "CLIENTE_SEQ", allocationSize=1)
		@Column(name = "ID_CLIENTE")
		private Long id;
		
		@Column(name = "NOMBRE")
	    @NotBlank(message = "El nombre de la persona es obligatorio")
	    @Size(min = 1, max = 50, message = "El nombre de la persona debe tener entre 1 y 50 caracteres")
	    private String nombre;

	    @Column(name = "APELLIDO")
	    @NotBlank(message = "El apellido de la persona es obligatorio")
	    @Size(min = 1, max = 50, message = "El apellido debe tener entre 1 y 50 caracteres")
	    private String apellido;

	    @Column(name = "EMAIL")
	    @NotBlank(message = "El email es obligatorio")
	    @Email(message = "El email debe tener un formato válido")
	    @Size(min = 1, max = 100, message = "El email debe tener entre 1 y 100 caracteres")
	    private String email;

	    @Column(name = "TELEFONO")
	    @NotBlank(message = "El teléfono es obligatorio")
	    @Size(min = 7, max = 20, message = "El teléfono debe tener entre 7 y 20 dígitos")
	    private String telefono;

	    @Column(name = "DIRECCION")
	    @Size(min = 1, max = 100, message = "La dirección debe tener entre 1 y 100 caracteres")
	    private String direccion;
	    
	    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonManagedReference
	    @Null(message = "Puede llevar datos nulos")
		private List<Pedidos> pedidos;
	    
		public Cliente() {
			this.pedidos = new ArrayList<>();
		}

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

		public List<Pedidos> getPedidos() {
			return pedidos;
		}

		public void setPedidos(List<Pedidos> pedidos) {
			this.pedidos = pedidos;
		}		
		
		
}
