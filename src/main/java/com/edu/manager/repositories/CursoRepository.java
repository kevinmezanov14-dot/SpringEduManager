package com.edu.manager.repositories;

import com.edu.manager.models.Curso;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Curso}.
 * <p>
 * Permite realizar operaciones CRUD básicas sobre cursos.
 * </p>
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	@Query("SELECT DISTINCT c FROM Curso c LEFT JOIN FETCH c.evaluaciones")
	List<Curso> findAllWithEvaluaciones();

	@Query("SELECT c FROM Curso c LEFT JOIN FETCH c.evaluaciones WHERE c.id = :id")
	Optional<Curso> findByIdWithEvaluaciones(@Param("id") Long id);
}