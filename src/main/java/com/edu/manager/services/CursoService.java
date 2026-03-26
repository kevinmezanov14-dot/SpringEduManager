package com.edu.manager.services;

import com.edu.manager.models.Curso;
import com.edu.manager.repositories.CursoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

	private final CursoRepository cursoRepository;

	public CursoService(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	public List<Curso> listarTodos() {
		return cursoRepository.findAll();
	}

	public void guardar(Curso curso) {
		cursoRepository.save(curso);
	}

	public Curso buscarPorId(Long id) {
		return cursoRepository.findById(id).orElse(null);
	}

	public boolean eliminarSiNoTieneEvaluaciones(Long id) {
		Curso curso = buscarPorId(id);
		if (curso != null && (curso.getEvaluaciones() == null || curso.getEvaluaciones().isEmpty())) {
			cursoRepository.deleteById(id);
			return true;
		}
		return false;
	}
}