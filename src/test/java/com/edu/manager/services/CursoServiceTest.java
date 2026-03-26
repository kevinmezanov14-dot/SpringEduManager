package com.edu.manager.services;

import com.edu.manager.models.Curso;
import com.edu.manager.models.Evaluacion;
import com.edu.manager.repositories.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoServiceTest {

	private CursoRepository cursoRepository;
	private CursoService cursoService;

	@BeforeEach
	void setUp() {
		cursoRepository = Mockito.mock(CursoRepository.class);
		cursoService = new CursoService(cursoRepository);
	}

	@Test
	void testEliminarSiNoTieneEvaluaciones() {
		Curso c = new Curso();
		c.setId(1L);
		c.setEvaluaciones(Collections.emptyList());
		when(cursoRepository.findById(1L)).thenReturn(Optional.of(c));

		boolean eliminado = cursoService.eliminarSiNoTieneEvaluaciones(1L);
		assertTrue(eliminado);
		verify(cursoRepository, times(1)).deleteById(1L);
	}

	@Test
	void testNoEliminarConEvaluaciones() {
		Curso c = new Curso();
		c.setId(2L);
		c.setEvaluaciones(Collections.singletonList(new Evaluacion()));
		when(cursoRepository.findById(2L)).thenReturn(Optional.of(c));

		boolean eliminado = cursoService.eliminarSiNoTieneEvaluaciones(2L);
		assertFalse(eliminado);
		verify(cursoRepository, never()).deleteById(2L);
	}
}