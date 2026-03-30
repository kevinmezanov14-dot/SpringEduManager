package com.edu.manager.dtos;

import jakarta.validation.constraints.*;

/**
 * DTO para la entidad Evaluacion.
 * <p>
 * Permite transferir información de evaluaciones entre capas sin exponer directamente
 * la entidad JPA completa. Contiene campos para backend (IDs) y para la vista (nombres).
 * </p>
 */
public class EvaluacionDTO {

    /** Identificador de la evaluación */
    private Long id;

    /** Título de la evaluación */
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    /** Descripción de la evaluación */
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    /** Nota de la evaluación (1.0 a 7.0) */
    @NotNull(message = "La nota es obligatoria")
    @DecimalMin(value = "1.0", message = "La nota mínima es 1.0")
    @DecimalMax(value = "7.0", message = "La nota máxima es 7.0")
    private Double nota;

    // IDs para backend (mantener lógica limpia)
    @NotNull(message = "Debe seleccionar un estudiante")
    private Long estudianteId;

    @NotNull(message = "Debe seleccionar un curso")
    private Long cursoId;

    //Datos para la vista (Thymeleaf)
    private String estudianteNombre;
    private String cursoNombre;

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