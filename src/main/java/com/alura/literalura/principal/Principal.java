package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosAutor;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.model.RespuestaApi;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepo;
    private AutorRepository autorRepo;

    // Constructor que recibe los repositorios desde la clase Application
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepo = libroRepository;
        this.autorRepo = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ----------------------------------
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    ----------------------------------
                    """;
            System.out.println(menu);

            if (lectura.hasNextInt()) {
                opcion = lectura.nextInt();
                lectura.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } else {
                System.out.println("Por favor, ingrese un número.");
                lectura.nextLine();
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var nombreLibro = lectura.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        var datosBusqueda = conversor.obtenerDatos(json, RespuestaApi.class);

        if (datosBusqueda.resultados() != null && !datosBusqueda.resultados().isEmpty()) {
            DatosLibro datosLibro = datosBusqueda.resultados().get(0);

            // 1. Convertir DatosLibro a Entidad Libro
            Libro libro = new Libro(datosLibro);

            // 2. Manejar el Autor para evitar duplicados en la BD
            if (!datosLibro.autor().isEmpty()) {
                DatosAutor datosAutor = datosLibro.autor().get(0);
                Autor autor = autorRepo.findByNombreContainsIgnoreCase(datosAutor.nombre())
                        .orElseGet(() -> {
                            Autor nuevoAutor = new Autor(datosAutor);
                            return autorRepo.save(nuevoAutor);
                        });
                libro.setAutor(autor);
            }

            // 3. Guardar el libro
            try {
                libroRepo.save(libro);
                System.out.println("----- LIBRO REGISTRADO -----");
                System.out.println(libro);
            } catch (Exception e) {
                System.out.println("Aviso: Este libro ya está en tu base de datos.");
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepo.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la base de datos.");
        } else {
            libros.stream()
                    .sorted(Comparator.comparing(Libro::getTitulo))
                    .forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(System.out::println);
        }
    }


    private void listarAutoresVivos() {
        System.out.println("Ingrese el año que desea consultar:");
        if (lectura.hasNextInt()) {
            var anio = lectura.nextInt();
            lectura.nextLine();
            List<Autor> autores = autorRepo.buscarAutoresVivosEnDeterminadoAnio(anio);
            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio);
            } else {
                autores.forEach(System.out::println);
            }
        } else {
            System.out.println("Año no válido.");
            lectura.nextLine();
        }
    }

    private void listarLibrosPorIdioma() {
        var menuIdiomas = """
            Ingrese el idioma para buscar los libros:
            es - español
            en - inglés
            fr - francés
            pt - portugués
            """;
        System.out.println(menuIdiomas);
        var idioma = lectura.nextLine();
        List<Libro> libros = libroRepo.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma.");
        } else {
            libros.forEach(System.out::println);
        }
    }
}