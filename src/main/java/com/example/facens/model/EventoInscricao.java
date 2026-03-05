/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facens.model;

/**
 *
 * @author absil
 */
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_evento_inscricao")
public class EventoInscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_inscricao")
    private Integer idEventoInscricao;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "data_inclusao", nullable = false, updatable = false)
    private LocalDateTime dataInclusao;

    @Column(name = "ativo", nullable = false, length = 1)
    private String ativo;

    @PrePersist
    public void prePersist() {
        this.dataInclusao = LocalDateTime.now();
        if (this.ativo == null) this.ativo = "S";
    }

    // Getters e Setters
    public Integer getIdEventoInscricao() { return idEventoInscricao; }
    public void setIdEventoInscricao(Integer id) { this.idEventoInscricao = id; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDateTime getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDateTime dataInclusao) { this.dataInclusao = dataInclusao; }

    public String getAtivo() { return ativo; }
    public void setAtivo(String ativo) { this.ativo = ativo; }
}
