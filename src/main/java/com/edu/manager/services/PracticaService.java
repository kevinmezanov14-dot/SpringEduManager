package com.edu.manager.services;

import com.edu.manager.models.Practica;
import com.edu.manager.repositories.PracticaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracticaService {

    private final PracticaRepository practicaRepository;

    public PracticaService(PracticaRepository practicaRepository) {
        this.practicaRepository = practicaRepository;
    }

    public List<Practica> listarTodos() {
        return practicaRepository.findAll();
    }

    public void guardar(Practica practica) {
        practicaRepository.save(practica);
    }

    public Practica buscarPorId(Long id) {
        return practicaRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        practicaRepository.deleteById(id);
    }
}