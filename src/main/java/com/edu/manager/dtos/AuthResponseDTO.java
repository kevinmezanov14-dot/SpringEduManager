package com.edu.manager.dtos;

/**
 * DTO de respuesta de autenticación.
 * <p>
 * Se utiliza para devolver al cliente la información de login, incluyendo el
 * token JWT generado, el nombre de usuario y el rol asignado.
 * </p>
 */
public class AuthResponseDTO {

	/** Token JWT generado para la sesión del usuario */
	private String token;

	/** Nombre de usuario autenticado */
	private String username;

	/** Rol del usuario (ROLE_ADMIN o ROLE_USER) */
	private String role;

	/**
	 * Constructor principal
	 *
	 * @param token    JWT generado
	 * @param username nombre de usuario
	 * @param role     rol asignado al usuario
	 */
	public AuthResponseDTO(String token, String username, String role) {
		this.token = token;
		this.username = username;
		this.role = role;
	}

	// Getters y setters
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}