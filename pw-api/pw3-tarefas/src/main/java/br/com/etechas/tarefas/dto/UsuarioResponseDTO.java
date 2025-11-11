package br.com.etechas.tarefas.dto;

import br.com.etechas.tarefas.enums.RoleEnum;

public record UsuarioResponseDTO(
        Long id,
        String username,
        RoleEnum role
        )
{

}
