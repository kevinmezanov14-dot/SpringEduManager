package com.edu.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Representa una práctica asignada a un estudiante dentro de un curso
 * en el sistema EduManager.
 * <p>
 * Cada práctica tiene un título, descripción, estado y está asociada
 * a un estudiante y a un curso.
 * </p>
 * 
 * <p>
 * Relaciones:
 * <ul>
 *   <li>Cada práctica pertenece a un estudiante (@ManyToOne)</li>
 *   <li>Cada práctica pertenece a un curso (@ManyToOne)</li>
 * </ul>
 * </p>
 * 
 * Validaciones:
 * <ul>
 *   <li>El título, descripción y estado no pueden estar vacíos.</li>
 *   <li>Estudiante y curso no pueden ser nulos.</li>
 * </ul>
 * </p>
 * 
 * Autor: Kevin
 */
@Entity
@Table(name = "practicas")
public class Practica {
    /**
     * Identificador único de la práctica.
     * Se genera automáticamente con estrategia IDENTITY.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
     * Título de la práctica.
     * No puede estar vacío.
     */
	@NotBlank(message = "El título es obligatorio")
	private String titulo;
    /**
     * Descripción de la práctica.
     * No puede estar vacía.
     */
	@NotBlank(message = "La descripción es obligatoria")
	private String descripcion;
	
	/**
     * Estado de la práctica.
     * Puede representar "Pendiente", "En proceso", "Finalizada", etc.
     * No puede estar vacío.
     */
	@NotBlank(message = "El estado es obligatorio")
	private String estado;
    /**
     * Estudiante asociado a la práctica.
     * <p>
     * Relación ManyToOne con la entidad Estudiante.
     * </p>
     */
	@NotNull(message = "Debe seleccionar un estudiante")
	@ManyToOne
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;
    /**
     * Curso asociado a la práctica.
     * <p>
     * Relación ManyToOne con la entidad Curso.
     * </p>
     */
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