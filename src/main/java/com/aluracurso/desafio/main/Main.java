package com.aluracurso.desafio.main;

import com.aluracurso.desafio.model.*;
import com.aluracurso.desafio.repository.AutorRepositoryImpl;
import com.aluracurso.desafio.repository.LibroRepository;
import com.aluracurso.desafio.repository.AutorRepository;
import com.aluracurso.desafio.repository.LibroRepositoryImpl;
import com.aluracurso.desafio.service.ConsumoAPI;
import com.aluracurso.desafio.service.ConvierteDatos;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Main {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository bookRepository;
    private AutorRepository authorRepository;

    public Main(LibroRepository bookRepository, AutorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libros por título
                    2 - Listar libros
                    3 - Listar autores
                    4 - Listar autores vivos en un año determinado
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresPorAnio();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el título del libro que deseas buscar");
        String tituloLibro = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + tituloLibro.replace(" ", "+"));
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        if (datos != null && datos.getResults() != null && !datos.getResults().isEmpty()) {
            Libro libro = new Libro();
            bookRepository.save(libro);
            System.out.println("Libro guardado: " + libro);
        } else {
            System.out.println("No se encontró el libro.");
        }
    }

    private void listarLibros() {
        List<Libro> libros = bookRepository.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autores = authorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresPorAnio() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        int anio = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autoresVivos = authorRepository.findAutoresVivosPorAnio(anio);
        autoresVivos.forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el código de idioma (por ejemplo, 'en' para inglés, 'es' para español):");
        String idioma = teclado.nextLine();
        List<Libro> librosPorIdioma = bookRepository.findByIdioma(idioma);
        librosPorIdioma.forEach(System.out::println);
    }

}
