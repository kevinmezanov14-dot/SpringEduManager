package com.edu.manager.controllers;

import com.edu.manager.dtos.PracticaDTO;
import com.edu.manager.mappers.PracticaMapper;
import com.edu.manager.models.Practica;
import com.edu.manager.services.PracticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/practicas")
public class PracticaRestController {

    private final PracticaService practicaService;
    private final PracticaMapper practicaMapper;

    public PracticaRestController(PracticaService practicaService, PracticaMapper practicaMapper) {
        this.practicaService = practicaService;
        this.practicaMapper = practicaMapper;
    }

    // LISTAR
    @GetMapping
    public List<PracticaDTO> listarTodos() {
        return practicaService.listarTodos().stream()
                .map(practicaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Practica practica = practicaService.buscarPorId(id);
        if (practica == null) {
            return ResponseEntity.status(404).body("Práctica no encontrada");
        }
        return ResponseEntity.ok(practicaMapper.toDTO(practica));
    }

    // CREAR
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PracticaDTO dto, BindingResult result) {
        // Validaciones automáticas
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(obtenerErrores(result));
        }

        // Validación adicional de estudiante y curso
        if (dto.getEstudiante() == null || dto.getEstudiante().getId() == null) {
            return ResponseEntity.badRequest().body(Map.of("estudiante", "El estudiante es obligatorio"));
        }
        if (dto.getCurso() == null || dto.getCurso().getId() == null) {
            return ResponseEntity.badRequest().body(Map.of("curso", "El curso es obligatorio"));
        }

        Practica practica = practicaMapper.toEntity(dto);
        practicaService.guardar(practica);
        return ResponseEntity.status(201).body(practicaMapper.toDTO(practica));
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody PracticaDTO dto,
                                        BindingResult result) {

        Practica existente = practicaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.status(404).body("Práctica no encontrada");
        }

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(obtenerErrores(result));
        }

        if (dto.getEstudiante() == null || dto.getEstudiante().getId() == null) {
            return ResponseEntity.badRequest().body(Map.of("estudiante", "El estudiante es obligatorio"));
        }
        if (dto.getCurso() == null || dto.getCurso().getId() == null) {
            return ResponseEntity.badRequest().body(Map.of("curso", "El curso es obligatorio"));
        }

        // Actualización de campos
        existente.setTitulo(dto.getTitulo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setEstudiante(practicaMapper.toEntity(dto).getEstudiante());
        existente.setCurso(practicaMapper.toEntity(dto).getCurso());

        practicaService.guardar(existente);
        return ResponseEntity.ok(practicaMapper.toDTO(existente));
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Practica existente = practicaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.status(404).body("Práctica no encontrada");
        }

        practicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // MÉTODO PARA FORMATEAR ERRORES DE VALIDACIÓN
    private Map<String, String> obtenerErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
        return errores;
    }
}