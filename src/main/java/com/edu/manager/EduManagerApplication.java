package com.edu.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación EduManager.
 *
 * Esta clase arranca el contexto de Spring Boot y lanza la aplicación.
 *
 * Anotaciones: - @SpringBootApplication: indica que esta es la clase principal
 * de Spring Boot. Combina @Configuration, @EnableAutoConfiguration
 * y @ComponentScan.
 */
@SpringBootApplication
public class EduManagerApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 *
	 * @param args argumentos de línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(EduManagerApplication.class, args);
	}
}