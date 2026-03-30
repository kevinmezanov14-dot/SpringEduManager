package com.edu.manager.controllers;

import com.edu.manager.dtos.CursoDTO;
import com.edu.manager.mappers.CursoMapper;
import com.edu.manager.models.Curso;
import com.edu.manager.services.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador MVC para gestión de cursos.
 * <p>
 * Exposición bajo /cursos - GET /cursos → lista todos los cursos - GET
 * /cursos/nuevo → formulario para nuevo curso - POST /cursos/guardar → guarda o
 * actualiza curso - GET /cursos/editar/{id} → formulario para editar curso
 * existente - GET /cursos/eliminar/{id} → elimina curso si no tiene
 * dependencias
 */
@Controller
@RequestMapping("/cursos")
public class CursoController {

	private final CursoService cursoService;
	private final CursoMapper cursoMapper;

	public CursoController(CursoService cursoService, CursoMapper cursoMapper) {
		this.cursoService = cursoService;
		this.cursoMapper = cursoMapper;
	}

	/**
	 * Lista todos los cursos y los pasa al template Thymeleaf.
	 *
	 * @param model modelo para la vista
	 * @return nombre de la vista Thymeleaf: cursos/lista
	 */
	@GetMapping
	public String listar(Model model) {
		List<CursoDTO> cursos = cursoService.listarTodos().stream().map(cursoMapper::toDTO)
				.collect(Collectors.toList());

		model.addAttribute("cursos", cursos);

		return "cursos/lista";
	}

	/**
	 * Muestra el formulario para crear un nuevo curso.
	 *
	 * @param model modelo para la vista
	 * @return nombre de la vista Thymeleaf: cursos/formulario
	 */
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("curso", new CursoDTO());
		return "cursos/formulario";
	}

	/**
	 * Guarda un curso nuevo o existente.
	 *
	 * @param dto DTO con datos del curso desde el formulario
	 * @return redirección a la lista de cursos
	 */
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute CursoDTO dto) {
		Curso curso = cursoMapper.toEntity(dto);
		cursoService.guardar(curso);
		return "redirect:/cursos";
	}

	/**
	 * Muestra el formulario para editar un curso existente.
	 *
	 * @param id    id del curso
	 * @param model modelo para la vista
	 * @return vista del formulario o redirección si no existe
	 */
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Curso curso = cursoService.buscarPorId(id);
		if (curso == null)
			return "redirect:/cursos";

		model.addAttribute("curso", cursoMapper.toDTO(curso));
		return "cursos/formulario";
	}

	/**
	 * Elimina un curso si no tiene evaluaciones ni prácticas asociadas.
	 *
	 * @param id                 id del curso
	 * @param redirectAttributes mensajes flash para la vista
	 * @return redirección a la lista de cursos
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		boolean eliminado = cursoService.eliminarSiNoTieneDependencias(id);

		if (!eliminado) {
			redirectAttributes.addFlashAttribute("error", "No se puede eliminar, tiene registros asociados");
		} else {
			redirectAttributes.addFlashAttribute("success", "Curso eliminado correctamente");
		}

		return "redirect:/cursos";
	}
}