package com.edu.manager.controllers;

import com.edu.manager.dtos.UsuarioDTO;
import com.edu.manager.mappers.UsuarioMapper;
import com.edu.manager.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios.
 * <p>
 * Endpoints principales: - GET /api/users → lista todos los usuarios
 */
@RestController
@RequestMapping("/api/users")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final UsuarioMapper usuarioMapper;

	public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;
	}

	/**
	 * Lista todos los usuarios registrados.
	 *
	 * @return lista de UsuarioDTO
	 */
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
		List<UsuarioDTO> lista = usuarioService.listarTodos().stream().map(usuarioMapper::toDto).toList();

		return ResponseEntity.ok(lista);
	}
}