package com.example.facens.repository;

import com.example.facens.model.EventoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoCategoriaRepository extends JpaRepository<EventoCategoria, Integer> {
    List<EventoCategoria> findByAtivo(String ativo);
}