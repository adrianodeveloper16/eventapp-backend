package com.example.facens.repository;

import com.example.facens.model.EventoInscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EventoInscricaoRepository extends JpaRepository<EventoInscricao, Integer> {
    List<EventoInscricao> findByUsuarioIdUsuarioAndAtivo(Integer idUsuario, String ativo);
    List<EventoInscricao> findByEventoIdEventoAndAtivo(Integer idEvento, String ativo);
    Optional<EventoInscricao> findByEventoIdEventoAndUsuarioIdUsuario(Integer idEvento, Integer idUsuario);
    boolean existsByEventoIdEventoAndUsuarioIdUsuarioAndAtivo(Integer idEvento, Integer idUsuario, String ativo);
    long countByEventoIdEventoAndAtivo(Integer idEvento, String ativo);
}