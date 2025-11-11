//Dupla: Matheus Pinter e Paulo Sergio

package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.dto.TarefaRequestDTO;
import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.exception.DateIsBeforeLocalDateNowException;
import br.com.etechas.tarefas.mapper.TarefaMapper;
import br.com.etechas.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private TarefaMapper tarefaMapper;

    public List<TarefaResponseDTO> findAll(){

        return tarefaMapper.toResponseDTOList(repository.findAll());
    }

    public boolean deleteById(Long id){

        var tarefa = repository.findById(id);
        if(tarefa.isEmpty()){
            return false;
        }
        if (tarefa.get().isPending()){
            repository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Tarefa não está pendente");
    }

    public Tarefa editTarefa(Long id, TarefaResponseDTO model) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));

        tarefa.setTitulo(model.titulo());
        tarefa.setDataLimite(model.dataLimite());
        tarefa.setStatus(model.status());
        tarefa.setResponsavel(model.responsavel());

        return repository.save(tarefa);
    }

    public Tarefa createTarefa(TarefaRequestDTO model) {

        if(model.dataLimite().isBefore(LocalDate.now()) ){
            throw new DateIsBeforeLocalDateNowException();
        }
        Tarefa tarefa = tarefaMapper.toResponseTarefa(model);
        tarefa.setStatus(StatusEnum.PENDING);
        
        return repository.save(tarefa);
    }


}
