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
@Table(name = "tb_cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Integer idCargo;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "admin", nullable = false, length = 1)
    private String admin;

    @Column(name = "ativo", nullable = false, length = 1)
    private String ativo;

    @Column(name = "data_inclusao", nullable = false, updatable = false)
    private LocalDateTime dataInclusao;

    @PrePersist
    public void prePersist() {
        this.dataInclusao = LocalDateTime.now();
    }

    // Getters e Setters
    public Integer getIdCargo() { return idCargo; }
    public void setIdCargo(Integer idCargo) { this.idCargo = idCargo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getAdmin() { return admin; }
    public void setAdmin(String admin) { this.admin = admin; }

    public String getAtivo() { return ativo; }
    public void setAtivo(String ativo) { this.ativo = ativo; }

    public LocalDateTime getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDateTime dataInclusao) { this.dataInclusao = dataInclusao; }
}
