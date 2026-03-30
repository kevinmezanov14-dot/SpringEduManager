package com.edu.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * Representa una evaluación de un estudiante en un curso dentro del sistema EduManager.
 * <p>
 * Cada evaluación tiene un título, descripción, nota y está asociada a un estudiante y un curso.
 * La entidad se mapea a la tabla "evaluaciones" en la base de datos.
 * </p>
 *
 * <p>
 * Relaciones:
 * <ul>
 *   <li>Cada evaluación pertenece a un estudiante (@ManyToOne)</li>
 *   <li>Cada evaluación pertenece a un curso (@ManyToOne)</li>
 * </ul>
 * </p>
 * Autor: Kevin
 */
@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

    /**
     * Identificador único de la evaluación.
     * Se genera automáticamente con estrategia IDENTITY.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    /**
     * Título de la evaluación.
     * No puede estar vacío.
     */
	@NotBlank(message = "El título es obligatorio")
	private String titulo;
	
    /**
     * Descripción de la evaluación.
     * No puede estar vacío.
     */
	@NotBlank(message = "La descripción es obligatoria")
	private String descripcion;

    /**
     * Nota obtenida en la evaluación.
     * <p>
     * Valor numérico obligatorio entre 1.0 y 7.0.
     * </p>
     */
	@NotNull(message = "La nota es obligatoria")
	@DecimalMin(value = "1.0", message = "La nota mínima es 1.0")
	@DecimalMax(value = "7.0", message = "La nota máxima es 7.0")
	private Double nota;

	// Relación con estudiante
    /**
     * Estudiante al que pertenece la evaluación.
     * <p>
     * Relación ManyToOne con la entidad Estudiante.
     * Se usa FetchType.LAZY para cargar el estudiante solo cuando se necesita.
     * </p>
     */
	@NotNull(message = "Debe seleccionar un estudiante")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;

	// Relación con curso
    /**
     * Curso asociado a la evaluación.
     * <p>
     * Relación ManyToOne con la entidad Curso (un curso puede tener muchas evaluaciones).
     * Se usa FetchType.LAZY para cargar el curso solo cuando se necesita.
     * </p>
     */
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