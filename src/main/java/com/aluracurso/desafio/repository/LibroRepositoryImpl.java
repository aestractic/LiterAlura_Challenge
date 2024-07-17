package com.aluracurso.desafio.repository;

import com.aluracurso.desafio.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LibroRepositoryImpl implements LibroRepositoryCustom {
    private List<Libro> libros = new ArrayList<>();

    @Override
    public Libro save(Libro libro) {
        libros.add(libro);
        return libro;
    }

    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(libros);
    }

    @Override
    public List<Libro> findByIdioma(String idioma) {
        return libros.stream()
                .filter(libro -> libro.getIdioma().equalsIgnoreCase(idioma))
                .collect(Collectors.toList());
    }
}
