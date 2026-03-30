package com.edu.manager.controllers;

import com.edu.manager.dtos.AuthResponseDTO;
import com.edu.manager.dtos.LoginRequestDTO;
import com.edu.manager.dtos.UsuarioDTO;
import com.edu.manager.dtos.UsuarioRegistroDTO;
import com.edu.manager.mappers.UsuarioMapper;
import com.edu.manager.models.Usuario;
import com.edu.manager.security.JwtUtil;
import com.edu.manager.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para autenticación y registro de usuarios.
 * <p>
 * Exposición bajo /api/auth - POST /login → realiza login y retorna JWT - POST
 * /registro → registra un nuevo usuario
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UsuarioService usuarioService;
	private final UsuarioMapper usuarioMapper;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioService usuarioService,
			UsuarioMapper usuarioMapper) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.usuarioService = usuarioService;
		this.usuarioMapper = usuarioMapper;
	}

	/**
	 * Endpoint para login de usuario.
	 * <p>
	 * Recibe JSON con username y password. Retorna JWT y datos del usuario si es
	 * exitoso.
	 *
	 * @param request DTO con username y password
	 * @return AuthResponseDTO con token, username y role o error 401
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			String token = jwtUtil.generarToken(userDetails);

			String role = userDetails.getAuthorities().iterator().next().getAuthority();

			AuthResponseDTO response = new AuthResponseDTO(token, userDetails.getUsername(), role);

			return ResponseEntity.ok(response);

		} catch (BadCredentialsException e) {
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}
	}

	/**
	 * Endpoint para registrar un nuevo usuario.
	 * <p>
	 * El primer usuario registrado será ROLE_ADMIN por defecto, los siguientes
	 * serán ROLE_USER.
	 *
	 * @param dto DTO con username y password
	 * @return UsuarioDTO con username y role o mensaje de error
	 */
	@PostMapping("/registro")
	public ResponseEntity<?> registrar(@RequestBody UsuarioRegistroDTO dto) {
		try {
			Usuario usuario = usuarioMapper.toEntity(dto);

			usuario.setRole("ROLE_USER");

			Usuario guardado = usuarioService.registrarUsuario(usuario);

			UsuarioDTO response = usuarioMapper.toDto(guardado);

			return ResponseEntity.ok(response);

		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}