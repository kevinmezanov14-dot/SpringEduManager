package com.edu.manager.mappers;

import com.edu.manager.dtos.UsuarioDTO;
import com.edu.manager.dtos.UsuarioRegistroDTO;
import com.edu.manager.models.Usuario;
import org.springframework.stereotype.Component;

/**
 * Mapper para la entidad {@link Usuario}.
 * <p>
 * Convierte entre la entidad Usuario y sus DTOs: - {@link UsuarioDTO}:
 * representación de usuario para vistas o APIs (sin password) -
 * {@link UsuarioRegistroDTO}: datos necesarios para registrar un usuario
 * </p>
 */
@Component
public class UsuarioMapper {

	/**
	 * Convierte un Usuario a su DTO correspondiente {@link UsuarioDTO}.
	 * <p>
	 * Solo incluye username y role; la contraseña se omite por seguridad.
	 * </p>
	 *
	 * @param usuario entidad Usuario
	 * @return DTO UsuarioDTO
	 */
	public UsuarioDTO toDto(Usuario usuario) {
		return new UsuarioDTO(usuario.getUsername(), usuario.getRole());
	}

	/**
	 * Convierte un DTO {@link UsuarioRegistroDTO} a la entidad Usuario.
	 * <p>
	 * Se utiliza para registrar un nuevo usuario; el password aún debe ser
	 * encriptado antes de persistir en la base de datos.
	 * </p>
	 *
	 * @param dto DTO de registro UsuarioRegistroDTO
	 * @return entidad Usuario
	 */
	public Usuario toEntity(UsuarioRegistroDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setUsername(dto.getUsername());
		usuario.setPassword(dto.getPassword()); // password plano, codificar antes de guardar
		return usuario;
	}
}