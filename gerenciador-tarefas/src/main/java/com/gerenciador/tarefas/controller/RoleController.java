package com.gerenciador.tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.service.RoleService;

import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Endpoint para obter todas as roles
    @GetMapping
    public ResponseEntity<List<Role>> obterTodasRoles() {
        List<Role> roles = roleService.obterTodasRoles();
        return ResponseEntity.ok(roles);
    }
}
