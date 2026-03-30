/**
 * DTO que representa los datos requeridos para registrar un usuario.
 * Se utiliza como entrada desde formularios o peticiones REST.
 */
package com.edu.manager.dtos;

public class UsuarioRegistroDTO {

	private String username;
	private String password;

	// Constructor vacío
	public UsuarioRegistroDTO() {
	}

	// Constructor con parámetros
	public UsuarioRegistroDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Getters y Setters
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