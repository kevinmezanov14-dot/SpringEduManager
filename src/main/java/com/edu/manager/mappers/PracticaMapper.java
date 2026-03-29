package com.edu.manager.mappers;

import com.edu.manager.dtos.PracticaDTO;
import com.edu.manager.models.Practica;
import org.springframework.stereotype.Component;

@Component
public class PracticaMapper {

    private final EstudianteMapper estudianteMapper;
    private final CursoMapper cursoMapper;

    public PracticaMapper(EstudianteMapper estudianteMapper, CursoMapper cursoMapper) {
        this.estudianteMapper = estudianteMapper;
        this.cursoMapper = cursoMapper;
    }

    // De entidad a DTO
    public PracticaDTO toDTO(Practica practica) {
        if (practica == null) return null;

        PracticaDTO dto = new PracticaDTO();
        dto.setId(practica.getId());
        dto.setTitulo(practica.getTitulo());
        dto.setDescripcion(practica.getDescripcion());
        dto.setEstado(practica.getEstado());
        dto.setEstudiante(estudianteMapper.toDTO(practica.getEstudiante()));
        dto.setCurso(cursoMapper.toDTO(practica.getCurso()));

        return dto;
    }

    // De DTO a entidad
    public Practica toEntity(PracticaDTO dto) {
        if (dto == null) return null;

        Practica practica = new Practica();
        practica.setId(dto.getId());
        practica.setTitulo(dto.getTitulo());
        practica.setDescripcion(dto.getDescripcion());
        practica.setEstado(dto.getEstado());
        practica.setEstudiante(estudianteMapper.toEntity(dto.getEstudiante()));
        practica.setCurso(cursoMapper.toEntity(dto.getCurso()));

        return practica;
    }
}