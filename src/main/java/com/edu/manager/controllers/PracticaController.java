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

	// LISTAR
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("practicas", practicaService.listarTodos());
		return "practicas/lista";
	}

	// NUEVO FORMULARIO
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("practica", new Practica());
		model.addAttribute("estudiantes", estudianteService.listarTodos());
		model.addAttribute("cursos", cursoService.listarTodos());
		return "practicas/formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute Practica practica, BindingResult result, Model model) {

		// Validación de campos
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

		// Cargar las entidades reales desde la DB
		practica.setEstudiante(estudianteService.buscarPorId(practica.getEstudiante().getId()));
		practica.setCurso(cursoService.buscarPorId(practica.getCurso().getId()));

		practicaService.guardar(practica);
		return "redirect:/practicas";
	}

	// ELIMINAR
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		practicaService.eliminar(id);
		return "redirect:/practicas";
	}

	// EDITAR FORMULARIO
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
}