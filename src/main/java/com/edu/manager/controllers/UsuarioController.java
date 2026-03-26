package com.edu.manager.controllers;

import com.edu.manager.dtos.UsuarioDTO;
import com.edu.manager.mappers.UsuarioMapper;
import com.edu.manager.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService,
                             UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {

        List<UsuarioDTO> lista = usuarioService.listarTodos()
                .stream()
                .map(usuarioMapper::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }
}