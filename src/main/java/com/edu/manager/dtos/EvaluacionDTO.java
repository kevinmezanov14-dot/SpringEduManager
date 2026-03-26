package com.edu.manager.dtos;

public class EvaluacionDTO {

	private Long id;
	private String titulo;
	private String descripcion;
	private Double nota;

	// Referencias a Estudiante y Curso
	private EstudianteDTO estudiante;
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

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
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