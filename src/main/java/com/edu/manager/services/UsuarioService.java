package com.edu.manager.services;

import com.edu.manager.models.Usuario;
import com.edu.manager.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los
 * usuarios.
 * <p>
 * Proporciona operaciones para registrar nuevos usuarios, listar usuarios
 * existentes y aplicar validaciones de seguridad y roles.
 * </p>
 * 
 * Funcionalidad clave:
 * <ul>
 * <li>Validación de campos obligatorios (username y password).</li>
 * <li>Encriptación de contraseña.</li>
 * <li>Asignación automática de roles: el primer usuario registrado es ADMIN,
 * los siguientes son USER.</li>
 * <li>Prevención de duplicación de usernames.</li>
 * </ul>
 * 
 * Autor: Kevin
 */
@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * Constructor de UsuarioService.
	 * 
	 * @param usuarioRepository repositorio para operaciones CRUD de usuarios
	 * @param passwordEncoder   codificador de contraseñas para seguridad
	 */
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Registra un nuevo usuario en el sistema.
	 * <p>
	 * Valida los campos obligatorios, verifica que el username no exista
	 * previamente, encripta la contraseña y asigna un rol automático.
	 * </p>
	 *
	 * @param usuario objeto Usuario a registrar
	 * @return el usuario registrado con ID y rol asignado
	 * @throws RuntimeException si el username es vacío, la contraseña es muy corta
	 *                          o el usuario ya existe
	 */
	public Usuario registrarUsuario(Usuario usuario) {

		if (usuario.getUsername() == null || usuario.getUsername().isBlank()) {
			throw new RuntimeException("El username es obligatorio");
		}

		if (usuario.getPassword() == null || usuario.getPassword().length() < 4) {
			throw new RuntimeException("La contraseña debe tener al menos 4 caracteres");
		}

		if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
			throw new RuntimeException("El usuario ya existe");
		}
		// Encriptar la contraseña antes de guardar
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		// Asignar rol automático: el primer usuario es ADMIN
		if (usuarioRepository.count() == 0) {
			usuario.setRole("ROLE_ADMIN");
		} else {
			usuario.setRole("ROLE_USER");
		}

		return usuarioRepository.save(usuario);
	}

	/**
	 * Obtiene la lista completa de usuarios registrados.
	 *
	 * @return lista de todos los usuarios
	 */
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
}