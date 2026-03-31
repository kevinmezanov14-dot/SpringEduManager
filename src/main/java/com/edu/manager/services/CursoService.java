package com.edu.manager.services;

import com.edu.manager.models.Curso;
import com.edu.manager.repositories.CursoRepository;
import com.edu.manager.repositories.PracticaRepository;
import com.edu.manager.repositories.EvaluacionRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con los cursos.
 * <p>
 * Este servicio proporciona operaciones para listar, guardar, buscar y eliminar cursos,
 * asegurando que no se eliminen cursos que tengan dependencias asociadas (evaluaciones o prácticas).
 * </p>
 * 
 * Autor: Kevin
 */
@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final PracticaRepository practicaRepository;
    private final EvaluacionRepository evaluacionRepository;

    /**
     * Constructor de CursoService.
     * 
     * @param cursoRepository repositorio para operaciones CRUD de cursos
     * @param practicaRepository repositorio para verificar prácticas asociadas
     * @param evaluacionRepository repositorio para verificar evaluaciones asociadas
     */
    public CursoService(CursoRepository cursoRepository,
                        PracticaRepository practicaRepository,
                        EvaluacionRepository evaluacionRepository) {
        this.cursoRepository = cursoRepository;
        this.practicaRepository = practicaRepository;
        this.evaluacionRepository = evaluacionRepository;
    }

    /**
     * Obtiene la lista completa de cursos.
     *
     * @return lista de todos los cursos registrados
     */
    public List<Curso> listarTodos() {
        return cursoRepository.findAllWithEvaluaciones();
    }

    /**
     * Guarda o actualiza un curso en la base de datos.
     *
     * @param curso objeto curso a persistir
     */
    public void guardar(Curso curso) {
        cursoRepository.save(curso);
    }

    /**
     * Busca un curso por su identificador.
     *
     * @param id identificador del curso
     * @return el curso si existe, o null si no se encuentra
     */
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    /**
     * Elimina un curso solo si no tiene dependencias asociadas.
     * <p>
     * Verifica que el curso no tenga evaluaciones ni prácticas relacionadas.
     * Si existen dependencias, no realiza la eliminación y devuelve false.
     * </p>
     *
     * @param id identificador del curso a eliminar
     * @return true si el curso fue eliminado, false si tiene dependencias o no existe
     */
    public boolean eliminarSiNoTieneDependencias(Long id) {

        Curso curso = buscarPorId(id);

        if (curso == null) return false;

        boolean tieneEvaluaciones =
                evaluacionRepository.existsByCursoId(id);

        boolean tienePracticas =
                practicaRepository.existsByCursoId(id);

        if (tieneEvaluaciones || tienePracticas) {
            return false;
        }

        cursoRepository.deleteById(id);
        return true;
    }
}
