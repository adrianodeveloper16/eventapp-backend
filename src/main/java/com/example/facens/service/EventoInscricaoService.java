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
import com.example.facens.model.EventoInscricao;
import com.example.facens.repository.EventoInscricaoRepository;
import com.example.facens.repository.EventoRepository;
import com.example.facens.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class EventoInscricaoService {
    
    @Autowired
    private EventoInscricaoRepository inscricaoRepository;
    
    @Autowired
    private EventoRepository eventoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<EventoInscricao> listarPorUsuario(Integer idUsuario) {
        return inscricaoRepository.findByUsuarioIdUsuarioAndAtivo(idUsuario, "S");
    }

    public List<EventoInscricao> listarPorEvento(Integer idEvento) {
        return inscricaoRepository.findByEventoIdEventoAndAtivo(idEvento, "S");
    }

   public EventoInscricao inscrever(Integer idEvento, Integer idUsuario) {
    Evento evento = eventoRepository.findById(idEvento)
        .orElseThrow(() -> new RuntimeException("Evento não encontrado!"));

    usuarioRepository.findById(idUsuario)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

    if (!"S".equals(evento.getAtivo())) {
        throw new RuntimeException("Evento inativo!");
    }

    // Verifica se já está inscrito e ativo
    if (inscricaoRepository.existsByEventoIdEventoAndUsuarioIdUsuarioAndAtivo(
            idEvento, idUsuario, "S")) {
        throw new RuntimeException("Usuário já inscrito neste evento!");
    }

    // Verifica se existe inscrição cancelada e reativa
    Optional<EventoInscricao> inscricaoExistente = inscricaoRepository
        .findByEventoIdEventoAndUsuarioIdUsuario(idEvento, idUsuario);

    if (inscricaoExistente.isPresent()) {
        long inscritos = inscricaoRepository.countByEventoIdEventoAndAtivo(idEvento, "S");
        if (inscritos >= evento.getNrVagas()) {
            throw new RuntimeException("Evento sem vagas disponíveis!");
        }
        // Reativa a inscrição existente
        EventoInscricao inscricao = inscricaoExistente.get();
        inscricao.setAtivo("S");
        inscricao.setDataInclusao(LocalDateTime.now());
        return inscricaoRepository.save(inscricao);
    }

    // Cria nova inscrição
    long inscritos = inscricaoRepository.countByEventoIdEventoAndAtivo(idEvento, "S");
    if (inscritos >= evento.getNrVagas()) {
        throw new RuntimeException("Evento sem vagas disponíveis!");
    }

    EventoInscricao inscricao = new EventoInscricao();
    inscricao.setEvento(evento);
    inscricao.setUsuario(usuarioRepository.findById(idUsuario).get());
    inscricao.setAtivo("S");
    return inscricaoRepository.save(inscricao);
  }

    public void cancelar(Integer idEvento, Integer idUsuario) {
        EventoInscricao inscricao = inscricaoRepository
            .findByEventoIdEventoAndUsuarioIdUsuario(idEvento, idUsuario)
            .orElseThrow(() -> new RuntimeException("Inscrição não encontrada!"));

        inscricao.setAtivo("N");
        inscricaoRepository.save(inscricao);
    }
}