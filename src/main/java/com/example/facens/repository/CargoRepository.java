package com.example.facens.repository;

import com.example.facens.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    List<Cargo> findByAtivo(String ativo);
}