package com.gerenciador.tarefas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gerenciador.tarefas.entity.Entregador;

public interface IEntregadorRepository extends JpaRepository<Entregador, Long> {

    @Query("SELECT e FROM Entregador e WHERE e.ativo = true")
    List<Entregador> obtemEntregadoresAtivos();

    Optional<Entregador> findByNomeCompleto(String nomeCompleto);
    
}
