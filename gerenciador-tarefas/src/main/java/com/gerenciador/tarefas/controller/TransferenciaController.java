package com.gerenciador.tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.tarefas.entity.Transferencia;
import com.gerenciador.tarefas.permissoes.StatusTransferenciaEnum;
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

    @PutMapping("/{id}/status")
    public ResponseEntity<String> atualizarStatusTransferencia(
            @PathVariable Long id,
            @RequestParam(name = "status") String novoStatus) {
        Transferencia transferencia = transferenciaService.findById(id);

        transferencia.setStatus(StatusTransferenciaEnum.valueOf(novoStatus.toUpperCase()));
        transferenciaService.salvarTransferencia(transferencia);

        return ResponseEntity.ok().body("Status da transferência atualizado com sucesso para: " + novoStatus);
    }

    @PutMapping("/{id}/status-entregador")
    public ResponseEntity<String> atualizarStatusEEntregador(
            @PathVariable Long id,
            @RequestParam(name = "status") String novoStatus,
            @RequestParam(name = "entregador") Long idEntregador) {
        Transferencia transferencia = transferenciaService.atualizarStatusEEntregador(id, novoStatus, idEntregador);

        return ResponseEntity.ok()
                .body("Status e entregador da transferência atualizados com sucesso para: " + novoStatus);
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> obtemTransferencias() {
        return new ResponseEntity<>(transferenciaService.obtemTransferencias(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transferencia> findById(@PathVariable Long id) {
        Transferencia obj = transferenciaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Transferencia>> buscarTransferenciasPendentes() {
        List<Transferencia> transferenciasPendentes = transferenciaService.buscarTransferenciasPendentes();
        return ResponseEntity.ok().body(transferenciasPendentes);
    }

    @GetMapping("/enviadas")
    public ResponseEntity<List<Transferencia>> buscarTransferenciasEnviadas() {
        List<Transferencia> transferenciasPendentes = transferenciaService.buscarTransferenciasEnviadas();
        return ResponseEntity.ok().body(transferenciasPendentes);
    }

    @GetMapping("/recebidas")
    public ResponseEntity<List<Transferencia>> buscarTransferenciasRecebidas() {
        List<Transferencia> transferenciasPendentes = transferenciaService.buscarTransferenciasRecebidas();
        return ResponseEntity.ok().body(transferenciasPendentes);
    }

    @GetMapping("/{lojaRemetente}/pendentes")
    public ResponseEntity<List<Transferencia>> buscarPorLojaRemetentePendetes(
            @PathVariable String lojaRemetente,
            @RequestParam(name = "status", required = false, defaultValue = "PENDENTE,ENVIADA") List<String> status) {
        List<Transferencia> transferencias = transferenciaService.buscarPorStatusELojaRemetentePendente(status,
                lojaRemetente);
        return ResponseEntity.ok().body(transferencias);
    }
    
    @GetMapping("/{lojaDestinatario}/enviadas")
    public ResponseEntity<List<Transferencia>> buscarPorLojaDestinatarioEnviadas(
            @PathVariable String lojaDestinatario,
            @RequestParam(name = "status", required = false, defaultValue = "ENVIADA,PENDENTE") List<String> status) {
        List<Transferencia> transferencias = transferenciaService.buscarPorStatusELojaDestinatarioEnviada(status,
                lojaDestinatario);
        return ResponseEntity.ok().body(transferencias);
    }
}
