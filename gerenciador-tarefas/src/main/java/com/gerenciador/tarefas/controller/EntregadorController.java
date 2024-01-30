package com.gerenciador.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.entity.Entregador;
import com.gerenciador.tarefas.service.EntregadorService;

@RestController
@RequestMapping(value = "/entregadores")
public class EntregadorController {

    @Autowired
    private EntregadorService entregadorService;

    @PostMapping
    public ResponseEntity<String> salvarEntregador(@RequestBody Entregador entregador) {

        Entregador entregadorSalvo = entregadorService.salvarEntregador(entregador);

        return new ResponseEntity<>("Novo entregador criado: " + entregadorSalvo.getNomeCompleto(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Entregador>> obtemEntregadoresAtivos() {
        return new ResponseEntity<>(entregadorService.obtemEntregadoresAtivos(), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Entregador>> obtemEntregadores() {
        return new ResponseEntity<>(entregadorService.obtemEntregadores(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Entregador> encontrarEntregador(@PathVariable Long id){
        Entregador obj = entregadorService.obtemEntregador(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping
    public ResponseEntity<String> atualizarEntregador(@RequestBody Entregador entregador) {
    
        Entregador entregadorSalvo = entregadorService.atualizarEntregador(entregador);     

        return new ResponseEntity<>("Usuario atualizado: " + entregadorSalvo.getNomeCompleto(), HttpStatus.OK);
    }

}
