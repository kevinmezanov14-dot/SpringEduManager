package com.edu.manager.controllers;

import com.edu.manager.models.Usuario;
import com.edu.manager.services.UsuarioService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC para manejo de login, registro y páginas principales.
 * <p>
 * Rutas principales: - GET /login → muestra formulario de login - POST /login →
 * procesa login y autentica al usuario - GET / → página principal - GET
 * /registro → muestra formulario de registro - POST /registro → crea un nuevo
 * usuario
 */
@Controller
public class LoginController {

	private final UsuarioService usuarioService;
	private final AuthenticationManager authenticationManager;

	public LoginController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
		this.usuarioService = usuarioService;
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Muestra el formulario de login.
	 *
	 * @return nombre de la vista "login"
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * Procesa el login del usuario.
	 *
	 * @param username nombre de usuario
	 * @param password contraseña
	 * @param model    modelo para pasar datos a la vista
	 * @return redirige a "/" si es exitoso, o recarga "login" con error si falla
	 */
	@PostMapping("/login")
	public String procesarLogin(@RequestParam String username, @RequestParam String password, Model model) {
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			// guardar autenticación en contexto de seguridad (simula sesión)
			SecurityContextHolder.getContext().setAuthentication(auth);

			return "redirect:/";

		} catch (BadCredentialsException e) {
			model.addAttribute("error", "Credenciales incorrectas");
			model.addAttribute("username", username);
			return "login";
		}
	}

	/**
	 * Página principal del sistema.
	 *
	 * @return nombre de la vista "index"
	 */
	@GetMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * Muestra el formulario de registro de usuario.
	 *
	 * @return nombre de la vista "registro"
	 */
	@GetMapping("/registro")
	public String registro() {
		return "registro";
	}

	/**
	 * Procesa el registro de un nuevo usuario.
	 *
	 * @param username nombre de usuario
	 * @param password contraseña
	 * @param model    modelo para pasar datos a la vista
	 * @return redirige a "/login" si es exitoso, o recarga "registro" con error si
	 *         falla
	 */
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