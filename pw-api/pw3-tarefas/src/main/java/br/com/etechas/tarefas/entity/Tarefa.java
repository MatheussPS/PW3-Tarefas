package br.com.etechas.tarefas.entity;

import br.com.etechas.tarefas.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "TBL_TAREFA")
@Getter
@Setter
public class Tarefa {
    @Id
    @Column(name = "ID_TASK")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TX_TITULO")
    private String titulo;

    @Column(name = "TX_DESCRICAO")
    private String descricao;

    @Column(name = "DT_DATA_LIMITE")
    private LocalDate dataLimite;

    @Column(name = "TX_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "TX_RESPONSAVEL")
    private String responsavel;

    public boolean isPending(){
        return status.equals(StatusEnum.PENDING);
    }

}
