package com.edu.manager.controllers;

import com.edu.manager.dtos.CursoDTO;
import com.edu.manager.mappers.CursoMapper;
import com.edu.manager.models.Curso;
import com.edu.manager.services.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

	private final CursoService cursoService;
	private final CursoMapper cursoMapper;

	public CursoRestController(CursoService cursoService, CursoMapper cursoMapper) {
		this.cursoService = cursoService;
		this.cursoMapper = cursoMapper;
	}

	@GetMapping
	public List<CursoDTO> listarTodos() {
		return cursoService.listarTodos().stream().map(cursoMapper::toDTO).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CursoDTO> buscarPorId(@PathVariable Long id) {
		Curso curso = cursoService.buscarPorId(id);
		if (curso == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cursoMapper.toDTO(curso));
	}

	@PostMapping
	public ResponseEntity<CursoDTO> crear(@RequestBody CursoDTO dto) {
		Curso curso = cursoMapper.toEntity(dto);
		cursoService.guardar(curso);
		return ResponseEntity.ok(cursoMapper.toDTO(curso));
	}

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

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) {
		boolean eliminado = cursoService.eliminarSiNoTieneEvaluaciones(id);
		if (!eliminado)
			return ResponseEntity.status(409).body("No se puede eliminar: tiene evaluaciones asociadas");
		return ResponseEntity.noContent().build();
	}
}