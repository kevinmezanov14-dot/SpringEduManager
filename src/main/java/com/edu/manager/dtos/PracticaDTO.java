/**
 * DTO que representa una práctica de un estudiante en un curso.
 * Usado para transferir datos entre frontend y backend sin exponer la entidad completa.
 * Contiene validaciones y referencias a EstudianteDTO y CursoDTO.
 */
package com.edu.manager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PracticaDTO {

	private Long id;

	@NotBlank(message = "El título es obligatorio")
	private String titulo;

	private String descripcion;

	@NotBlank(message = "El estado es obligatorio")
	private String estado;

	@NotNull(message = "El estudiante es obligatorio")
	private EstudianteDTO estudiante;

	@NotNull(message = "El curso es obligatorio")
	private CursoDTO curso;

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public EstudianteDTO getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(EstudianteDTO estudiante) {
		this.estudiante = estudiante;
	}

	public CursoDTO getCurso() {
		return curso;
	}

	public void setCurso(CursoDTO curso) {
		this.curso = curso;
	}
}