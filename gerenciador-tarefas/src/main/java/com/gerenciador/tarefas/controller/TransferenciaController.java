package com.gerenciador.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.entity.Transferencia;
import com.gerenciador.tarefas.service.TransferenciaService;

@RestController
@RequestMapping(value = "/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<String> salvarTransferencia(@RequestBody Transferencia transferencia) {

        Transferencia transferenciaSalva = transferenciaService.salvarTransferencia(transferencia);

        return new ResponseEntity<>("Nova transferencia criada: " + transferenciaSalva.getNumeroNota(),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> obtemTransferencias() {
        return new ResponseEntity<>(transferenciaService.obtemTransferencias(), HttpStatus.OK);
    }

    @GetMapping("/{lojaDestinatario}")
    public ResponseEntity<List<Transferencia>> buscarPorLojaDestinatario(
            @PathVariable String lojaDestinatario,
            @RequestParam(name = "status", required = false, defaultValue = "ENVIADA") String status) {
        List<Transferencia> transferencias = transferenciaService.buscarPorStatusELojaDestinatario(status, lojaDestinatario);
        return ResponseEntity.ok().body(transferencias);
    }
}
