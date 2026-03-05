package com.example.facens.repository;

import com.example.facens.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByUsuarioIdUsuarioAndAtivo(Integer idUsuario, String ativo);
    List<Evento> findByAtivo(String ativo);

    @Query("SELECT e FROM Evento e WHERE e.ativo = 'S' AND " +
           "LOWER(e.titulo) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Evento> buscarPorTitulo(@Param("termo") String termo);

    @Query("SELECT e FROM Evento e WHERE e.ativo = 'S' AND " +
           "e.categoria.idEventoCategoria = :idCategoria")
    List<Evento> buscarPorCategoria(@Param("idCategoria") Integer idCategoria);
}