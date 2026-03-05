/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facens.service;

/**
 *
 * @author absil
 */

import com.example.facens.model.EventoCategoria;
import com.example.facens.repository.EventoCategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventoCategoriaService {
    
    @Autowired
    private EventoCategoriaRepository categoriaRepository;

    public List<EventoCategoria> listarAtivas() {
        return categoriaRepository.findByAtivo("S");
    }
}
