package com.edu.manager.mappers;

import com.edu.manager.dtos.EvaluacionDTO;
import com.edu.manager.models.*;
import org.springframework.stereotype.Component;

/**
 * Mapper para la entidad {@link Evaluacion} y su {@link EvaluacionDTO}.
 * <p>
 * Convierte de entidad a DTO y de DTO a entidad, manejando las relaciones
 * con {@link Estudiante} y {@link Curso} solo mediante IDs.
 * </p>
 */
@Component
public class EvaluacionMapper {

    /**
     * Convierte un objeto EvaluacionDTO en su entidad Evaluacion.
     * <p>
     * Las relaciones con Estudiante y Curso se establecen solo mediante los IDs,
     * evitando cargar objetos completos desde la base de datos.
     * </p>
     *
     * @param dto objeto EvaluacionDTO
     * @return entidad Evaluacion con referencias a Estudiante y Curso
     */
    public Evaluacion toEntity(EvaluacionDTO dto) {
        Evaluacion e = new Evaluacion();

        e.setId(dto.getId());
        e.setTitulo(dto.getTitulo());
        e.setDescripcion(dto.getDescripcion());
        e.setNota(dto.getNota());

        // Solo asignar ID de estudiante si existe
        if (dto.getEstudianteId() != null) {
            Estudiante est = new Estudiante();
            est.setId(dto.getEstudianteId());
            e.setEstudiante(est);
        }

        // Solo asignar ID de curso si existe
        if (dto.getCursoId() != null) {
            Curso cur = new Curso();
            cur.setId(dto.getCursoId());
            e.setCurso(cur);
        }

        return e;
    }

    /**
     * Convierte un objeto Evaluacion en su DTO correspondiente.
     * <p>
     * Incluye IDs y nombres para mostrar en interfaces o APIs sin exponer
     * toda la entidad relacionada.
     * </p>
     *
     * @param e entidad Evaluacion
     * @return objeto EvaluacionDTO con información resumida de Estudiante y Curso
     */
    public EvaluacionDTO toDTO(Evaluacion e) {
        EvaluacionDTO dto = new EvaluacionDTO();

        dto.setId(e.getId());
        dto.setTitulo(e.getTitulo());
        dto.setDescripcion(e.getDescripcion());
        dto.setNota(e.getNota());

        // 🔹 IDs y nombres de estudiante
        if (e.getEstudiante() != null) {
            dto.setEstudianteId(e.getEstudiante().getId());
            dto.setEstudianteNombre(e.getEstudiante().getNombre() + " " + e.getEstudiante().getApellido());
        }

        // 🔹 IDs y nombres de curso
        if (e.getCurso() != null) {
            dto.setCursoId(e.getCurso().getId());
            dto.setCursoNombre(e.getCurso().getNombre());
        }

        return dto;
    }
}