package com.edu.manager.mappers;

import com.edu.manager.dtos.CursoDTO;
import com.edu.manager.models.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

	public CursoDTO toDTO(Curso curso) {
		CursoDTO dto = new CursoDTO();
		dto.setId(curso.getId());
		dto.setNombre(curso.getNombre());
		dto.setDescripcion(curso.getDescripcion());
		dto.setInstructor(curso.getInstructor());
		return dto;
	}

	public Curso toEntity(CursoDTO dto) {
		Curso curso = new Curso();
		curso.setId(dto.getId());
		curso.setNombre(dto.getNombre());
		curso.setDescripcion(dto.getDescripcion());
		curso.setInstructor(dto.getInstructor());
		return curso;
	}
}