package com.edu.manager.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class CursoDTO {

	private Long id;
	private String nombre;
	private String descripcion;
	private String instructor;

	@JsonIgnore
	private List<EvaluacionDTO> evaluaciones;

	// Getters y Setters
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