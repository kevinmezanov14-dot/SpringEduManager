package com.edu.manager.controllers;

import com.edu.manager.models.Practica;
import com.edu.manager.services.CursoService;
import com.edu.manager.services.EstudianteService;
import com.edu.manager.services.PracticaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("practicas", practicaService.listarTodos());
		return "practicas/lista";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("practica", new Practica());
		model.addAttribute("estudiantes", estudianteService.listarTodos());
		model.addAttribute("cursos", cursoService.listarTodos());
		return "practicas/formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Practica practica) {
		practicaService.guardar(practica);
		return "redirect:/practicas";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		practicaService.eliminar(id);
		return "redirect:/practicas";
	}
}