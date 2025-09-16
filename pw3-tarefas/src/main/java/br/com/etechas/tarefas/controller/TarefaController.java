//Dupla: Matheus Pinter e Paulo Sergio
package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.dto.TarefaRequestDTO;
import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService service;


    @GetMapping
    public List<TarefaResponseDTO> listar(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable Long id){
        if(service.deleteById(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tarefa deletada");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Tarefa> atualizarPorId(@PathVariable Long id, @RequestBody TarefaResponseDTO model){
        return ResponseEntity.status(HttpStatus.OK).body(service.editTarefa(id, model));
    }

    @PostMapping("/criar")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody TarefaRequestDTO model){
        return ResponseEntity.status(HttpStatus.OK).body(service.createTarefa(model));
    }
}
