package com.edu.manager.controllers;

import com.edu.manager.dtos.EstudianteDTO;
import com.edu.manager.mappers.EstudianteMapper;
import com.edu.manager.models.Estudiante;
import com.edu.manager.services.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

	private final EstudianteService estudianteService;
	private final EstudianteMapper estudianteMapper;

	public EstudianteRestController(EstudianteService estudianteService, EstudianteMapper estudianteMapper) {
		this.estudianteService = estudianteService;
		this.estudianteMapper = estudianteMapper;
	}

	@GetMapping
	public List<EstudianteDTO> listarTodos() {
		return estudianteService.listarTodos().stream().map(estudianteMapper::toDTO).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstudianteDTO> buscarPorId(@PathVariable Long id) {
		Estudiante estudiante = estudianteService.buscarPorId(id);
		if (estudiante == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(estudianteMapper.toDTO(estudiante));
	}

	@PostMapping
	public ResponseEntity<EstudianteDTO> crear(@RequestBody EstudianteDTO dto) {
		Estudiante estudiante = estudianteMapper.toEntity(dto);
		estudianteService.guardar(estudiante);
		return ResponseEntity.ok(estudianteMapper.toDTO(estudiante));
	}

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

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		Estudiante existente = estudianteService.buscarPorId(id);
		if (existente == null)
			return ResponseEntity.notFound().build();

		estudianteService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}