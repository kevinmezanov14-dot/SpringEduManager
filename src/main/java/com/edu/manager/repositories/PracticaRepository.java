package com.edu.manager.repositories;

import com.edu.manager.models.Practica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Long> {

}