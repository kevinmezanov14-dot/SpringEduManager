package com.edu.manager.services;

import com.edu.manager.models.Evaluacion;
import com.edu.manager.repositories.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con las evaluaciones.
 * <p>
 * Proporciona operaciones para listar, guardar, buscar y eliminar evaluaciones
 * de estudiantes dentro de cursos.
 * </p>
 * 
 * Autor: Kevin
 */
@Service
public class EvaluacionService {

    @Autowired
    private final EvaluacionRepository evaluacionRepository;

    /**
     * Constructor de EvaluacionService.
     * 
     * @param evaluacionRepository repositorio para operaciones CRUD de evaluaciones
     */
    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    /**
     * Obtiene la lista completa de evaluaciones, incluyendo relaciones con
     * estudiantes y cursos.
     *
     * @return lista de todas las evaluaciones registradas
     */
    public List<Evaluacion> listarTodos() {
        return evaluacionRepository.findAllWithRelations();
    }

    /**
     * Guarda o actualiza una evaluación en la base de datos.
     *
     * @param evaluacion objeto Evaluacion a persistir
     */
    public void guardar(Evaluacion evaluacion) {
        evaluacionRepository.save(evaluacion);
    }

    /**
     * Busca una evaluación por su identificador.
     *
     * @param id identificador de la evaluación
     * @return la evaluación si existe, o null si no se encuentra
     */
    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id).orElse(null);
    }

    /**
     * Elimina una evaluación por su identificador.
     *
     * @param id identificador de la evaluación a eliminar
     */
    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }
}