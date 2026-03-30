package com.edu.manager.mappers;

import com.edu.manager.dtos.EstudianteDTO;
import com.edu.manager.models.Estudiante;
import org.springframework.stereotype.Component;

/**
 * Mapper para la entidad {@link Estudiante} y su {@link EstudianteDTO}.
 * <p>
 * Convierte de entidad a DTO y de DTO a entidad.
 * </p>
 */
@Component
public class EstudianteMapper {

	/**
	 * Convierte un objeto Estudiante en su DTO correspondiente.
	 *
	 * @param estudiante entidad Estudiante
	 * @return objeto EstudianteDTO con los datos relevantes
	 */
	public EstudianteDTO toDTO(Estudiante estudiante) {
		EstudianteDTO dto = new EstudianteDTO();
		dto.setId(estudiante.getId());
		dto.setNombre(estudiante.getNombre());
		dto.setApellido(estudiante.getApellido());
		dto.setEmail(estudiante.getEmail());
		return dto;
	}

	/**
	 * Convierte un objeto EstudianteDTO en su entidad Estudiante.
	 *
	 * @param dto objeto EstudianteDTO
	 * @return entidad Estudiante con los datos del DTO
	 */
	public Estudiante toEntity(EstudianteDTO dto) {
		Estudiante estudiante = new Estudiante();
		estudiante.setId(dto.getId());
		estudiante.setNombre(dto.getNombre());
		estudiante.setApellido(dto.getApellido());
		estudiante.setEmail(dto.getEmail());
		return estudiante;
	}
}