/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.facens.service;

/**
 *
 * @author absil
 */

import com.example.facens.model.Usuario;
import com.example.facens.repository.CargoRepository;
import com.example.facens.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CargoRepository cargoRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado!");
        }
        usuario.setAtivo("S");
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Senha incorreta!");
        }
        if (!"S".equals(usuario.getAtivo())) {
            throw new RuntimeException("Usuário inativo!");
        }
        return usuario;
    }

    public Usuario atualizar(Integer id, Usuario dadosNovos) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        usuario.setNome(dadosNovos.getNome());
        usuario.setEmail(dadosNovos.getEmail());
        if (dadosNovos.getSenha() != null && !dadosNovos.getSenha().isBlank()) {
            usuario.setSenha(dadosNovos.getSenha());
        }
        return usuarioRepository.save(usuario);
    }

    public void inativar(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        usuario.setAtivo("N");
        usuarioRepository.save(usuario);
    }
}
