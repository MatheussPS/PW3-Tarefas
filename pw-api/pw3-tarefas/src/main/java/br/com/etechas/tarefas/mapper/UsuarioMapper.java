package br.com.etechas.tarefas.mapper;

import br.com.etechas.tarefas.dto.UsuarioRequestDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);
    UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario);
    List<UsuarioResponseDTO> toUsuarioResponseDTOList(List<Usuario> usuarios);
}
