package com.edu.manager.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class EstudianteDTO {

	private Long id;
	private String nombre;
	private String apellido;
	private String email;

	// Evitar ciclo al serializar JSON
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<EvaluacionDTO> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(List<EvaluacionDTO> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
}