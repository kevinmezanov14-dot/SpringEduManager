package com.edu.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre del curso es obligatorio")
	private String nombre;

	@NotBlank(message = "La descripción es obligatoria")
	private String descripcion;

	@NotBlank(message = "El instructor es obligatorio")
	private String instructor;

	// Relación con evaluaciones
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Evaluacion> evaluaciones;

	public Curso() {
	}

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

	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(List<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
}