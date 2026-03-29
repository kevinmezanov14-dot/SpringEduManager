package com.edu.manager.repositories;

import com.edu.manager.models.Practica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticaRepository extends JpaRepository<Practica, Long> {

	boolean existsByCursoId(Long cursoId);

}