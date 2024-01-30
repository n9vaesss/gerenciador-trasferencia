package com.gerenciador.tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciador.tarefas.entity.Transferencia;
import com.gerenciador.tarefas.permissoes.StatusTransferenciaEnum;

public interface ITransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByStatusAndLojaDestinatario(StatusTransferenciaEnum status, String lojaDestinatario);
}
