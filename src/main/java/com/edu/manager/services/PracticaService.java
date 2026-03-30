package com.edu.manager.services;

import com.edu.manager.models.Practica;
import com.edu.manager.repositories.PracticaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio relacionada con las prácticas.
 * <p>
 * Proporciona operaciones para listar, guardar, buscar y eliminar prácticas
 * asociadas a estudiantes y cursos dentro del sistema SpringEduManager.
 * </p>
 * 
 * Autor: Kevin
 */
@Service
public class PracticaService {

    private final PracticaRepository practicaRepository;

    /**
     * Constructor de PracticaService.
     * 
     * @param practicaRepository repositorio para operaciones CRUD de prácticas
     */
    public PracticaService(PracticaRepository practicaRepository) {
        this.practicaRepository = practicaRepository;
    }

    /**
     * Obtiene la lista completa de prácticas registradas.
     *
     * @return lista de todas las prácticas
     */
    public List<Practica> listarTodos() {
        return practicaRepository.findAll();
    }

    /**
     * Guarda o actualiza una práctica en la base de datos.
     *
     * @param practica objeto Practica a persistir
     */
    public void guardar(Practica practica) {
        practicaRepository.save(practica);
    }

    /**
     * Busca una práctica por su identificador.
     *
     * @param id identificador de la práctica
     * @return la práctica si existe, o null si no se encuentra
     */
    public Practica buscarPorId(Long id) {
        return practicaRepository.findById(id).orElse(null);
    }

    /**
     * Elimina una práctica por su identificador.
     *
     * @param id identificador de la práctica a eliminar
     */
    public void eliminar(Long id) {
        practicaRepository.deleteById(id);
    }
}