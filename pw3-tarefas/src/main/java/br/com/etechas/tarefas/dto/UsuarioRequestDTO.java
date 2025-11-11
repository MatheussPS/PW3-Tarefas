package br.com.etechas.tarefas.dto;


import br.com.etechas.tarefas.enums.RoleEnum;

public record UsuarioRequestDTO(
        String username,
        String password,
        RoleEnum role
        )
{

}
