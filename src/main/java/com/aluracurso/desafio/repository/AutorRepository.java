package com.aluracurso.desafio.repository;


import com.aluracurso.desafio.model.Autor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository {
    List<Autor> findAll();
    List<Autor> findAutoresVivosPorAnio(int anio);
}
