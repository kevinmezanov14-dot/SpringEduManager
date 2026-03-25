package com.edu.manager.controllers;

import com.edu.manager.models.Usuario;
import com.edu.manager.security.JwtUtil;
import com.edu.manager.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UsuarioService usuarioService;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioService usuarioService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.usuarioService = usuarioService;
	}

	// LOGIN
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {

		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciales.get("username"), credenciales.get("password")));

			String token = jwtUtil.generarToken(authentication.getName());

			Map<String, String> response = new HashMap<>();
			response.put("token", token);
			response.put("usuario", authentication.getName());

			return ResponseEntity.ok(response);

		} catch (BadCredentialsException e) {
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}
	}

	// REGISTRO
	@PostMapping("/registro")
	public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {

		try {
			Usuario nuevo = usuarioService.registrarUsuario(usuario);
			return ResponseEntity.ok(nuevo);

		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}