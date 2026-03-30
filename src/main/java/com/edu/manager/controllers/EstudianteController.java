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

/**
 * Controlador web para gestión de estudiantes.
 * <p>
 * Exposición bajo /estudiantes - GET /estudiantes → listar todos los
 * estudiantes - GET /estudiantes/nuevo → mostrar formulario de creación - POST
 * /estudiantes/guardar → guardar un estudiante (nuevo o editado) - GET
 * /estudiantes/editar/{id} → mostrar formulario de edición - GET
 * /estudiantes/eliminar/{id} → eliminar estudiante si no tiene evaluaciones
 */
@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

	private final EstudianteService estudianteService;
	private final EstudianteMapper estudianteMapper;

	public EstudianteController(EstudianteService estudianteService, EstudianteMapper estudianteMapper) {
		this.estudianteService = estudianteService;
		this.estudianteMapper = estudianteMapper;
	}

	/**
	 * Lista todos los estudiantes y opcionalmente muestra mensaje de error.
	 *
	 * @param model Spring Model
	 * @param error mensaje de error (opcional)
	 * @return plantilla Thymeleaf "estudiantes/lista"
	 */
	@GetMapping
	public String listar(Model model, @RequestParam(required = false) String error) {
		List<EstudianteDTO> estudiantes = estudianteService.listarTodos().stream().map(estudianteMapper::toDTO)
				.collect(Collectors.toList());

		model.addAttribute("estudiantes", estudiantes);
		model.addAttribute("error", error);
		return "estudiantes/lista";
	}

	/**
	 * Muestra formulario para crear un nuevo estudiante.
	 *
	 * @param model Spring Model
	 * @return plantilla Thymeleaf "estudiantes/formulario"
	 */
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("estudiante", new EstudianteDTO());
		return "estudiantes/formulario";
	}

	/**
	 * Guarda un estudiante nuevo o editado.
	 *
	 * @param dto datos del estudiante
	 * @return redirección a /estudiantes
	 */
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute EstudianteDTO dto) {
		Estudiante estudiante = estudianteMapper.toEntity(dto);
		estudianteService.guardar(estudiante);
		return "redirect:/estudiantes";
	}

	/**
	 * Muestra formulario de edición de un estudiante existente.
	 *
	 * @param id    ID del estudiante
	 * @param model Spring Model
	 * @return plantilla "estudiantes/formulario" o redirección si no existe
	 */
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Estudiante estudiante = estudianteService.buscarPorId(id);
		if (estudiante == null)
			return "redirect:/estudiantes";

		model.addAttribute("estudiante", estudianteMapper.toDTO(estudiante));
		return "estudiantes/formulario";
	}

	/**
	 * Elimina un estudiante si no tiene evaluaciones asociadas.
	 *
	 * @param id ID del estudiante
	 * @return redirección a /estudiantes, con error si no se puede eliminar
	 */
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