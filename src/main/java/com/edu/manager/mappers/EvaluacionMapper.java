package com.edu.manager.mappers;

import com.edu.manager.dtos.EvaluacionDTO;
import com.edu.manager.models.*;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionMapper {

	public Evaluacion toEntity(EvaluacionDTO dto) {
		Evaluacion e = new Evaluacion();

		e.setId(dto.getId());
		e.setTitulo(dto.getTitulo());
		e.setDescripcion(dto.getDescripcion());
		e.setNota(dto.getNota());

		// 🔥 SOLO ID
		if (dto.getEstudianteId() != null) {
			Estudiante est = new Estudiante();
			est.setId(dto.getEstudianteId());
			e.setEstudiante(est);
		}

		if (dto.getCursoId() != null) {
			Curso cur = new Curso();
			cur.setId(dto.getCursoId());
			e.setCurso(cur);
		}

		return e;
	}

	public EvaluacionDTO toDTO(Evaluacion e) {
		EvaluacionDTO dto = new EvaluacionDTO();

		dto.setId(e.getId());
		dto.setTitulo(e.getTitulo());
		dto.setDescripcion(e.getDescripcion());
		dto.setNota(e.getNota());

		// 🔥 IDs + nombres
		if (e.getEstudiante() != null) {
			dto.setEstudianteId(e.getEstudiante().getId());
			dto.setEstudianteNombre(e.getEstudiante().getNombre() + " " + e.getEstudiante().getApellido());
		}

		if (e.getCurso() != null) {
			dto.setCursoId(e.getCurso().getId());
			dto.setCursoNombre(e.getCurso().getNombre());
		}

		return dto;
	}
}