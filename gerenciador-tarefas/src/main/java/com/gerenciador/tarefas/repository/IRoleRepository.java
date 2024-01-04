package com.gerenciador.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.permissoes.PermissaoEnum;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{

    Role findByNome(PermissaoEnum nome);
    
}
