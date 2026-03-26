package com.edu.manager.controllers;

import com.edu.manager.dtos.EstudianteDTO;
import com.edu.manager.mappers.EstudianteMapper;
import com.edu.manager.models.Estudiante;
import com.edu.manager.services.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

	private final EstudianteService estudianteService;
	private final EstudianteMapper estudianteMapper;

	public EstudianteController(EstudianteService estudianteService, EstudianteMapper estudianteMapper) {
		this.estudianteService = estudianteService;
		this.estudianteMapper = estudianteMapper;
	}

	@GetMapping
	public String listar(Model model, @RequestParam(value = "error", required = false) String error) {
		List<EstudianteDTO> estudiantes = estudianteService.listarTodos().stream().map(estudianteMapper::toDTO)
				.collect(Collectors.toList());

		model.addAttribute("estudiantes", estudiantes);
		model.addAttribute("error", error);
		return "estudiantes/lista";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("estudiante", new EstudianteDTO());
		return "estudiantes/formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute EstudianteDTO dto) {
		Estudiante estudiante = estudianteMapper.toEntity(dto);
		estudianteService.guardar(estudiante);
		return "redirect:/estudiantes";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Estudiante estudiante = estudianteService.buscarPorId(id);
		if (estudiante == null)
			return "redirect:/estudiantes";

		model.addAttribute("estudiante", estudianteMapper.toDTO(estudiante));
		return "estudiantes/formulario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Estudiante estudiante = estudianteService.buscarPorId(id);
		if (estudiante != null && estudiante.getEvaluaciones() != null && !estudiante.getEvaluaciones().isEmpty()) {
			return "redirect:/estudiantes?error=No se puede eliminar, tiene evaluaciones";
		}
		estudianteService.eliminar(id);
		return "redirect:/estudiantes";
	}
}