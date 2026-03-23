package com.edu.manager.controllers;

import com.edu.manager.models.Evaluacion;
import com.edu.manager.services.EvaluacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionRestController {

    private final EvaluacionService evaluacionService;

    public EvaluacionRestController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping
    public List<Evaluacion> listarTodos() {
        return evaluacionService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> buscarPorId(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionService.buscarPorId(id);
        if (evaluacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evaluacion);
    }

    @PostMapping
    public ResponseEntity<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        evaluacionService.guardar(evaluacion);
        return ResponseEntity.ok(evaluacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        Evaluacion existente = evaluacionService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        evaluacion.setId(id);
        evaluacionService.guardar(evaluacion);
        return ResponseEntity.ok(evaluacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Evaluacion existente = evaluacionService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        evaluacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}