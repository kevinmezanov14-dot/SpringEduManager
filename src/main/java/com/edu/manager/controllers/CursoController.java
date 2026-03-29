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

@Controller
@RequestMapping("/cursos")
public class CursoController {

	private final CursoService cursoService;
	private final CursoMapper cursoMapper;

	public CursoController(CursoService cursoService, CursoMapper cursoMapper) {
		this.cursoService = cursoService;
		this.cursoMapper = cursoMapper;
	}

	@GetMapping
	public String listar(Model model) {
		List<CursoDTO> cursos = cursoService.listarTodos().stream().map(cursoMapper::toDTO)
				.collect(Collectors.toList());

		model.addAttribute("cursos", cursos);

		return "cursos/lista";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("curso", new CursoDTO());
		return "cursos/formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@ModelAttribute CursoDTO dto) {
		Curso curso = cursoMapper.toEntity(dto);
		cursoService.guardar(curso);
		return "redirect:/cursos";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Curso curso = cursoService.buscarPorId(id);
		if (curso == null)
			return "redirect:/cursos";

		model.addAttribute("curso", cursoMapper.toDTO(curso));
		return "cursos/formulario";
	}

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