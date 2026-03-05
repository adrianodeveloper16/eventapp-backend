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
@Table(name = "tb_evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name = "duracao", nullable = false)
    private Integer duracao;

    @Column(name = "local", length = 200)
    private String local;

    @Column(name = "link", length = 300)
    private String link;

    @ManyToOne
    @JoinColumn(name = "id_evento_categoria", nullable = false)
    private EventoCategoria categoria;

    @Column(name = "nr_vagas", nullable = false)
    private Integer nrVagas;

    @Column(name = "data_inclusao", nullable = false, updatable = false)
    private LocalDateTime dataInclusao;

    @Column(name = "data_exclusao")
    private LocalDateTime dataExclusao;

    @Column(name = "ativo", nullable = false, length = 1)
    private String ativo;

    @PrePersist
    public void prePersist() {
        this.dataInclusao = LocalDateTime.now();
        if (this.ativo == null) this.ativo = "S";
    }

    @Transient
    public String getStatus() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fim = this.data.plusMinutes(this.duracao);
        if (now.isBefore(this.data)) return "FUTURE";
        else if (now.isAfter(fim)) return "FINISHED";
        else return "ONGOING";
    }

    // Getters e Setters
    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public EventoCategoria getCategoria() { return categoria; }
    public void setCategoria(EventoCategoria categoria) { this.categoria = categoria; }

    public Integer getNrVagas() { return nrVagas; }
    public void setNrVagas(Integer nrVagas) { this.nrVagas = nrVagas; }

    public LocalDateTime getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDateTime dataInclusao) { this.dataInclusao = dataInclusao; }

    public LocalDateTime getDataExclusao() { return dataExclusao; }
    public void setDataExclusao(LocalDateTime dataExclusao) { this.dataExclusao = dataExclusao; }

    public String getAtivo() { return ativo; }
    public void setAtivo(String ativo) { this.ativo = ativo; }
}
