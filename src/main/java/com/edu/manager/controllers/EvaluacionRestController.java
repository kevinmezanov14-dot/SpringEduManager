package com.edu.manager.controllers;

import com.edu.manager.dtos.EvaluacionDTO;
import com.edu.manager.mappers.EvaluacionMapper;
import com.edu.manager.models.Evaluacion;
import com.edu.manager.services.EvaluacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionRestController {

	private final EvaluacionService evaluacionService;
	private final EvaluacionMapper evaluacionMapper;

	public EvaluacionRestController(EvaluacionService evaluacionService, EvaluacionMapper evaluacionMapper) {
		this.evaluacionService = evaluacionService;
		this.evaluacionMapper = evaluacionMapper;
	}

	@GetMapping
	public List<EvaluacionDTO> listarTodos() {
		return evaluacionService.listarTodos().stream().map(evaluacionMapper::toDTO).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EvaluacionDTO> buscarPorId(@PathVariable Long id) {
		Evaluacion evaluacion = evaluacionService.buscarPorId(id);
		if (evaluacion == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(evaluacionMapper.toDTO(evaluacion));
	}

	@PostMapping
	public ResponseEntity<EvaluacionDTO> crear(@RequestBody EvaluacionDTO dto) {
		Evaluacion evaluacion = evaluacionMapper.toEntity(dto);
		evaluacionService.guardar(evaluacion);
		return ResponseEntity.ok(evaluacionMapper.toDTO(evaluacion));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EvaluacionDTO> actualizar(@PathVariable Long id, @RequestBody EvaluacionDTO dto) {
		Evaluacion existente = evaluacionService.buscarPorId(id);
		if (existente == null)
			return ResponseEntity.notFound().build();

		existente.setTitulo(dto.getTitulo());
		existente.setDescripcion(dto.getDescripcion());
		existente.setNota(dto.getNota());
		existente.setEstudiante(dto.getEstudiante() != null ? evaluacionMapper.toEntity(dto).getEstudiante() : null);
		existente.setCurso(dto.getCurso() != null ? evaluacionMapper.toEntity(dto).getCurso() : null);

		evaluacionService.guardar(existente);
		return ResponseEntity.ok(evaluacionMapper.toDTO(existente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		Evaluacion existente = evaluacionService.buscarPorId(id);
		if (existente == null)
			return ResponseEntity.notFound().build();

		evaluacionService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}