package com.edu.manager.mappers;

import com.edu.manager.dtos.PracticaDTO;
import com.edu.manager.models.Practica;
import org.springframework.stereotype.Component;

/**
 * Mapper para la entidad {@link Practica} y su {@link PracticaDTO}.
 * <p>
 * Convierte de entidad a DTO y de DTO a entidad, incluyendo las relaciones
 * completas con {@link com.edu.manager.models.Estudiante} y
 * {@link com.edu.manager.models.Curso}.
 * </p>
 */
@Component
public class PracticaMapper {

	private final EstudianteMapper estudianteMapper;
	private final CursoMapper cursoMapper;

	public PracticaMapper(EstudianteMapper estudianteMapper, CursoMapper cursoMapper) {
		this.estudianteMapper = estudianteMapper;
		this.cursoMapper = cursoMapper;
	}

	/**
	 * Convierte una entidad Practica en su DTO correspondiente.
	 * <p>
	 * Incluye información completa de Estudiante y Curso.
	 * </p>
	 *
	 * @param practica entidad Practica
	 * @return DTO PracticaDTO
	 */
	public PracticaDTO toDTO(Practica practica) {
		if (practica == null)
			return null;

		PracticaDTO dto = new PracticaDTO();
		dto.setId(practica.getId());
		dto.setTitulo(practica.getTitulo());
		dto.setDescripcion(practica.getDescripcion());
		dto.setEstado(practica.getEstado());
		dto.setEstudiante(estudianteMapper.toDTO(practica.getEstudiante()));
		dto.setCurso(cursoMapper.toDTO(practica.getCurso()));

		return dto;
	}

	/**
	 * Convierte un DTO PracticaDTO en su entidad correspondiente.
	 * <p>
	 * Incluye las relaciones completas con Estudiante y Curso mediante sus mappers.
	 * </p>
	 *
	 * @param dto DTO PracticaDTO
	 * @return entidad Practica
	 */
	public Practica toEntity(PracticaDTO dto) {
		if (dto == null)
			return null;

		Practica practica = new Practica();
		practica.setId(dto.getId());
		practica.setTitulo(dto.getTitulo());
		practica.setDescripcion(dto.getDescripcion());
		practica.setEstado(dto.getEstado());
		practica.setEstudiante(estudianteMapper.toEntity(dto.getEstudiante()));
		practica.setCurso(cursoMapper.toEntity(dto.getCurso()));

		return practica;
	}
}