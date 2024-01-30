package com.gerenciador.tarefas.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.tarefas.permissoes.LojasEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Data
@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, length = 100)
    @NotNull
    private String username;

    @Column(length = 150)
    @NotNull
    private String password;

    @Column(length = 150)
    @NotNull
    private LojasEnum lojaDeRegistro;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuario_roles", 
        joinColumns = @JoinColumn(name = "usuario_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"), 
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "role_id"}))
    private List<Role> roles;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transferencia> transferencias;

    public Usuario(){

    }

    public Usuario(Long id, String username, List<Role> roles){
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

}
