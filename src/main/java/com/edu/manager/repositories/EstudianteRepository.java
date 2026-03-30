package com.edu.manager.repositories;

import com.edu.manager.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Estudiante}.
 * <p>
 * Permite realizar operaciones CRUD básicas sobre estudiantes.
 * </p>
 */
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Ejemplo: Optional<Estudiante> findByEmail(String email);
}