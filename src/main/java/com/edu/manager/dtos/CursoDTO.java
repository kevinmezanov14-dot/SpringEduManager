package com.edu.manager.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * DTO (Data Transfer Object) para la entidad Curso.
 * <p>
 * Permite transferir información de cursos entre capas de la aplicación sin
 * exponer directamente la entidad JPA completa.
 * </p>
 */
public class CursoDTO {

	/** Identificador del curso */
	private Long id;

	/** Nombre del curso */
	private String nombre;

	/** Descripción breve del curso */
	private String descripcion;

	/** Nombre del instructor del curso */
	private String instructor;

	/**
	 * Lista de evaluaciones asociadas al curso.
	 * <p>
	 * Se ignora en la serialización JSON para evitar ciclos de referencia o
	 * sobrecarga de datos.
	 * </p>
	 */
	@JsonIgnore
	private List<EvaluacionDTO> evaluaciones;

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public List<EvaluacionDTO> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(List<EvaluacionDTO> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
}