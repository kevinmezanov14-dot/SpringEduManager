package com.edu.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * Representa un curso dentro del sistema EduManager.
 * <p>
 * Cada curso tiene un nombre, descripción, instructor y puede tener
 * varias evaluaciones asociadas. La entidad está mapeada a la tabla
 * "cursos" en la base de datos.
 * </p>
 * 
 * <p>
 * Relaciones:
 * <ul>
 *   <li>Un curso puede tener muchas evaluaciones (@OneToMany)</li>
 * </ul>
 * </p>
 * 
 * @author Kevin
 */
@Entity
@Table(name = "cursos")
public class Curso {

    /**
     * Identificador único del curso.
     * Se genera automáticamente con estrategia IDENTITY.
     */
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
	/**
     * Lista de evaluaciones asociadas a este curso.
     * <p>
     * Relación uno a muchos con Evaluacion.  
     * CascadeType.ALL asegura que las operaciones (persist, merge, remove) se propaguen a las evaluaciones.
     * orphanRemoval=true elimina evaluaciones huérfanas cuando se quita de la lista.
     * FetchType.LAZY evita cargar evaluaciones automáticamente hasta que se accede a ellas.
     * </p>
     */
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