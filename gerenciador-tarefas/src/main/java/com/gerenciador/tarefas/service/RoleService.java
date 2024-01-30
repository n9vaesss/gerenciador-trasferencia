package com.gerenciador.tarefas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.repository.IRoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private IRoleRepository iRoleRepository;

    public List<Role> obterTodasRoles() {
        return this.iRoleRepository.findAll();
    }

}