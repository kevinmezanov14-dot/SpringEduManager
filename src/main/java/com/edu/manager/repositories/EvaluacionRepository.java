package com.edu.manager.repositories;

import com.edu.manager.models.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad {@link Evaluacion}.
 * <p>
 * Permite operaciones CRUD y carga de relaciones con curso y estudiante.
 * </p>
 */
@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

	/**
	 * Obtiene todas las evaluaciones junto con los estudiantes y cursos asociados.
	 * Esto evita problemas de LazyInitializationException al acceder a relaciones.
	 */
	@Query("SELECT e FROM Evaluacion e JOIN FETCH e.estudiante JOIN FETCH e.curso")
	List<Evaluacion> findAllWithRelations();

	boolean existsByCursoId(Long cursoId);

	boolean existsByEstudianteId(Long estudianteId);
}