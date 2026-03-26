package com.edu.manager.controllers;

import com.edu.manager.models.Usuario;
import com.edu.manager.services.UsuarioService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

	private final UsuarioService usuarioService;
	private final AuthenticationManager authenticationManager;

	public LoginController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
		this.usuarioService = usuarioService;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String procesarLogin(@RequestParam String username, @RequestParam String password, Model model) {

		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			// 🔥 IMPORTANTE: guardar en contexto (simular sesión)
			SecurityContextHolder.getContext().setAuthentication(auth);

			return "redirect:/";

		} catch (BadCredentialsException e) {
			model.addAttribute("error", "Credenciales incorrectas");
			model.addAttribute("username", username);
			return "login";
		}
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/registro")
	public String registro() {
		return "registro";
	}

	@PostMapping("/registro")
	public String guardarUsuario(@RequestParam String username, @RequestParam String password, Model model) {

		try {
			Usuario usuario = new Usuario();
			usuario.setUsername(username.trim());
			usuario.setPassword(password.trim());

			usuarioService.registrarUsuario(usuario);

			return "redirect:/login";

		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("username", username);
			return "registro";
		}
	}
}