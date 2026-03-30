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

/**
 * Controlador REST para la gestión de evaluaciones.
 * <p>
 * Exposición bajo /api/evaluaciones - GET /api/evaluaciones → lista todas las
 * evaluaciones - GET /api/evaluaciones/{id} → obtiene una evaluación por ID -
 * POST /api/evaluaciones → crea una nueva evaluación - PUT
 * /api/evaluaciones/{id} → actualiza una evaluación existente - DELETE
 * /api/evaluaciones/{id} → elimina una evaluación
 */
@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionRestController {

	private final EvaluacionService evaluacionService;
	private final EvaluacionMapper evaluacionMapper;

	public EvaluacionRestController(EvaluacionService evaluacionService, EvaluacionMapper evaluacionMapper) {
		this.evaluacionService = evaluacionService;
		this.evaluacionMapper = evaluacionMapper;
	}

	/**
	 * Lista todas las evaluaciones.
	 *
	 * @return lista de EvaluacionDTO
	 */
	@GetMapping
	public List<EvaluacionDTO> listarTodos() {
		return evaluacionService.listarTodos().stream().map(evaluacionMapper::toDTO).collect(Collectors.toList());
	}

	/**
	 * Obtiene una evaluación por su ID.
	 *
	 * @param id ID de la evaluación
	 * @return EvaluacionDTO o 404 si no existe
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Evaluacion evaluacion = evaluacionService.buscarPorId(id);

		if (evaluacion == null) {
			return ResponseEntity.status(404).body("Evaluación no encontrada");
		}

		return ResponseEntity.ok(evaluacionMapper.toDTO(evaluacion));
	}

	/**
	 * Crea una nueva evaluación.
	 *
	 * @param dto    datos de la evaluación
	 * @param result binding result de validación
	 * @return EvaluacionDTO creado o errores de validación
	 */
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody EvaluacionDTO dto, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(obtenerErrores(result));
		}

		Evaluacion evaluacion = evaluacionMapper.toEntity(dto);
		evaluacionService.guardar(evaluacion);

		return ResponseEntity.status(201).body(evaluacionMapper.toDTO(evaluacion));
	}

	/**
	 * Actualiza una evaluación existente.
	 *
	 * @param id     ID de la evaluación
	 * @param dto    nuevos datos
	 * @param result binding result de validación
	 * @return EvaluacionDTO actualizado o errores/404
	 */
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

		// Mapear datos del DTO a la entidad existente
		Evaluacion nueva = evaluacionMapper.toEntity(dto);

		existente.setTitulo(nueva.getTitulo());
		existente.setDescripcion(nueva.getDescripcion());
		existente.setNota(nueva.getNota());
		existente.setEstudiante(nueva.getEstudiante());
		existente.setCurso(nueva.getCurso());

		evaluacionService.guardar(existente);

		return ResponseEntity.ok(evaluacionMapper.toDTO(existente));
	}

	/**
	 * Elimina una evaluación por ID.
	 *
	 * @param id ID de la evaluación
	 * @return 204 No Content si se eliminó, 404 si no existe
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		Evaluacion existente = evaluacionService.buscarPorId(id);

		if (existente == null) {
			return ResponseEntity.status(404).body("Evaluación no encontrada");
		}

		evaluacionService.eliminar(id);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Convierte errores de validación en un mapa campo → mensaje.
	 *
	 * @param result binding result con errores
	 * @return mapa de errores
	 */
	private Map<String, String> obtenerErrores(BindingResult result) {
		Map<String, String> errores = new HashMap<>();
		result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
		return errores;
	}
}