package com.aluracurso.desafio.repository;

import com.aluracurso.desafio.model.Autor;

import java.util.ArrayList;
import java.util.List;


public class AutorRepositoryImpl implements AutorRepository {
    private List<Autor> autores = new ArrayList<>();

    @Override
    public List<Autor> findAll() {
        return List.of();
    }

    @Override
    public List<Autor> findAutoresVivosPorAnio(int anio) {
        return List.of();
    }
}
