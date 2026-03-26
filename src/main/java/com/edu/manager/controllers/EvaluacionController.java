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

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("evaluaciones",
				evaluacionService.listarTodos().stream().map(evaluacionMapper::toDTO).collect(Collectors.toList()));
		return "evaluaciones/lista";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("evaluacion", new EvaluacionDTO());
		model.addAttribute("estudiantes",
				estudianteService.listarTodos().stream().map(estudianteMapper::toDTO).collect(Collectors.toList()));
		model.addAttribute("cursos",
				cursoService.listarTodos().stream().map(cursoMapper::toDTO).collect(Collectors.toList()));
		return "evaluaciones/formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute EvaluacionDTO evaluacionDTO, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("estudiantes",
					estudianteService.listarTodos().stream().map(estudianteMapper::toDTO).collect(Collectors.toList()));
			model.addAttribute("cursos",
					cursoService.listarTodos().stream().map(cursoMapper::toDTO).collect(Collectors.toList()));
			return "evaluaciones/formulario";
		}

		Evaluacion evaluacion = evaluacionMapper.toEntity(evaluacionDTO);
		evaluacionService.guardar(evaluacion);
		return "redirect:/evaluaciones";
	}

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

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		evaluacionService.eliminar(id);
		return "redirect:/evaluaciones";
	}
}