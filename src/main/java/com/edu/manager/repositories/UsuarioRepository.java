package com.edu.manager.repositories;

import com.edu.manager.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para la entidad {@link Usuario}.
 * <p>
 * Utilizado para autenticación y gestión de usuarios.
 * </p>
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Busca un usuario por su username.
	 *
	 * @param username nombre de usuario
	 * @return Optional con el usuario si existe
	 */
	Optional<Usuario> findByUsername(String username);
}