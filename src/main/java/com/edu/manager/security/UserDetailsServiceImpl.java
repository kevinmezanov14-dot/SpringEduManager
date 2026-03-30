package com.edu.manager.security;

import com.edu.manager.models.Usuario;
import com.edu.manager.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Implementación de {@link UserDetailsService} para cargar la información de usuarios
 * desde la base de datos.
 * <p>
 * Este servicio es utilizado por Spring Security durante el proceso de autenticación,
 * proporcionando los detalles del usuario (username, password, roles) para la verificación
 * de credenciales y autorización de acceso.
 * </p>
 * 
 * Funcionalidad clave:
 * <ul>
 *     <li>Busca un usuario en la base de datos a partir de su username.</li>
 *     <li>Lanza {@link UsernameNotFoundException} si el usuario no existe.</li>
 *     <li>Construye un objeto {@link UserDetails} con username, password y roles para Spring Security.</li>
 * </ul>
 * 
 * Autor: Kevin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor de UserDetailsServiceImpl.
     *
     * @param usuarioRepository repositorio para acceder a los datos de usuarios
     */
    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carga un usuario a partir del username.
     * <p>
     * Utilizado por Spring Security para autenticar usuarios y obtener sus roles.
     * </p>
     *
     * @param username nombre de usuario a buscar
     * @return objeto {@link UserDetails} con username, password y roles
     * @throws UsernameNotFoundException si el usuario no existe en la base de datos
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Construye UserDetails para Spring Security
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().replace("ROLE_", "")) // elimina prefijo ROLE_ para Spring
                .build();
    }
}