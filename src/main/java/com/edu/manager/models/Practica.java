package com.edu.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "practicas")
public class Practica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El título es obligatorio")
	private String titulo;

	@NotBlank(message = "La descripción es obligatoria")
	private String descripcion;

	@NotBlank(message = "El estado es obligatorio")
	private String estado;

	@NotNull(message = "Debe seleccionar un estudiante")
	@ManyToOne
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;

	@NotNull(message = "Debe seleccionar un curso")
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;

	public Practica() {
	}

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