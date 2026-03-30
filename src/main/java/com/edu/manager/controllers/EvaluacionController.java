package com.edu.manager.controllers;

import com.edu.manager.dtos.EvaluacionDTO;
import com.edu.manager.mappers.CursoMapper;
import com.edu.manager.mappers.EstudianteMapper;
import com.edu.manager.mappers.EvaluacionMapper;
import com.edu.manager.models.Evaluacion;
import com.edu.manager.services.CursoService;
import com.edu.manager.services.EstudianteService;
import com.edu.manager.services.EvaluacionService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Controlador MVC para gestión de evaluaciones.
 * <p>
 * Exposición bajo /evaluaciones - GET /evaluaciones → lista todas las
 * evaluaciones - GET /evaluaciones/nuevo → muestra el formulario para crear una
 * nueva evaluación - POST /evaluaciones/guardar → guarda la evaluación nueva o
 * editada - GET /evaluaciones/editar/{id} → muestra formulario para editar una
 * evaluación existente - GET /evaluaciones/eliminar/{id} → elimina una
 * evaluación
 */
@Controller
@RequestMapping("/evaluaciones")
public class EvaluacionController {

	private final EvaluacionService evaluacionService;
	private final EstudianteService estudianteService;
	private final CursoService cursoService;
	private final EvaluacionMapper evaluacionMapper;
	private final EstudianteMapper estudianteMapper;
	private final CursoMapper cursoMapper;

	public EvaluacionController(EvaluacionService evaluacionService, EstudianteService estudianteService,
			CursoService cursoService, EvaluacionMapper evaluacionMapper, EstudianteMapper estudianteMapper,
			CursoMapper cursoMapper) {
		this.evaluacionService = evaluacionService;
		this.estudianteService = estudianteService;
		this.cursoService = cursoService;
		this.evaluacionMapper = evaluacionMapper;
		this.estudianteMapper = estudianteMapper;
		this.cursoMapper = cursoMapper;
	}

	/**
	 * Lista todas las evaluaciones.
	 *
	 * @param model modelo de la vista
	 * @return vista "evaluaciones/lista"
	 */
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("evaluaciones",
				evaluacionService.listarTodos().stream().map(evaluacionMapper::toDTO).collect(Collectors.toList()));
		return "evaluaciones/lista";
	}

	/**
	 * Muestra formulario para crear una nueva evaluación.
	 *
	 * @param model modelo de la vista
	 * @return vista "evaluaciones/formulario"
	 */
	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("evaluacion", new EvaluacionDTO());

		model.addAttribute("estudiantes",
				estudianteService.listarTodos().stream().map(estudianteMapper::toDTO).collect(Collectors.toList()));

		model.addAttribute("cursos",
				cursoService.listarTodos().stream().map(cursoMapper::toDTO).collect(Collectors.toList()));

		return "evaluaciones/formulario";
	}

	/**
	 * Guarda una evaluación (nueva o editada).
	 *
	 * @param dto    datos de la evaluación
	 * @param result binding result de validación
	 * @param model  modelo de la vista
	 * @return redirect a la lista o vuelve al formulario si hay errores
	 */
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute("evaluacion") EvaluacionDTO dto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("evaluacion", dto);
			model.addAttribute("estudiantes",
					estudianteService.listarTodos().stream().map(estudianteMapper::toDTO).collect(Collectors.toList()));
			model.addAttribute("cursos",
					cursoService.listarTodos().stream().map(cursoMapper::toDTO).collect(Collectors.toList()));
			return "evaluaciones/formulario";
		}

		Evaluacion evaluacion = evaluacionMapper.toEntity(dto);
		evaluacionService.guardar(evaluacion);

		return "redirect:/evaluaciones";
	}

	/**
	 * Muestra formulario para editar una evaluación existente.
	 *
	 * @param id    ID de la evaluación
	 * @param model modelo de la vista
	 * @return vista "evaluaciones/formulario" o redirect si no existe
	 */
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {

		Evaluacion evaluacion = evaluacionService.buscarPorId(id);

		if (evaluacion == null) {
			return "redirect:/evaluaciones";
		}

		model.addAttribute("evaluacion", evaluacionMapper.toDTO(evaluacion));

		model.addAttribute("estudiantes",
				estudianteService.listarTodos().stream().map(estudianteMapper::toDTO).collect(Collectors.toList()));

		model.addAttribute("cursos",
				cursoService.listarTodos().stream().map(cursoMapper::toDTO).collect(Collectors.toList()));

		return "evaluaciones/formulario";
	}

	/**
	 * Elimina una evaluación por ID.
	 *
	 * @param id ID de la evaluación
	 * @return redirect a la lista de evaluaciones
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		evaluacionService.eliminar(id);
		return "redirect:/evaluaciones";
	}
}