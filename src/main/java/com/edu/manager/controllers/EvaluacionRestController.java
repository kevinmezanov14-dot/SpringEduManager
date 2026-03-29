package com.edu.manager.controllers;

import com.edu.manager.dtos.EvaluacionDTO;
import com.edu.manager.mappers.EvaluacionMapper;
import com.edu.manager.models.Evaluacion;
import com.edu.manager.services.EvaluacionService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	// LISTAR
	@GetMapping
	public List<EvaluacionDTO> listarTodos() {
		return evaluacionService.listarTodos().stream().map(evaluacionMapper::toDTO).collect(Collectors.toList());
	}

	// BUSCAR POR ID
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Evaluacion evaluacion = evaluacionService.buscarPorId(id);

		if (evaluacion == null) {
			return ResponseEntity.status(404).body("Evaluación no encontrada");
		}

		return ResponseEntity.ok(evaluacionMapper.toDTO(evaluacion));
	}

	// CREAR
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody EvaluacionDTO dto, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(obtenerErrores(result));
		}

		Evaluacion evaluacion = evaluacionMapper.toEntity(dto);
		evaluacionService.guardar(evaluacion);

		return ResponseEntity.status(201).body(evaluacionMapper.toDTO(evaluacion));
	}

	// ACTUALIZAR
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody EvaluacionDTO dto,
			BindingResult result) {

		Evaluacion existente = evaluacionService.buscarPorId(id);

		if (existente == null) {
			return ResponseEntity.status(404).body("Evaluación no encontrada");
		}

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(obtenerErrores(result));
		}

		// MAPEO PRO (evita duplicación)
		Evaluacion nueva = evaluacionMapper.toEntity(dto);

		existente.setTitulo(nueva.getTitulo());
		existente.setDescripcion(nueva.getDescripcion());
		existente.setNota(nueva.getNota());
		existente.setEstudiante(nueva.getEstudiante());
		existente.setCurso(nueva.getCurso());

		evaluacionService.guardar(existente);

		return ResponseEntity.ok(evaluacionMapper.toDTO(existente));
	}

	// ELIMINAR
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		Evaluacion existente = evaluacionService.buscarPorId(id);

		if (existente == null) {
			return ResponseEntity.status(404).body("Evaluación no encontrada");
		}

		evaluacionService.eliminar(id);

		return ResponseEntity.noContent().build();
	}

	// FORMATEO DE ERRORES
	private Map<String, String> obtenerErrores(BindingResult result) {
		Map<String, String> errores = new HashMap<>();

		result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

		return errores;
	}
}