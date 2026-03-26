package com.edu.manager.services;

import com.edu.manager.models.Estudiante;
import com.edu.manager.repositories.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstudianteServiceTest {

    private EstudianteRepository estudianteRepository;
    private EstudianteService estudianteService;

    @BeforeEach
    void setUp() {
        estudianteRepository = Mockito.mock(EstudianteRepository.class);
        estudianteService = new EstudianteService(estudianteRepository);
    }

    @Test
    void testListarTodos() {
        when(estudianteRepository.findAll()).thenReturn(Arrays.asList(
                new Estudiante(), new Estudiante()
        ));

        List<Estudiante> lista = estudianteService.listarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void testGuardar() {
        Estudiante e = new Estudiante();
        estudianteService.guardar(e);
        verify(estudianteRepository, times(1)).save(e);
    }

    @Test
    void testBuscarPorId() {
        Estudiante e = new Estudiante();
        e.setId(1L);
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(e));

        Estudiante encontrado = estudianteService.buscarPorId(1L);
        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void testEliminar() {
        estudianteService.eliminar(1L);
        verify(estudianteRepository, times(1)).deleteById(1L);
    }
}