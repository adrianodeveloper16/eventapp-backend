/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facens.controller;

/**
 *
 * @author absil
 */

import com.example.facens.model.Evento;
import com.example.facens.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "http://localhost:4200")
public class EventoController {
    
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> listarTodos(
        @RequestParam(required = false) String titulo,
        @RequestParam(required = false) Integer idCategoria) {

        if (titulo != null && !titulo.isBlank()) {
            return eventoService.buscarPorTitulo(titulo);
        }
        if (idCategoria != null) {
            return eventoService.buscarPorCategoria(idCategoria);
        }
        return eventoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        return eventoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/organizador/{idUsuario}")
    public List<Evento> listarPorOrganizador(@PathVariable Integer idUsuario) {
        return eventoService.listarPorOrganizador(idUsuario);
    }

    @PostMapping
    public ResponseEntity<?> criar(
        @RequestBody Evento evento,
        @RequestParam Integer idUsuario,
        @RequestParam Integer idCategoria) {
        try {
            return ResponseEntity.ok(eventoService.criar(evento, idUsuario, idCategoria));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
        @PathVariable Integer id,
        @RequestBody Evento evento,
        @RequestParam Integer idCategoria) {
        try {
            return ResponseEntity.ok(eventoService.atualizar(id, evento, idCategoria));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            eventoService.excluir(id);
            return ResponseEntity.ok(Map.of("mensagem", "Evento excluído com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/vagas")
    public ResponseEntity<?> vagasDisponiveis(@PathVariable Integer id) {
        try {
            int vagas = eventoService.vagasDisponiveis(id);
            return ResponseEntity.ok(Map.of("vagasDisponiveis", vagas));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}
