package br.com.etechas.tarefas.repository;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}








