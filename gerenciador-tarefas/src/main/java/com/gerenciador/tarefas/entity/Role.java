package com.gerenciador.tarefas.entity;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gerenciador.tarefas.permissoes.PermissaoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Data
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private PermissaoEnum nome;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<Usuario> usuarios;

}
