package com.edu.manager.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * DTO (Data Transfer Object) para la entidad Estudiante.
 * <p>
 * Permite transferir información de estudiantes entre capas de la aplicación
 * sin exponer directamente la entidad JPA completa.
 * </p>
 */
public class EstudianteDTO {

	/** Identificador del estudiante */
	private Long id;

	/** Nombre del estudiante */
	private String nombre;

	/** Apellido del estudiante */
	private String apellido;

	/** Email del estudiante */
	private String email;

	/**
	 * Lista de evaluaciones asociadas al estudiante.
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