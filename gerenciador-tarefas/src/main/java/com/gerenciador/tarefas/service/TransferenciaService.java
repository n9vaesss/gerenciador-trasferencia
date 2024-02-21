package com.gerenciador.tarefas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Entregador;
import com.gerenciador.tarefas.entity.Transferencia;
import com.gerenciador.tarefas.permissoes.StatusTransferenciaEnum;
import com.gerenciador.tarefas.repository.IEntregadorRepository;
import com.gerenciador.tarefas.repository.ITransferenciaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferenciaService {
    @Autowired
    private ITransferenciaRepository iTransferenciaRepository;

    @Autowired IEntregadorRepository iEntregadorRepository;

    public Transferencia salvarTransferencia(Transferencia transferencia) {
        return this.iTransferenciaRepository.save(transferencia);
    }

    public List<Transferencia> obtemTransferencias() {
        return this.iTransferenciaRepository.findAll();
    }

    public Transferencia findById(long id){
        Optional<Transferencia> obj = iTransferenciaRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException());
    }

    public Transferencia atualizarStatusEEntregador(Long id, String novoStatus, Long idEntregador) {
        Transferencia transferencia = findById(id);
        Entregador entregador = iEntregadorRepository.findById(idEntregador)
                .orElseThrow(() -> new ResourceNotFoundException("Entregador não encontrado com o ID: " + idEntregador));
    
        transferencia.setStatus(StatusTransferenciaEnum.valueOf(novoStatus.toUpperCase()));
        transferencia.setEntregador(entregador);
    
        return salvarTransferencia(transferencia);
    }

    public List<Transferencia> buscarTransferenciasPendentes() {
        return this.iTransferenciaRepository.findByStatus(StatusTransferenciaEnum.PENDENTE);
    }

    public List<Transferencia> buscarTransferenciasEnviadas() {
        return this.iTransferenciaRepository.findByStatus(StatusTransferenciaEnum.ENVIADA);
    }

    public List<Transferencia> buscarTransferenciasRecebidas() {
        return this.iTransferenciaRepository.findByStatus(StatusTransferenciaEnum.FINALIZADA);
    }
    
    public List<Transferencia> buscarPorStatusELojaRemetentePendente(String status, String lojaRemetente) {
        return this.iTransferenciaRepository.findByStatusAndLojaRemetente(StatusTransferenciaEnum.PENDENTE,
        lojaRemetente);
    }

    public List<Transferencia> buscarPorStatusELojaDestinatarioEnviada(String status, String lojaDestinatario) {
        return this.iTransferenciaRepository.findByStatusAndLojaDestinatario(StatusTransferenciaEnum.ENVIADA,
        lojaDestinatario);
    }
}
