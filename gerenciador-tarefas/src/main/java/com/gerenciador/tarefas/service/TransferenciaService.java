package com.gerenciador.tarefas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Transferencia;
import com.gerenciador.tarefas.permissoes.StatusTransferenciaEnum;
import com.gerenciador.tarefas.repository.ITransferenciaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferenciaService {
    @Autowired
    private ITransferenciaRepository iTransferenciaRepository;

    public Transferencia salvarTransferencia(Transferencia transferencia) {
        return this.iTransferenciaRepository.save(transferencia);
    }

    public List<Transferencia> obtemTransferencias() {
        return this.iTransferenciaRepository.findAll();
    }

    public List<Transferencia> buscarPorStatusELojaDestinatario(String status, String lojaDestinatario) {
        return this.iTransferenciaRepository.findByStatusAndLojaDestinatario(StatusTransferenciaEnum.ENVIADA,
        lojaDestinatario);
    }
}
