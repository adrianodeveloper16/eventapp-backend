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
@Table(name = "tb_evento_categoria")
public class EventoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_categoria")
    private Integer idEventoCategoria;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "data_inclusao", nullable = false, updatable = false)
    private LocalDateTime dataInclusao;

    @Column(name = "ativo", nullable = false, length = 1)
    private String ativo;

    @PrePersist
    public void prePersist() {
        this.dataInclusao = LocalDateTime.now();
    }

    // Getters e Setters
    public Integer getIdEventoCategoria() { return idEventoCategoria; }
    public void setIdEventoCategoria(Integer idEventoCategoria) { this.idEventoCategoria = idEventoCategoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDateTime dataInclusao) { this.dataInclusao = dataInclusao; }

    public String getAtivo() { return ativo; }
    public void setAtivo(String ativo) { this.ativo = ativo; }
}
