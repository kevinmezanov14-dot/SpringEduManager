package com.edu.manager.mappers;

import com.edu.manager.dtos.UsuarioDTO;
import com.edu.manager.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getUsername(),
                usuario.getRole()
        );
    }
}