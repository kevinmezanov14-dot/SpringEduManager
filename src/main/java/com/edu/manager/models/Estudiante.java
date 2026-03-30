package com.edu.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * Representa un estudiante en el sistema EduManager.
 * <p>
 * Cada estudiante tiene un nombre, apellido, email único y puede tener
 * múltiples evaluaciones asociadas. La entidad se mapea a la tabla
 * "estudiantes" en la base de datos.
 * </p>
 * 
 * <p>
 * Relaciones:
 * <ul>
 *   <li>Un estudiante puede tener muchas evaluaciones (@OneToMany)</li>
 * </ul>
 * </p>
 * 
 * Validaciones:
 * <ul>
 *   <li>Nombre y apellido no pueden estar vacíos.</li>
 *   <li>El email es obligatorio y debe ser único y válido.</li>
 * </ul>
 * </p>
 * 
 * Autor: Kevin
 */
@Entity
@Table(name = "estudiantes")
public class Estudiante {

    /**
     * Identificador único del estudiante.
     * Se genera automáticamente con estrategia IDENTITY.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    /**
     * Nombre del estudiante.
     * No puede estar vacío.
     */
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
    /**
     * Apellido del estudiante.
     * No puede estar vacío.
     */
	@NotBlank(message = "El apellido es obligatorio")
	private String apellido;
    /**
     * Email del estudiante.
     * <p>
     * Debe ser válido y único en la base de datos.
     * </p>
     */
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "Debe ser un email válido")
	@Column(unique = true)
	private String email;

    /**
     * Lista de evaluaciones asociadas al estudiante.
     * <p>
     * Relación uno a muchos con Evaluacion.  
     * CascadeType.ALL asegura que las operaciones (persist, merge, remove) se propaguen a las evaluaciones.
     * orphanRemoval=true elimina evaluaciones huérfanas cuando se quita de la lista.
     * FetchType.LAZY evita cargar evaluaciones automáticamente hasta que se accede a ellas.
     * </p>
     */
	// Relación con evaluaciones
	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Evaluacion> evaluaciones;

	public Estudiante() {
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

	public List<Evaluacion> getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(List<Evaluacion> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}
}