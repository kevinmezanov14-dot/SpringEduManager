package com.edu.manager.repositories;

import com.edu.manager.models.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

	@Query("SELECT e FROM Evaluacion e " + "JOIN FETCH e.estudiante " + "JOIN FETCH e.curso")
	List<Evaluacion> findAllWithRelations();
}