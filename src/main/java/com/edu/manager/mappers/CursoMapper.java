package com.edu.manager.mappers;

import com.edu.manager.dtos.CursoDTO;
import com.edu.manager.models.Curso;
import org.springframework.stereotype.Component;

/**
 * Mapper para la entidad {@link Curso} y su {@link CursoDTO}.
 * <p>
 * Convierte de entidad a DTO y de DTO a entidad.
 * </p>
 */
@Component
public class CursoMapper {

	/**
	 * Convierte un objeto Curso en su DTO correspondiente.
	 *
	 * @param curso entidad Curso
	 * @return objeto CursoDTO con los datos relevantes
	 */
	public CursoDTO toDTO(Curso curso) {
		CursoDTO dto = new CursoDTO();
		dto.setId(curso.getId());
		dto.setNombre(curso.getNombre());
		dto.setDescripcion(curso.getDescripcion());
		dto.setInstructor(curso.getInstructor());
		return dto;
	}

	/**
	 * Convierte un objeto CursoDTO en su entidad Curso.
	 *
	 * @param dto objeto CursoDTO
	 * @return entidad Curso con los datos del DTO
	 */
	public Curso toEntity(CursoDTO dto) {
		Curso curso = new Curso();
		curso.setId(dto.getId());
		curso.setNombre(dto.getNombre());
		curso.setDescripcion(dto.getDescripcion());
		curso.setInstructor(dto.getInstructor());
		return curso;
	}
}