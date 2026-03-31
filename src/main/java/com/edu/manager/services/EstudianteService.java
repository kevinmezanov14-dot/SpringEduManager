package com.edu.manager.services;

import com.edu.manager.models.Estudiante;
import com.edu.manager.repositories.EstudianteRepository;
import com.edu.manager.repositories.EvaluacionRepository;
import com.edu.manager.repositories.PracticaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

	private final EstudianteRepository estudianteRepository;
	private final EvaluacionRepository evaluacionRepository;
	private final PracticaRepository practicaRepository;

	public EstudianteService(EstudianteRepository estudianteRepository, EvaluacionRepository evaluacionRepository,
			PracticaRepository practicaRepository) {
		this.estudianteRepository = estudianteRepository;
		this.evaluacionRepository = evaluacionRepository;
		this.practicaRepository = practicaRepository;
	}

	public List<Estudiante> listarTodos() {
		return estudianteRepository.findAll();
	}

	public void guardar(Estudiante estudiante) {
		estudianteRepository.save(estudiante);
	}

	public Estudiante buscarPorId(Long id) {
		return estudianteRepository.findById(id).orElse(null);
	}

	public boolean eliminarSiNoTieneDependencias(Long id) {

		Estudiante estudiante = buscarPorId(id);

		if (estudiante == null)
			return false;

		boolean tieneEvaluaciones = evaluacionRepository.existsByEstudianteId(id);
		boolean tienePracticas = practicaRepository.existsByEstudianteId(id);

		if (tieneEvaluaciones || tienePracticas) {
			return false;
		}

		estudianteRepository.deleteById(id);
		return true;
	}
}