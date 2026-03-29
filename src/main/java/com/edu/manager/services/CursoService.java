package com.edu.manager.services;

import com.edu.manager.models.Curso;
import com.edu.manager.repositories.CursoRepository;
import com.edu.manager.repositories.PracticaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

	private final CursoRepository cursoRepository;
	private final PracticaRepository practicaRepository;

	public CursoService(CursoRepository cursoRepository, PracticaRepository practicaRepository) {
		this.cursoRepository = cursoRepository;
		this.practicaRepository = practicaRepository;
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

	public boolean eliminarSiNoTieneDependencias(Long id) {

		Curso curso = buscarPorId(id);

		if (curso == null) return false;

		boolean tieneEvaluaciones =
				curso.getEvaluaciones() != null && !curso.getEvaluaciones().isEmpty();

		boolean tienePracticas =
				practicaRepository.existsByCursoId(id);

		if (tieneEvaluaciones || tienePracticas) {
			return false;
		}

		cursoRepository.deleteById(id);
		return true;
	}
}
