/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facens.controller;

/**
 *
 * @author absil
 */

import com.example.facens.model.EventoInscricao;
import com.example.facens.service.EventoInscricaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/inscricoes")
@CrossOrigin(origins = "http://localhost:4200")
public class EventoInscricaoController {
    
    @Autowired
    private EventoInscricaoService inscricaoService;

    @GetMapping("/usuario/{idUsuario}")
    public List<EventoInscricao> listarPorUsuario(@PathVariable Integer idUsuario) {
        return inscricaoService.listarPorUsuario(idUsuario);
    }

    @GetMapping("/evento/{idEvento}")
    public List<EventoInscricao> listarPorEvento(@PathVariable Integer idEvento) {
        return inscricaoService.listarPorEvento(idEvento);
    }

    @PostMapping
    public ResponseEntity<?> inscrever(
        @RequestParam Integer idEvento,
        @RequestParam Integer idUsuario) {
        try {
            return ResponseEntity.ok(inscricaoService.inscrever(idEvento, idUsuario));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> cancelar(
        @RequestParam Integer idEvento,
        @RequestParam Integer idUsuario) {
        try {
            inscricaoService.cancelar(idEvento, idUsuario);
            return ResponseEntity.ok(Map.of("mensagem", "Inscrição cancelada com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}
