package com.edu.manager.services;

import com.edu.manager.models.Evaluacion;
import com.edu.manager.repositories.EvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public List<Evaluacion> listarTodos() {
        return evaluacionRepository.findAllWithRelations();
    }

    public void guardar(Evaluacion evaluacion) {
        evaluacionRepository.save(evaluacion);
    }

    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }
}