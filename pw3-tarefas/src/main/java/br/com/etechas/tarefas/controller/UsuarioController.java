package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.dto.UsuarioRequestDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @PostMapping("/cadastrar")
    ResponseEntity<String> registrar(@RequestBody UsuarioRequestDTO cadastro) {
        usuarioService.registrar(cadastro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso");
    }
}
