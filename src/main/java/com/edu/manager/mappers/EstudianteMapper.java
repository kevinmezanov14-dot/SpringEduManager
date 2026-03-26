package com.edu.manager.mappers;

import com.edu.manager.dtos.EstudianteDTO;
import com.edu.manager.models.Estudiante;
import org.springframework.stereotype.Component;

@Component
public class EstudianteMapper {

	public EstudianteDTO toDTO(Estudiante estudiante) {
		EstudianteDTO dto = new EstudianteDTO();
		dto.setId(estudiante.getId());
		dto.setNombre(estudiante.getNombre());
		dto.setApellido(estudiante.getApellido());
		dto.setEmail(estudiante.getEmail());
		return dto;
	}

	public Estudiante toEntity(EstudianteDTO dto) {
		Estudiante estudiante = new Estudiante();
		estudiante.setId(dto.getId());
		estudiante.setNombre(dto.getNombre());
		estudiante.setApellido(dto.getApellido());
		estudiante.setEmail(dto.getEmail());
		return estudiante;
	}
}