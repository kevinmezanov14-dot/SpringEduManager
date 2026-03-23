package com.edu.manager.controllers;

import com.edu.manager.models.Practica;
import com.edu.manager.services.PracticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practicas")
public class PracticaRestController {

    private final PracticaService practicaService;

    public PracticaRestController(PracticaService practicaService) {
        this.practicaService = practicaService;
    }

    @GetMapping
    public List<Practica> listarTodos() {
        return practicaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Practica> buscarPorId(@PathVariable Long id) {
        Practica practica = practicaService.buscarPorId(id);
        if (practica == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(practica);
    }

    @PostMapping
    public ResponseEntity<Practica> crear(@RequestBody Practica practica) {
        practicaService.guardar(practica);
        return ResponseEntity.ok(practica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Practica> actualizar(@PathVariable Long id, @RequestBody Practica practica) {
        Practica existente = practicaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        practica.setId(id);
        practicaService.guardar(practica);
        return ResponseEntity.ok(practica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Practica existente = practicaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        practicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}