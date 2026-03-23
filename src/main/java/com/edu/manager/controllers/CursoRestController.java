package com.edu.manager.controllers;

import com.edu.manager.models.Curso;
import com.edu.manager.services.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

    private final CursoService cursoService;

    public CursoRestController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listarTodos() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> crear(@RequestBody Curso curso) {
        cursoService.guardar(curso);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        Curso existente = cursoService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        curso.setId(id);
        cursoService.guardar(curso);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Curso existente = cursoService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}