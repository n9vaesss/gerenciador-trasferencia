package com.gerenciador.tarefas.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.gerenciador.tarefas.permissoes.StatusTransferenciaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transferencias")
@Data
@Getter
@Setter
public class Transferencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, length = 100)
    @NotNull
    private String numeroNota;

    @Column(length = 100)
    @NotNull
    private String lojaRemetente;

    @Column(length = 100)
    @NotNull
    private String lojaDestinatario;

    @Column(length = 100)
    @NotNull
    private LocalDateTime horaDeRegistro = getHoraAtualBrasil();

    @Column(length = 100)
    @NotNull
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusTransferenciaEnum status;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;

    private LocalDateTime getHoraAtualBrasil() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime brasilTime = zonedDateTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        return brasilTime.toLocalDateTime();
    }
}