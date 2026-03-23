package com.edu.manager.controllers;

import com.edu.manager.models.Evaluacion;
import com.edu.manager.services.CursoService;
import com.edu.manager.services.EstudianteService;
import com.edu.manager.services.EvaluacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/evaluaciones")
public class EvaluacionController {

	private final EvaluacionService evaluacionService;
	private final EstudianteService estudianteService;
	private final CursoService cursoService;

	public EvaluacionController(EvaluacionService evaluacionService, EstudianteService estudianteService,
			CursoService cursoService) {
		this.evaluacionService = evaluacionService;
		this.estudianteService = estudianteService;
		this.cursoService = cursoService;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("evaluaciones", evaluacionService.listarTodos());
		return "evaluaciones/lista";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("evaluacion", new Evaluacion());
		model.addAttribute("estudiantes", estudianteService.listarTodos());
		model.addAttribute("cursos", cursoService.listarTodos());
		return "evaluaciones/formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Evaluacion evaluacion) {
		evaluacionService.guardar(evaluacion);
		return "redirect:/evaluaciones";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		evaluacionService.eliminar(id);
		return "redirect:/evaluaciones";
	}
}