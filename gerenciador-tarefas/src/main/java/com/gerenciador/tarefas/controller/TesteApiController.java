package com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteApiController {

    @GetMapping("/teste")
    private String teste(){
        return "Sucesso";
    }

    @GetMapping("/teste-api-bem-vindo")
    public String testeBemVindo(@RequestParam(name="nome") String nome){
        return "Ola " + nome + " Seja muio bem vindo!";
    }
}
