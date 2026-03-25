package com.edu.manager.controllers;

import com.edu.manager.models.Usuario;
import com.edu.manager.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

	private final UsuarioService usuarioService;

	public LoginController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
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

			// mantener datos en el formulario
			model.addAttribute("username", username);

			return "registro";
		}
	}
}