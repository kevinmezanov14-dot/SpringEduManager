package com.edu.manager.dtos;

import jakarta.validation.constraints.*;

public class EvaluacionDTO {

	private Long id;

	@NotBlank(message = "El título es obligatorio")
	private String titulo;

	@NotBlank(message = "La descripción es obligatoria")
	private String descripcion;

	@NotNull(message = "La nota es obligatoria")
	@DecimalMin(value = "1.0", message = "La nota mínima es 1.0")
	@DecimalMax(value = "7.0", message = "La nota máxima es 7.0")
	private Double nota;

	// 🔥 IDs (backend limpio)
	@NotNull(message = "Debe seleccionar un estudiante")
	private Long estudianteId;

	@NotNull(message = "Debe seleccionar un curso")
	private Long cursoId;

	// 🔥 DATOS PARA VISTA (Thymeleaf)
	private String estudianteNombre;
	private String cursoNombre;

	// GETTERS Y SETTERS

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

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Long getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(Long estudianteId) {
		this.estudianteId = estudianteId;
	}

	public Long getCursoId() {
		return cursoId;
	}

	public void setCursoId(Long cursoId) {
		this.cursoId = cursoId;
	}

	public String getEstudianteNombre() {
		return estudianteNombre;
	}

	public void setEstudianteNombre(String estudianteNombre) {
		this.estudianteNombre = estudianteNombre;
	}

	public String getCursoNombre() {
		return cursoNombre;
	}

	public void setCursoNombre(String cursoNombre) {
		this.cursoNombre = cursoNombre;
	}
}