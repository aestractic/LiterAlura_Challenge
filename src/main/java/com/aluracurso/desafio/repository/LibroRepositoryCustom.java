package com.aluracurso.desafio.repository;

import com.aluracurso.desafio.model.Libro;

import java.util.List;

public interface LibroRepositoryCustom {
    Libro save(Libro libro);

    List<Libro> findAll();

    List<Libro> findByIdioma(String idioma);
}
