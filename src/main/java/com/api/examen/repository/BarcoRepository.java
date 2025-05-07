package com.api.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.examen.entity.Barco;

public interface BarcoRepository extends JpaRepository<Barco, Integer> {

}