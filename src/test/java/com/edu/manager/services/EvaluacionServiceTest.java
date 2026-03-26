package com.edu.manager.services;

import com.edu.manager.models.Evaluacion;
import com.edu.manager.repositories.EvaluacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EvaluacionServiceTest {

    private EvaluacionRepository evaluacionRepository;
    private EvaluacionService evaluacionService;

    @BeforeEach
    void setUp() {
        evaluacionRepository = Mockito.mock(EvaluacionRepository.class);
        evaluacionService = new EvaluacionService(evaluacionRepository);
    }

    @Test
    void testListarTodos() {
        when(evaluacionRepository.findAll()).thenReturn(Arrays.asList(new Evaluacion(), new Evaluacion()));
        List<Evaluacion> lista = evaluacionService.listarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void testGuardar() {
        Evaluacion e = new Evaluacion();
        evaluacionService.guardar(e);
        verify(evaluacionRepository, times(1)).save(e);
    }

    @Test
    void testBuscarPorId() {
        Evaluacion e = new Evaluacion();
        e.setId(1L);
        when(evaluacionRepository.findById(1L)).thenReturn(Optional.of(e));
        Evaluacion encontrado = evaluacionService.buscarPorId(1L);
        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void testEliminar() {
        evaluacionService.eliminar(1L);
        verify(evaluacionRepository, times(1)).deleteById(1L);
    }
}