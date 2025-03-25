package com.commerce.clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

}
