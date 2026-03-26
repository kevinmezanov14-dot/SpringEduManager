package com.edu.manager.services;

import com.edu.manager.models.Usuario;
import com.edu.manager.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

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

		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		if (usuarioRepository.count() == 0) {
			usuario.setRole("ROLE_ADMIN");
		} else {
			usuario.setRole("ROLE_USER");
		}

		return usuarioRepository.save(usuario);
	}

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
}