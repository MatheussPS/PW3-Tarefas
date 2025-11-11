package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.dto.LoginRequestDTO;
import br.com.etechas.tarefas.dto.LoginResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.security.JWTHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTHolder jwtHolder;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        Authentication autenticado = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        String token = jwtHolder.generateToken((Usuario) autenticado.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
