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
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo cargo;

    @Column(name = "data_inclusao", nullable = false, updatable = false)
    private LocalDateTime dataInclusao;

    @Column(name = "usu_inclusao", length = 100)
    private String usuInclusao;

    @Column(name = "ativo", nullable = false, length = 1)
    private String ativo;

    @PrePersist
    public void prePersist() {
        this.dataInclusao = LocalDateTime.now();
        if (this.ativo == null) this.ativo = "S";
    }

    // Getters e Setters
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public LocalDateTime getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDateTime dataInclusao) { this.dataInclusao = dataInclusao; }

    public String getUsuInclusao() { return usuInclusao; }
    public void setUsuInclusao(String usuInclusao) { this.usuInclusao = usuInclusao; }

    public String getAtivo() { return ativo; }
    public void setAtivo(String ativo) { this.ativo = ativo; }
}
