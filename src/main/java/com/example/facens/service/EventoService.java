/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facens.service;

/**
 *
 * @author absil
 */

import com.example.facens.model.Evento;
import com.example.facens.model.EventoCategoria;
import com.example.facens.model.Usuario;
import com.example.facens.repository.EventoCategoriaRepository;
import com.example.facens.repository.EventoInscricaoRepository;
import com.example.facens.repository.EventoRepository;
import com.example.facens.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private EventoCategoriaRepository categoriaRepository;
    
    @Autowired
    private EventoInscricaoRepository inscricaoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findByAtivo("S");
    }

    public List<Evento> listarPorOrganizador(Integer idUsuario) {
        return eventoRepository.findByUsuarioIdUsuarioAndAtivo(idUsuario, "S");
    }

    public Optional<Evento> buscarPorId(Integer id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> buscarPorTitulo(String termo) {
        return eventoRepository.buscarPorTitulo(termo);
    }

    public List<Evento> buscarPorCategoria(Integer idCategoria) {
        return eventoRepository.buscarPorCategoria(idCategoria);
    }

    public Evento criar(Evento evento, Integer idUsuario, Integer idCategoria) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        EventoCategoria categoria = categoriaRepository.findById(idCategoria)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        evento.setUsuario(usuario);
        evento.setCategoria(categoria);
        evento.setAtivo("S");
        return eventoRepository.save(evento);
    }

    public Evento atualizar(Integer id, Evento dadosNovos, Integer idCategoria) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado!"));

        EventoCategoria categoria = categoriaRepository.findById(idCategoria)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        evento.setTitulo(dadosNovos.getTitulo());
        evento.setDescricao(dadosNovos.getDescricao());
        evento.setData(dadosNovos.getData());
        evento.setDuracao(dadosNovos.getDuracao());
        evento.setLocal(dadosNovos.getLocal());
        evento.setLink(dadosNovos.getLink());
        evento.setNrVagas(dadosNovos.getNrVagas());
        evento.setCategoria(categoria);
        return eventoRepository.save(evento);
    }

    public void excluir(Integer id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado!"));
        evento.setAtivo("N");
        eventoRepository.save(evento);
    }
    
    public int vagasDisponiveis(Integer idEvento) {
    long inscritos = inscricaoRepository.countByEventoIdEventoAndAtivo(idEvento, "S");
    Evento evento = eventoRepository.findById(idEvento)
        .orElseThrow(() -> new RuntimeException("Evento não encontrado!"));
    return evento.getNrVagas() - (int) inscritos;
}
}