package com.edu.manager.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para login. Se utiliza para recibir el username y password desde el
 * frontend o API.
 */
public class LoginRequestDTO {

	@NotBlank(message = "El usuario es obligatorio")
	private String username;

	@NotBlank(message = "La contraseña es obligatoria")
	private String password;

	// Getters y setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}