package com.edu.manager.repositories;

import com.edu.manager.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Curso}.
 * <p>
 * Permite realizar operaciones CRUD básicas sobre cursos.
 * </p>
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Se puede agregar métodos personalizados si es necesario
}