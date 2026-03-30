package com.edu.manager.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Representa un usuario del sistema EduManager.
 * <p>
 * Los usuarios se utilizan para la autenticación y autorización mediante Spring
 * Security. Cada usuario tiene un nombre de usuario único, contraseña y un rol
 * asignado.
 * </p>
 * 
 * <p>
 * Validaciones:
 * <ul>
 * <li>El username es obligatorio y único.</li>
 * <li>La contraseña es obligatoria y debe tener al menos 4 caracteres.</li>
 * <li>El rol define los permisos del usuario (por ejemplo, ADMIN o USER).</li>
 * </ul>
 * </p>
 * 
 * Seguridad:
 * <ul>
 * <li>El campo password se ignora en la serialización JSON mediante @JsonIgnore
 * para no exponerlo.</li>
 * </ul>
 * </p>
 * 
 * Autor: Kevin
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
	/**
	 * Identificador único de la práctica. Se genera automáticamente con estrategia
	 * IDENTITY.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Nombre de usuario único para autenticación. No puede estar vacío.
	 */
	@NotBlank(message = "El usuario es obligatorio")
	@Column(unique = true, nullable = false)
	private String username;
	/**
	 * Contraseña del usuario.
	 * <p>
	 * Debe tener al menos 4 caracteres.
	 * 
	 * @JsonIgnore evita que se exponga en respuestas JSON.
	 *             </p>
	 */
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
	@Column(nullable = false)
	@JsonIgnore // evita exponer password
	private String password;
	/**
	 * Rol asignado al usuario.
	 * <p>
	 * Define los permisos del usuario dentro del sistema (por ejemplo, ADMIN o
	 * USER).
	 * </p>
	 */
	@Column(nullable = false)
	private String role;

	public Usuario() {
	}
    /**
     * Constructor completo de la clase Usuario.
     * 
     * @param id identificador del usuario
     * @param username nombre de usuario
     * @param password contraseña
     * @param role rol asignado
     */
	public Usuario(Long id, String username, String password, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}
}