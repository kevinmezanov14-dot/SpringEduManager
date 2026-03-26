package com.edu.manager.security;

import com.edu.manager.models.Usuario;
import com.edu.manager.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		return User.builder().username(usuario.getUsername()).password(usuario.getPassword())
				.roles(usuario.getRole().replace("ROLE_", "")).build();
	}
}