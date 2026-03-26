package com.edu.manager.mappers;

import com.edu.manager.dtos.EvaluacionDTO;
import com.edu.manager.models.Evaluacion;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionMapper {

	private final EstudianteMapper estudianteMapper;
	private final CursoMapper cursoMapper;

	public EvaluacionMapper(EstudianteMapper estudianteMapper, CursoMapper cursoMapper) {
		this.estudianteMapper = estudianteMapper;
		this.cursoMapper = cursoMapper;
	}

	public EvaluacionDTO toDTO(Evaluacion evaluacion) {
		EvaluacionDTO dto = new EvaluacionDTO();
		dto.setId(evaluacion.getId());
		dto.setTitulo(evaluacion.getTitulo());
		dto.setDescripcion(evaluacion.getDescripcion());
		dto.setNota(evaluacion.getNota());

		// Mapear referencias
		dto.setEstudiante(estudianteMapper.toDTO(evaluacion.getEstudiante()));
		dto.setCurso(cursoMapper.toDTO(evaluacion.getCurso()));

		return dto;
	}

	public Evaluacion toEntity(EvaluacionDTO dto) {
		Evaluacion evaluacion = new Evaluacion();
		evaluacion.setId(dto.getId());
		evaluacion.setTitulo(dto.getTitulo());
		evaluacion.setDescripcion(dto.getDescripcion());
		evaluacion.setNota(dto.getNota());

		// Mapear referencias
		evaluacion.setEstudiante(estudianteMapper.toEntity(dto.getEstudiante()));
		evaluacion.setCurso(cursoMapper.toEntity(dto.getCurso()));

		return evaluacion;
	}
}