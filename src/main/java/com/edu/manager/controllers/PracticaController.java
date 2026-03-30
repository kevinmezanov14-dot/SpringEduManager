package com.edu.manager.controllers;

import com.edu.manager.models.Practica;
import com.edu.manager.services.CursoService;
import com.edu.manager.services.EstudianteService;
import com.edu.manager.services.PracticaService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC para manejo de prácticas de estudiantes.
 * <p>
 * Rutas principales: - GET /practicas → lista todas las prácticas - GET
 * /practicas/nuevo → muestra formulario para nueva práctica - POST
 * /practicas/guardar → guarda nueva práctica o actualiza existente - GET
 * /practicas/editar/{id} → muestra formulario para editar práctica existente -
 * GET /practicas/eliminar/{id} → elimina práctica
 */
@Controller
@RequestMapping("/practicas")
public class PracticaController {

	private final PracticaService practicaService;
	private final EstudianteService estudianteService;
	private final CursoService cursoService;

	public PracticaController(PracticaService practicaService, EstudianteService estudianteService,
			CursoService cursoService) {
		this.practicaService = practicaService;
		this.estudianteService = estudianteService;
		this.cursoService = cursoService;
	}

	/**
	 * Lista todas las prácticas.
	 *
	 * @param model modelo para pasar datos a la vista
	 * @return nombre de la vista "practicas/lista"
	 */
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("practicas", practicaService.listarTodos());
		return "practicas/lista";
	}

	/**
	 * Muestra formulario para crear nueva práctica.
	 *
	 * @param model modelo para pasar datos a la vista
	 * @return nombre de la vista "practicas/formulario"
	 */
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("practica", new Practica());
		model.addAttribute("estudiantes", estudianteService.listarTodos());
		model.addAttribute("cursos", cursoService.listarTodos());
		return "practicas/formulario";
	}

	/**
	 * Guarda una práctica nueva o actualiza una existente.
	 *
	 * @param practica objeto Practica con datos del formulario
	 * @param result   resultados de validación
	 * @param model    modelo para pasar datos a la vista
	 * @return redirige a "/practicas" si es exitoso, o recarga formulario si hay
	 *         errores
	 */
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute Practica practica, BindingResult result, Model model) {

		// Validación de campos obligatorios
		if (practica.getEstudiante() == null || practica.getEstudiante().getId() == null) {
			result.rejectValue("estudiante.id", "NotNull", "El estudiante es obligatorio");
		}

		if (practica.getCurso() == null || practica.getCurso().getId() == null) {
			result.rejectValue("curso.id", "NotNull", "El curso es obligatorio");
		}

		if (result.hasErrors()) {
			model.addAttribute("estudiantes", estudianteService.listarTodos());
			model.addAttribute("cursos", cursoService.listarTodos());
			return "practicas/formulario";
		}

		// Cargar las entidades completas desde la DB antes de guardar
		practica.setEstudiante(estudianteService.buscarPorId(practica.getEstudiante().getId()));
		practica.setCurso(cursoService.buscarPorId(practica.getCurso().getId()));

		practicaService.guardar(practica);
		return "redirect:/practicas";
	}

	/**
	 * Muestra formulario para editar una práctica existente.
	 *
	 * @param id    id de la práctica
	 * @param model modelo para pasar datos a la vista
	 * @return nombre de la vista "practicas/formulario" o redirige a lista si no
	 *         existe
	 */
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Practica practica = practicaService.buscarPorId(id);
		if (practica == null) {
			return "redirect:/practicas";
		}
		model.addAttribute("practica", practica);
		model.addAttribute("estudiantes", estudianteService.listarTodos());
		model.addAttribute("cursos", cursoService.listarTodos());
		return "practicas/formulario";
	}

	/**
	 * Elimina una práctica por su id.
	 *
	 * @param id id de la práctica
	 * @return redirige a "/practicas"
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		practicaService.eliminar(id);
		return "redirect:/practicas";
	}
}