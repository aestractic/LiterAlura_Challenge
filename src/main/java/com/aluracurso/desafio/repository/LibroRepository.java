package com.aluracurso.desafio.repository;

import com.aluracurso.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>, LibroRepositoryCustom {
}
