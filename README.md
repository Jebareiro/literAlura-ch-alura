# 📚 BookStore Global Catalog - Challenge Alura Latam (In Progress 🚧)

> **Estado:** 🚧 *In Progress* / Proyecto en fase de unificación e integración de servicios.
> 
> **Programa:** Oracle Next Education (ONE) & Alura Latam  
> **Especialidad:** ONE Tech Foundation G9 - Back End

---

## 📖 Descripción del Proyecto

Este proyecto nace de la unificación e integración de dos desafíos del programa **ONE Tech Foundation G9 - Back End**: el *Conversor de Monedas* y *LiterAlura*.

La aplicación está desarrollada en **Java 17** con **Spring Boot 3** y actúa como un sistema de **catálogo e internacionalización de literatura**. Permite consumir datos reales de obras y autores desde la API de [Gutendex](https://gutendex.com/), almacenarlos en una base de datos relacional PostgreSQL, e integrar la API de [ExchangeRate-API](https://www.exchangerate-api.com/) para calcular y mostrar el precio de los libros en múltiples divisas internacionales en tiempo real.

---

## 🚀 Funcionalidades Principales

- **🔍 Búsqueda y Persistencia Automática:** Localiza libros por su título consumiendo la API de Gutendex y registra automáticamente las obras junto con sus autores en PostgreSQL.
- **💱 Precios Dinámicos Multidivisa (*In Progress*):** Conversión automática de precios a monedas locales (**USD, ARS, BRL, COP, PYG, EUR**) utilizando tasas de cambio en tiempo real.
- **📚 Consultas e Historiales:**
  - Listado completo de la biblioteca registrada.
  - Filtro de autores vivos en un año específico.
  - Consulta de libros por idioma (`es`, `en`, `fr`, `pt`).
- **🔐 Gestión Segura de Credenciales:** Uso de variables de entorno y archivos de configuración para proteger credenciales de la base de datos y llaves de APIs.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java 17
- **Framework:** Spring Boot 3.2.x
- **Persistencia de Datos:** Spring Data JPA / Hibernate
- **Base de Datos:** PostgreSQL
- **Manejo de JSON / APIs:** Jackson
- **APIs Externas Integradas:**
  - [Gutendex API](https://gutendex.com/) *(Catálogo de libros)*
  - [ExchangeRate-API](https://www.exchangerate-api.com/) *(Tasas de cambio)*
- **Gestión de Dependencias:** Maven

---

## ⚙️ Requisitos e Instalación

### Requisitos Previos
- **Java 17** o superior.
- **PostgreSQL** (puerto `5432`).
- Una API Key válida de **ExchangeRate-API**.

### Configuración
1. Crear la base de datos en PostgreSQL:
   ```sql
   CREATE DATABASE literalura_db;
