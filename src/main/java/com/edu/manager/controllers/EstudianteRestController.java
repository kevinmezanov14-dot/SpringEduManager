package com.edu.manager.controllers;

import com.edu.manager.models.Estudiante;
import com.edu.manager.services.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    public EstudianteRestController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<Estudiante> listarTodos() {
        return estudianteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        if (estudiante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudiante);
    }

    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        estudianteService.guardar(estudiante);
        return ResponseEntity.ok(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        Estudiante existente = estudianteService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        estudiante.setId(id);
        estudianteService.guardar(estudiante);
        return ResponseEntity.ok(estudiante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Estudiante existente = estudianteService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}