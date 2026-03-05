/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facens.controller;

/**
 *
 * @author absil
 */

import com.example.facens.model.EventoCategoria;
import com.example.facens.service.EventoCategoriaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class EventoCategoriaController {
    
    @Autowired
    private EventoCategoriaService categoriaService;

    @GetMapping
    public List<EventoCategoria> listarAtivas() {
        return categoriaService.listarAtivas();
    }
}
