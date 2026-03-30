package com.edu.manager.controllers;

import com.edu.manager.dtos.EstudianteDTO;
import com.edu.manager.mappers.EstudianteMapper;
import com.edu.manager.models.Estudiante;
import com.edu.manager.services.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gestión de estudiantes.
 * <p>
 * Exposición bajo /api/estudiantes - GET /api/estudiantes → listar todos los
 * estudiantes - GET /api/estudiantes/{id} → obtener un estudiante por ID - POST
 * /api/estudiantes → crear un nuevo estudiante - PUT /api/estudiantes/{id} →
 * actualizar un estudiante existente - DELETE /api/estudiantes/{id} → eliminar
 * un estudiante
 */
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

	private final EstudianteService estudianteService;
	private final EstudianteMapper estudianteMapper;

	public EstudianteRestController(EstudianteService estudianteService, EstudianteMapper estudianteMapper) {
		this.estudianteService = estudianteService;
		this.estudianteMapper = estudianteMapper;
	}

	/**
	 * Lista todos los estudiantes.
	 *
	 * @return lista de EstudianteDTO
	 */
	@GetMapping
	public List<EstudianteDTO> listarTodos() {
		return estudianteService.listarTodos().stream().map(estudianteMapper::toDTO).collect(Collectors.toList());
	}

	/**
	 * Obtiene un estudiante por ID.
	 *
	 * @param id ID del estudiante
	 * @return ResponseEntity con el estudiante o 404 si no existe
	 */
	@GetMapping("/{id}")
	public ResponseEntity<EstudianteDTO> buscarPorId(@PathVariable Long id) {
		Estudiante estudiante = estudianteService.buscarPorId(id);
		if (estudiante == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(estudianteMapper.toDTO(estudiante));
	}

	/**
	 * Crea un nuevo estudiante.
	 *
	 * @param dto datos del estudiante
	 * @return ResponseEntity con el estudiante creado
	 */
	@PostMapping
	public ResponseEntity<EstudianteDTO> crear(@RequestBody EstudianteDTO dto) {
		Estudiante estudiante = estudianteMapper.toEntity(dto);
		estudianteService.guardar(estudiante);
		return ResponseEntity.ok(estudianteMapper.toDTO(estudiante));
	}

	/**
	 * Actualiza un estudiante existente.
	 *
	 * @param id  ID del estudiante
	 * @param dto datos actualizados
	 * @return ResponseEntity con el estudiante actualizado o 404 si no existe
	 */
	@PutMapping("/{id}")
	public ResponseEntity<EstudianteDTO> actualizar(@PathVariable Long id, @RequestBody EstudianteDTO dto) {
		Estudiante existente = estudianteService.buscarPorId(id);
		if (existente == null)
			return ResponseEntity.notFound().build();

		existente.setNombre(dto.getNombre());
		existente.setApellido(dto.getApellido());
		existente.setEmail(dto.getEmail());

		estudianteService.guardar(existente);
		return ResponseEntity.ok(estudianteMapper.toDTO(existente));
	}

	/**
	 * Elimina un estudiante por ID.
	 *
	 * @param id ID del estudiante
	 * @return ResponseEntity 204 si se eliminó, 404 si no existe
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		Estudiante existente = estudianteService.buscarPorId(id);
		if (existente == null)
			return ResponseEntity.notFound().build();

		estudianteService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}