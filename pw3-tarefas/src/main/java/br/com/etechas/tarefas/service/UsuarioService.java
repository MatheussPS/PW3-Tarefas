package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.dto.UsuarioRequestDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.mapper.UsuarioMapper;
import br.com.etechas.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UsuarioResponseDTO registrar(UsuarioRequestDTO cadastro) {
        var exists = repository.findByUsername(cadastro.username());
        if (exists.isPresent()) {
            throw new RuntimeException("Nome de usuário já existe");
        }

        var senhaCifrada = passwordEncoder.encode(cadastro.password());
        Usuario usuario = usuarioMapper.toEntity(cadastro);
        usuario.setPassword(senhaCifrada);

        var salvo = repository.save(usuario);
        return usuarioMapper.toUsuarioResponseDTO(salvo);
    }

    public List<UsuarioResponseDTO> findAll() {
        return usuarioMapper.toUsuarioResponseDTOList(repository.findAll());
    }

}