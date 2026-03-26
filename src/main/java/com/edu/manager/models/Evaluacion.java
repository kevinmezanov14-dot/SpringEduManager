package com.edu.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El título es obligatorio")
	private String titulo;

	@NotBlank(message = "La descripción es obligatoria")
	private String descripcion;

	@NotNull(message = "La nota es obligatoria")
	@DecimalMin(value = "1.0", message = "La nota mínima es 1.0")
	@DecimalMax(value = "7.0", message = "La nota máxima es 7.0")
	private Double nota;

	// Relación con estudiante
	@NotNull(message = "Debe seleccionar un estudiante")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;

	// Relación con curso
	@NotNull(message = "Debe seleccionar un curso")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
	private Curso curso;

	public Evaluacion() {
	}

	// Getters y setters
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

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}