# 📚 **Literalura - Challenge Alura Latam**

Proyecto diseñado como un **Catálogo de Libros** interactivo que consume datos reales de la API **Gutendex**. El objetivo es permitir a los usuarios buscar libros por título, almacenarlos en una base de datos local y realizar consultas avanzadas sobre autores y obras registradas.

Este desafío forma parte de la formación de **Alura Latam** (programa **Oracle Next Education**) y pone a prueba habilidades en consumo de APIs externas, persistencia de datos con **Spring Data JPA** y manejo de bases de datos relacionales.

---

### 🚀 **Funcionalidades Principales**
Nuestra aplicación de consola permite realizar las siguientes acciones de forma eficiente:

* **Búsqueda Inteligente:** Localiza libros por su título consumiendo la API de Gutendex.
* **Persistencia Automática:** Al buscar un libro, este se guarda automáticamente en la base de datos junto con su autor.
* **Listado de Biblioteca:** Visualiza todos los libros que has registrado previamente.
* **Consulta de Autores:** Lista todos los autores almacenados, incluyendo sus años de nacimiento y fallecimiento.
* **Filtro Histórico:** Busca qué autores de tu base de datos estaban vivos en un año específico.
* **Filtro por Idioma:** Consulta libros registrados según su código de idioma (es, en, fr, pt).

---

### 🛠️ **Tecnologías Utilizadas**
* **Lenguaje:** Java 17 (Temurin)
* **Framework:** Spring Boot 3.2.4
* **Base de Datos:** PostgreSQL
* **Persistencia:** Spring Data JPA & Hibernate
* **Manejo de JSON:** Jackson
* **Gestión de Dependencias:** Maven
* **API Externa:** [Gutendex](https://gutendex.com/)

---

### 📸 **Cómo usarlo**
1. **Configuración:** Asegúrate de tener una base de datos llamada `literalura_db` en tu PostgreSQL.
2. **Credenciales:** Configura tu usuario y contraseña en el archivo `application.properties`.
3. **Ejecución:** Corre la aplicación desde tu IDE (IntelliJ IDEA).
4. **Menú Interactivo:** Usa los números del **0 al 5** para navegar por las opciones:
    * **Opción 1:** Busca un libro por título (ej: "Don Quijote" o "Pride and Prejudice").
    * **Opción 2:** Lista todos los libros que ya guardaste.
    * **Opción 4:** Ingresa un año para descubrir qué escritores vivían en esa época.
    * **Opción 5:** Escribe el código del idioma (ej: `es` para español o `en` para inglés).

---

> ✨ Proyecto desarrollado como parte del programa **Oracle Next Education - ALURA LATAM**.