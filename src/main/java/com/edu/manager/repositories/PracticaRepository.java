package com.edu.manager.repositories;

import com.edu.manager.models.Practica;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad {@link Practica}.
 * <p>
 * Permite operaciones CRUD sobre prácticas y consultas específicas.
 * </p>
 */
public interface PracticaRepository extends JpaRepository<Practica, Long> {

	/**
	 * Verifica si existen prácticas asociadas a un curso.
	 *
	 * @param cursoId ID del curso
	 * @return true si existen prácticas asociadas
	 */
	boolean existsByCursoId(Long cursoId);

	boolean existsByEstudianteId(Long estudianteId);
}