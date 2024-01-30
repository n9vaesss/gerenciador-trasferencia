package com.gerenciador.tarefas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Entregador;
import com.gerenciador.tarefas.repository.IEntregadorRepository;
import com.gerenciador.tarefas.repository.ITransferenciaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EntregadorService {
    @Autowired
    private IEntregadorRepository iEntregadorRepository;

    @Autowired
    ITransferenciaRepository iTransferenciaRepository;

    public Entregador salvarEntregador(Entregador entregador) {
        return this.iEntregadorRepository.save(entregador);
    }

    public List<Entregador> obtemEntregadoresAtivos() {
        return this.iEntregadorRepository.obtemEntregadoresAtivos();
    }

    public List<Entregador> obtemEntregadores() {
        return this.iEntregadorRepository.findAll();
    }

    public Entregador obtemEntregador(long id){
        
        Optional<Entregador> obj = iEntregadorRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException());
    }

    public Entregador atualizarEntregador(Entregador entregador){
        return this.iEntregadorRepository.save(entregador);
    }
    
}
