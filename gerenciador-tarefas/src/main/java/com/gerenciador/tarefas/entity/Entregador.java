package com.gerenciador.tarefas.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "entregador")
@Data
@Getter
@Setter
public class Entregador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100)
    @NotNull
    private String nomeCompleto;

    @Column(unique = true, length = 100)
    @NotNull
    private String cpf;
    
    @Column
    @NotNull
    private boolean ativo;

    @OneToMany(mappedBy = "entregador", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transferencia> transferencias;

}
