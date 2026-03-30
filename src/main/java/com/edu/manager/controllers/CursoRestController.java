package com.edu.manager.controllers;

import com.edu.manager.dtos.CursoDTO;
import com.edu.manager.mappers.CursoMapper;
import com.edu.manager.models.Curso;
import com.edu.manager.services.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gestión de cursos.
 * <p>
 * Exposición bajo /api/cursos - GET /api/cursos → listar todos los cursos - GET
 * /api/cursos/{id} → obtener curso por ID - POST /api/cursos → crear un nuevo
 * curso - PUT /api/cursos/{id} → actualizar un curso existente - DELETE
 * /api/cursos/{id} → eliminar un curso si no tiene dependencias
 */
@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

	private final CursoService cursoService;
	private final CursoMapper cursoMapper;

	public CursoRestController(CursoService cursoService, CursoMapper cursoMapper) {
		this.cursoService = cursoService;
		this.cursoMapper = cursoMapper;
	}

	/**
	 * Lista todos los cursos.
	 *
	 * @return lista de CursoDTO
	 */
	@GetMapping
	public List<CursoDTO> listarTodos() {
		return cursoService.listarTodos().stream().map(cursoMapper::toDTO).collect(Collectors.toList());
	}

	/**
	 * Obtiene un curso por su ID.
	 *
	 * @param id ID del curso
	 * @return CursoDTO si existe, 404 si no
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CursoDTO> buscarPorId(@PathVariable Long id) {
		Curso curso = cursoService.buscarPorId(id);
		if (curso == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cursoMapper.toDTO(curso));
	}

	/**
	 * Crea un nuevo curso.
	 *
	 * @param dto datos del curso
	 * @return CursoDTO creado
	 */
	@PostMapping
	public ResponseEntity<CursoDTO> crear(@RequestBody CursoDTO dto) {
		Curso curso = cursoMapper.toEntity(dto);
		cursoService.guardar(curso);
		return ResponseEntity.ok(cursoMapper.toDTO(curso));
	}

	/**
	 * Actualiza un curso existente.
	 *
	 * @param id  ID del curso a actualizar
	 * @param dto datos actualizados
	 * @return CursoDTO actualizado o 404 si no existe
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CursoDTO> actualizar(@PathVariable Long id, @RequestBody CursoDTO dto) {
		Curso existente = cursoService.buscarPorId(id);
		if (existente == null)
			return ResponseEntity.notFound().build();

		existente.setNombre(dto.getNombre());
		existente.setDescripcion(dto.getDescripcion());
		existente.setInstructor(dto.getInstructor());

		cursoService.guardar(existente);
		return ResponseEntity.ok(cursoMapper.toDTO(existente));
	}

	/**
	 * Elimina un curso si no tiene evaluaciones ni prácticas asociadas.
	 *
	 * @param id ID del curso
	 * @return 204 si eliminado, 409 si tiene dependencias
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) {
		boolean eliminado = cursoService.eliminarSiNoTieneDependencias(id);

		if (!eliminado) {
			return ResponseEntity.status(409).body("No se puede eliminar: tiene registros asociados");
		}

		return ResponseEntity.noContent().build();
	}
}